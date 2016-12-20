package com.unlcn.ils.tps.services;

import java.math.BigDecimal;  
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.unlcn.ils.tps.*;
import com.unlcn.ils.tps.ininterface.ShipperInterface;
import com.unlcn.ils.tps.ininterface.SqlInterface;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

/**
 * 
 *@Title:
 *@Description:报价单生成服务 
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class QutoationService  implements ArrayContentProvider
{	
	private static Logger logger=Logger.getLogger(Logger.class );
	@Override
	public Page<?> getElements(ArrayContext context) 
	{
		Map<String, Object> conditions = context.getCondition();
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;	
		
		
		
		Dao<E_quotation_head> data=DaoFactory.create(E_quotation_head.class);
		Page<E_quotation_head> datalist = data.selectPageByCondition(conditions, 
				condition,context.getPageable(),context.getSortable(),true);
		
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Page<Map<String,String>> resultdata = new Page<Map<String,String>>();
		List<E_quotation_head> lhead=datalist.getContents();
		List<Map<String, String>> listdata= new ArrayList<Map<String,String>>();
		for ( int i=0;i<lhead.size();i++)
		{
			E_quotation_head head = lhead.get(i);
			Map<String,String> map = new HashMap<String,String>();
			map.put("lineid", head.getLineid().toString());
			map.put("shipperid", head.getShipperId().toString());
			
			Map<String,String> shipper = ShipperInterface.getShipperByID(head.getShipperId().toString());
			if ( shipper == null) continue;
			String shippername = shipper.get("name");
			
			map.put("shippername", shippername);
			map.put("quotationNo", head.getQuotationNo());
			map.put("payperiod", head.getPayPeriod().getLineid().toString());
			map.put("payperiodname",head.getPayPeriod().getPayPeriodName());
			
			String invoiceway ="开票"; 
			if ( head.getInvoiceway()==0)
				invoiceway ="不开票";
			map.put("invoiceway",invoiceway);
			
			map.put("audituser",(head.getAuditUser()==null?"":head.getAuditUser().toString()));
			
			
			map.put("urgent", head.getUrgent().toString());
			String begindate = sdf.format(head.getBeginDate());
			String enddate = sdf.format(head.getEndDate());
			map.put("begindate", begindate);
			map.put("enddate", enddate);
			
			map.put("quotationtype", head.getQuotationType().toString());
			
			map.put("standardpricetotal", removeNullDec(head.getStandardPriceTotal(),2));
			map.put("quotationpricetotal", removeNullDec(head.getQuotationPriceTotal(),2));
			
			Double overflowlv =  Double.parseDouble(removeNullDec(head.getPriceOverflow(),3))*100;
			logger.info("当前的溢价比为:"+overflowlv);
			//Double overflow = Math.ceil(overflowlv);
			
			map.put("priceoverlfow",overflowlv.toString()+"%"	);
			map.put("overflowmemo", head.getOverflowMemo());
			
			map.put("checkflag", removeNull(head.getCheckFlag()));
			map.put("quotationflag", removeNull(head.getQuotationFlag()));
			
			map.put("createuser", head.getCreateUser().toString());
			String createtime = sdf.format(head.getCreateTime());
			map.put("createTime", createtime);
			map.put("transwayname", head.getTranswayName());
			map.put("estimate", removeNull(head.getEstimate()));
			
			map.put("isapply", removeNull(head.getIsapply()));
			
			String applydate = "";
			if (head.getApplyDate() !=null ) applydate = sdf2.format(head.getApplyDate());
			map.put("applydate", applydate);
			
			if ( head.getContractId() == null || head.getContractId() == 0 )
				map.put("contractstatus", "未生成");
			else map.put("contractstatus", "已生成");
			
			if ( head.getUrgent() == 0)
				map.put("urgent", "普通");
			else map.put("urgent", "紧急");
			 
			listdata.add(map);
		}
		 
		int pagesize = context.getPageable().getPageSize();
		int pageindex = context.getPageable().getPageIndex();
		
		
		resultdata.setPageIndex(pageindex);
		resultdata.setPageSize(pagesize);
		resultdata.setTotal(datalist.getTotal());
		resultdata.setContents(listdata);
		
		
		return resultdata;
	}
	/**
	 * 
	 * @param data
	 * @param flag
	 * @return
	 * @Description:
	 */
	public String removeNullDec(java.math.BigDecimal data,int flag)
	{
		if (data == null) return "0";
		else 
		{
			return data.setScale(flag,BigDecimal.ROUND_HALF_UP).toString();
		}
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:
	 */
	public String removeNull(Integer data)
	{
		if (data == null) return "0";
		else return data.toString();
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:
	 */
	public String removeNull(String data)
	{
		return (data == null ? "":data);
	}
	/**
	 * 
	 * @return得到单个报价单主 表
	 * @Description:
	 */
	public E_quotation_head getQuotationData()
	{
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		String lineid=(String) input.get("quotationid");//报价单序号
		
		Dao<E_quotation_head> dao=DaoFactory.create(E_quotation_head.class);
		E_quotation_head data = dao.selectByID(lineid);
		
		return data;
	}
	
	@Transactional
	public String quotationAdd()
	{//保存报价单
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Map<String, Object> input = context.getInput();
		String userid = context.getUser().getId();

		@SuppressWarnings("unchecked")
		Map<String, String> postdata= (Map<String, String>) input.get("postdata");//主数据
		
		String shipperid = postdata.get("shipperid");
		String urgency = postdata.get("urgency");
		String enddate = sdf.format(java.sql.Date.valueOf(postdata.get("enddate")));
		
		String estimate = postdata.get("estimate");
		
		
		String transid = postdata.get("transid");
		String transname = postdata.get("transname");
		
		String overflow = postdata.get("overflow");
		
		String invoice = postdata.get("invoice");
		String payperiod= postdata.get("payperiod");
		String flag = postdata.get("flag"); //报价单类型（1：临时，2：合同）
		
		String lineid = postdata.get("lineid");//报价单序号，0：代表新增
		
		if ( lineid==null) lineid="0";
		Dao<E_quotation_head> daohead=DaoFactory.create(E_quotation_head.class);
		E_quotation_head head = daohead.selectByID(lineid);
		SqlInterface sqlinterface = new SqlInterface();
		boolean isdata = true;
		if ( head == null) 
		{
			head = new E_quotation_head();//不存在，则新增
			lineid= sqlinterface.getNextVal("SEQ_TPS_QUOTATION_HEAD");
			head.setLineid(lineid);
			head.setShipperId(shipperid);//分供方id不能修改
			String quotationno="B"+generateNo();
			head.setQuotationNo(quotationno);//报价单号不能修改
			isdata = false;
		}
		
		
		head.setEstimate(Integer.parseInt(estimate));
		
		E_transway transway = new E_transway();
		transway.setLineid(Integer.parseInt(transid));
		head.setTranswayId(transway);
		head.setTranswayName(transname);
		
		head.setInvoiceway(Integer.parseInt(invoice));
		E_pay_period epayperiod = new E_pay_period();		
		epayperiod.setLineid(Integer.parseInt(payperiod));
		head.setPayPeriod(epayperiod);
		head.setUrgent(Integer.parseInt(urgency));
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		head.setBeginDate(currdate);
		head.setCreateTime(currdate);
		
		
		head.setEndDate(Timestamp.valueOf(enddate));
		
		head.setShareId(0);
		head.setQuotationType(Integer.parseInt(flag));//1:临时报价单,2:合同报价单
		
		head.setOverflowMemo(overflow);
		
		int quotationFlag = 0 ;
		head.setQuotationFlag(quotationFlag);
		head.setCreateUser(userid);		
		head.setActive(1);
		head.setPrintCount(0);
		head.setIsapply(0);
		
		if ( !isdata )
			daohead.insert(head);
		else daohead.update(head);
		
		
		//新增报价明细
		Dao<E_quotation_line> daoline=DaoFactory.create(E_quotation_line.class);
		//检查是否存在，存在则删除
		E_quotation_line searchline = new E_quotation_line();
		searchline.setQuotationId(head);
		List<E_quotation_line> oldline =  daoline.select(searchline);
		daoline.deleteBatch(oldline);//删除以前的报价明细		
		
		@SuppressWarnings("unchecked")
		List<Map<String,String>> data= (List<Map<String,String>>) input.get("data");//报价数据
		List<E_quotation_line> linelist = new ArrayList<E_quotation_line>();
		for (int i = 0; i < data.size(); i++) 
		{
	        Map<String,String> bill = data.get(i);
	        E_quotation_line line = new E_quotation_line();
	        
	        lineid= sqlinterface.getNextVal("SEQ_TPS_QUOTATION_LINE");
			line.setLineid(lineid);
			
			
	        line.setQuotationId(head);
	        line.setCustomerId(bill.get("customerId"));
	        line.setItemId(bill.get("styleId"));
	        line.setStartCityId(Integer.parseInt(bill.get("startCityId")));
	        line.setStartCity(bill.get("startCityName"));
	        line.setDestCity(bill.get("destCityName"));
	        line.setDestCityId(Integer.parseInt(bill.get("destCityId")));
	        line.setQty(new BigDecimal(bill.get("qty")));
	        line.setAccountQty(new BigDecimal(bill.get("accountQty")));
	        line.setStandardPrice(new BigDecimal(bill.get("price")));
	        line.setQuotationPrice(new BigDecimal(bill.get("quotationPrice")));
	       
	        line.setStandardKilometer(new BigDecimal(bill.get("kilometer")));
	        BigDecimal lv ;

	        if(line.getStandardPrice().doubleValue()!=0) 
	        {
	        	lv = (line.getQuotationPrice().subtract(line.getStandardPrice())).divide(line.getStandardPrice(),10,BigDecimal.ROUND_HALF_DOWN);
	        }else lv= new BigDecimal(1000);
	        line.setOverflow(lv);
	        line.setCreateUser(userid);
	        line.setCreateTime(currdate);
	        line.setActive(1);
	        linelist.add(line);
	        
	    }
		
			daoline.insertBatch(linelist);
		
		//更新报价明细完成
		
		//更新报价单主表开始
		HashMap<String,Object> mapprice = 
				daoline.getSession().selectOne("com.unlcn.ils.tps.E_quotation_lineMapper.selectOverflow", head.getLineid());
		
		if (mapprice !=null)
		{
			BigDecimal standard_price = (BigDecimal)mapprice.get("standard_price");
			BigDecimal quotation_price= (BigDecimal)mapprice.get("quotation_price");
			
			E_quotation_head head2 = new E_quotation_head();
			head2.setLineid(head.getLineid());
			head2.setStandardPriceTotal(standard_price);
			head2.setQuotationPriceTotal(quotation_price);	
			BigDecimal headlv;
			if(standard_price.doubleValue()!=0)
	        {

				headlv = (quotation_price.subtract(standard_price)).divide(standard_price,10,BigDecimal.ROUND_HALF_DOWN);
				
	        }else headlv= new BigDecimal(1000);
		    head2.setPriceOverflow(headlv);
		    daohead.update(head2);
		}
		//更新报价单主表结束
		
		
		//新增支付方式
		//检查是否存在，存在则删除
		Dao<E_quotation_pay> daopay=DaoFactory.create(E_quotation_pay.class);
		E_quotation_pay searchpay = new E_quotation_pay();
		searchpay.setQuotationId(head);
		List<E_quotation_pay> oldpay =  daopay.select(searchpay);
		daopay.deleteBatch(oldpay);//删除以前的支付方式
				
		@SuppressWarnings("unchecked")
		List<Map<String,String>> paydata= (List<Map<String,String>>) input.get("payment");//支付方式数据
		List<E_quotation_pay> linepay = new ArrayList<E_quotation_pay>();
		for ( int i = 0;i<paydata.size();i++)
		{
			Map<String,String> pay = paydata.get(i);
	        E_quotation_pay payline = new E_quotation_pay();
	        
	        lineid= sqlinterface.getNextVal("SEQ_TPS_QUOTATION_PAY");
	        payline.setLineid(lineid);
			
	        payline.setActive(1);
	        payline.setQuotationId(head);
	        E_payment_method paymethod = new E_payment_method();
	        paymethod.setLineid(Integer.parseInt(pay.get("paymentmethodid")));
	        payline.setPaymentId(paymethod);
			payline.setScale(new BigDecimal(pay.get("scale")));
			payline.setCardno(pay.get("cardno"));
			payline.setCreateUser(userid);
			payline.setCreateTime(currdate);
			linepay.add(payline);
		}
			daopay.insertBatch(linepay);
		
		//更新支付方式完成
		
		//新增其它费用		
		//检查是否存在，存在则删除
		Dao<E_other_charge> daocharge=DaoFactory.create(E_other_charge.class);
		E_other_charge searchcharge = new E_other_charge();
		searchcharge.setQuotationId(head);
		List<E_other_charge> oldcharge =  daocharge.select(searchcharge);
		daocharge.deleteBatch(oldcharge);//删除以前的其它费用
				
		@SuppressWarnings("unchecked")
		List<Map<String,String>> chargedata= (List<Map<String,String>>) input.get("othercharge");//其它费用数据
		List<E_other_charge> linecharge = new ArrayList<E_other_charge>();
		for ( int i = 0;i<chargedata.size();i++)
		{
			Map<String,String> onecharge = chargedata.get(i);
			E_other_charge charge = new E_other_charge();
			
			
			lineid= sqlinterface.getNextVal("SEQ_TPS_QUOTATION_CHARGE");
			charge.setLineid(lineid);
			
	        
			charge.setQuotationId(head);//报价单主表
			
			E_charge_list chargelist = new E_charge_list();
			chargelist.setLineid(onecharge.get("chargeid"));
			charge.setChargeId(chargelist);//费用id
			
			charge.setChargeDesc(onecharge.get("chargedesc"));
			charge.setTotal(new BigDecimal(onecharge.get("chargetotal")));	
			
			charge.setActive(1);
			charge.setCreateUser(userid);
			charge.setCreateTime(currdate);
			
			linecharge.add(charge);
		}
			daocharge.insertBatch(linecharge);
		//更新支付方式完成
		
		return "";
	}
	public String generateNo()
	{//生成报价单编号
		String data = "";
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyyMMddHHmmss");
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		
		data = sdf.format(currdate);
		return data;
	}
	@Transactional
	public int deleteData()
	{//取消报价单
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		String userid = context.getUser().getId();
		Map<String, Object> input = context.getInput();
		String data=(String) input.get("postdata");//Lined列表,用,号分开
		String quotationtype=(String) input.get("quotationtype");//quotationtype报价单类型
		
		Map<String, Object> param=new HashMap<String, Object>();
		
	    param.put("activememo", "取消报价单");             
	    param.put("activeuser",userid);
	    
	    //多条选 中的数据 
	    String[] aLine = data.split(",");
	    param.put("listlineid",aLine);
	    param.put("quotationtype",quotationtype);
	    String now=timestamp2str(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss");
	    param.put("pApplyDate",now);
	    Dao<E_quotation_head> dao=DaoFactory.create(E_quotation_head.class);
	    
	    //检查是否审核通过，审核通过后，不可以删除
	    for ( int i =0;i<aLine.length;i++)
	    {
	    	String lineid = aLine[0];
	    	E_quotation_head one = new E_quotation_head();
	    	one.setLineid(lineid);
	    	E_quotation_head searchone = dao.selectOne(one);
	    	if ( searchone == null)
	    	{
	    		throw new RuntimeException("不存在相应的报价单，请刷新数据");
	    	}
	    	logger.info("已处理不存在报价单判断");
	    	if( searchone.getActive() == 0)
	    	{
	    		throw new RuntimeException("该报价单【"+searchone.getQuotationNo()+"】已经删除，不能重复删除");
	    	}
	    	logger.info("已处理重复删除报价单判断");
	    	if((searchone.getCheckFlag()!=null) && (searchone.getCheckFlag() == 2))
	    	{
	    		return 0;
	    		//throw new RuntimeException("该报价单【"+searchone.getQuotationNo()+"】已经审核完成，不能重复删除");
	    	}
	    	logger.info("已处理审核完成报价单判断");
	    }
		int count = dao.getSession().update("com.unlcn.ils.tps.E_quotation_headMapper.deleteBatchLogic", param);		
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		for (String string : aLine) {
			E_quotation_head e_quotation_head=dao.selectByID(string);
			e_quotation_head.setActiveTime(timestamp);
			dao.update(e_quotation_head);
		}
		return count;
	}
	
	@SuppressWarnings("unused")
	@Transactional
	public int submitData()
	{//提交审核
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		//String userid = context.getUser().getId();
		Map<String, Object> input = context.getInput();
		String data=(String) input.get("postdata");//Lined列表,用,号分开
		String quotationtype=(String) input.get("quotationtype");//quotationtype报价单类型
		
		Map<String, Object> param=new HashMap<String, Object>();
	    //多 条选 中的数据 
	    String[] aLine = data.split(",");
	    param.put("listlineid",aLine);
	    param.put("quotationtype",quotationtype);
	    String now=timestamp2str(new Timestamp(System.currentTimeMillis()),"yyyy-MM-dd HH:mm:ss");
	   // param.put("pApplyDate",now);
	    //更新提交审核标志
	    Dao<E_quotation_head> dao=DaoFactory.create(E_quotation_head.class);
		dao.getSession().update("com.unlcn.ils.tps.E_quotation_headMapper.submitApply", param);
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		for (String string : aLine) {
			E_quotation_head e_quotation_head=dao.selectByID(string);
			e_quotation_head.setApplyDate(timestamp);
			dao.update(e_quotation_head);
		}
		//更新报价单当前审核人
		for ( int i=0;i<aLine.length;i++)
		{
			String lineid = aLine[i];
			E_quotation_head quotation = dao.selectByID(lineid);
		
			E_quotation_head updatequotation =new E_quotation_head();
			updatequotation.setLineid(quotation.getLineid());
			//找到相应审核人			
			HashMap<String,Object> curruser = getAuditUser(quotation);				
			if ( curruser ==null )
			{	//没有下一个审批人,则审核通过
				updatequotation.setCheckFlag(2);
				updatequotation.setFlowid(999);
			}
			else 
			{
				updatequotation.setCheckFlag(1);//审批 中，则确定下一个
				Object flowid = curruser.get("flowid");
				updatequotation.setFlowid((Integer)flowid);//确定下一个节点序号
				updatequotation.setAuditUser((String)curruser.get("audit_user"));				
			}
			dao.update(updatequotation);
		}
		return 0;
	}
	public String getCurrentUser() {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		String userid = context.getUser().getName();
		return userid;
		
	}
	@Transactional
	public int checkData()
	{//报价单审核
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();		
		String userid = context.getUser().getName();
		logger.info("当前userid为"+userid);
		Map<String, Object> input = context.getInput();
		String data=(String) input.get("postdata");//Lined列表,用,号分开
		logger.info("获取到lineid列表为"+data);
		@SuppressWarnings("unchecked")
		Map<String, String> checkdata=(Map<String, String> ) input.get("data");//审核意见
		
		String quotationtype=(String) input.get("quotationtype");//报价单类型1：临时，2：合同
		logger.info("获取到报价单类型为"+quotationtype);
		Map<String, Object> param=new HashMap<String, Object>();
	    //多 条选 中的数据 
	    String[] aLine = data.split(",");
	    param.put("listlineid",aLine);
	    
	    Dao<E_quotation_head> dao=DaoFactory.create(E_quotation_head.class);
	    Dao<E_quotation_audit_record> daocheck=DaoFactory.create(E_quotation_audit_record.class);
	    //更新审核标志及审核用户,增加审核记录	    
		for ( int i=0;i<aLine.length;i++)
		{
			
			String checkstatus = checkdata.get("checkstatus");
			
			String lineid = aLine[i];
			logger.info("当前操作的lineid为"+lineid);
			E_quotation_head updatequotation =new E_quotation_head();
			updatequotation.setLineid(lineid);
			
			if (checkstatus.equals("1"))
			{//审核不通过
				
				updatequotation.setCheckFlag(3);//审核不通过打回
				updatequotation.setFlowid(0);//流程序号初始化
				updatequotation.setAuditUser("");//没有审核人
				updatequotation.setIsapply(0);//需要重新提交审核				
			}
			else
			{//审核通过
				E_quotation_head quotation = dao.selectByID(lineid);
				//找到相应审核人
				
				HashMap<String,Object> curruser = getAuditUser(quotation);				
				if ( curruser ==null )
				{	//没有下一个审批人,则审核完成
					updatequotation.setCheckFlag(2);//完成标志
					updatequotation.setFlowid(999);//序号设置比较大
				}
				else 
				{
					updatequotation.setCheckFlag(1);//审批 中，则确定下一个
					Object flowid = curruser.get("flowid");
					updatequotation.setFlowid((Integer)flowid);//确定下一个节点序号
					updatequotation.setAuditUser((String)curruser.get("audit_user"));
					
				}				
			}
			
			dao.update(updatequotation);//更新报价单审核状态
			
			//增加审核记录
			
			String checkmemo = checkdata.get("checkmemo");
			E_quotation_audit_record audit = new E_quotation_audit_record();
			SqlInterface interface1=new SqlInterface();
			audit.setLineid(interface1.getNextVal("SEQ_TPS_TPS_QUOTATION_AUDIT_RECORD"));
			audit.setQuotationId(updatequotation);
			audit.setActive(1);
			audit.setAuditUser(userid);
			audit.setAuditUser( context.getUser().getName());
			audit.setAuditFlag(Integer.parseInt(checkstatus));
			audit.setAuditMemo(checkmemo);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			audit.setAuditDatetime(currdate);
			daocheck.insert(audit);
		}
		return 0;
	}
	
	public HashMap<String,Object> getAuditUser(E_quotation_head quotation)
	{//找到相应的审核人
		BigDecimal overflowlv = quotation.getPriceOverflow();
		int flowid = (quotation.getFlowid()==null?0:quotation.getFlowid());
		int type = quotation.getQuotationType();
		if ( overflowlv== null ) overflowlv = new BigDecimal("0"); 
		Map<String, Object> dataparam=new HashMap<String, Object>();
		logger.info("传到getAuditUser的overflowlv为"+overflowlv);
		overflowlv = overflowlv.multiply(new BigDecimal(100));
		logger.info("溢价比入参为"+overflowlv);
		dataparam.put("price",overflowlv);          
		dataparam.put("flowid",flowid);		
		dataparam.put("type",type);
		Dao<E_quotation_audit_set> daoset=DaoFactory.create(E_quotation_audit_set.class);
		HashMap<String,Object> auditset = 
				daoset.getSession().selectOne("com.unlcn.ils.tps.E_quotation_audit_setMapper.selectsAuditSet",dataparam);
		String userid = "0";
		if (auditset!=null) {
			HashMap<String,Object> audit = (HashMap<String,Object>) auditset;
			logger.info("获取的audit_user"+audit.get("audit_user"));
			if (audit.get("audit_user")==null) {
				logger.info("获取的audit_user的账号为空的");
			}
			else {
				userid = (String) audit.get("audit_user");
				logger.info("获取的audit_user的账号为"+userid);
			}
		}
		if ( auditset !=null )
		{
			logger.info("auditset不为空");
		
		}
		logger.info("auditset为空");
		return auditset;
	}
	public List<Map<String, String>> getQutotaionLine()
	{//根据报价单序号得到报价明细，入参lineid
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		String lineid=(String) input.get("lineid");//报价单序号
		
		String flag = (String) input.get("flag");//来源
		
		Dao<E_quotation_line> dao=DaoFactory.create(E_quotation_line.class);
		E_quotation_line line = new E_quotation_line();
		E_quotation_head head = new E_quotation_head();
		head.setLineid(lineid);
		line.setQuotationId(head);
		List<E_quotation_line> lline = dao.select(line);
		
		List<Map<String, String>> listdata= new ArrayList<Map<String,String>>();
		
		CustomerService customerservice= new CustomerService (); 
		StyleService styleservice = new StyleService(); 
		for ( int i=0;i<lline.size();i++)
		{
			E_quotation_line oneline = lline.get(i);
			Map<String,String> map = new HashMap<String,String>();
			map.put("lineid", oneline.getLineid().toString());
			String customerid = oneline.getCustomerId();
			
			//查询客户
			Map<String,String> customer =  customerservice.getCustomerById(customerid);
			String customername = "";
			if ( customer!=null)
				customername = customer.get("name");			
			map.put("customername", customername);
			map.put("customerid", customerid);
			
			//查询车型
			String itemid = oneline.getItemId();
			Map<String,String> style =  styleservice.getStyleById(itemid);
			String itemname ="";
			if ( style != null )
				itemname = style.get("stylename");
			
			map.put("styleid",itemid);
			map.put("stylename",itemname);
			
			map.put("startcityid",oneline.getStartCityId().toString());
			map.put("startcityname",oneline.getStartCity());	
			map.put("destcityname",oneline.getDestCity());
			map.put("destcityid",oneline.getDestCityId().toString());
			
			map.put("qty", removeNullDec(oneline.getQty(),2));
			map.put("accountqty", removeNullDec(oneline.getAccountQty(),2));

			map.put("kilometer",removeNullDec(oneline.getStandardKilometer(),2));
			map.put("standardprice", removeNullDec(oneline.getStandardPrice(),3));
			map.put("qutotaionprice", removeNullDec(oneline.getQuotationPrice(),3));
			

			Double overflowlv =  Double.parseDouble(removeNullDec(oneline.getOverflow(),2))*100;
			
			Double overflow = Math.ceil(overflowlv);
			
			map.put("overflow",overflow.toString()+"%"	);
			
			if (flag.equals("check"))
			{//审核时可查看
				//查询收入,处理利润
				
				double incomeprice = 1200;//修改成接口查询
				
				BigDecimal arprice = new BigDecimal(incomeprice);
				arprice = arprice.multiply(oneline.getQty()).setScale(2);
				
				
				
				map.put("arprice",arprice.toString());
				BigDecimal margin = arprice.subtract(oneline.getQuotationPrice().multiply(oneline.getStandardKilometer().multiply(oneline.getQty())));
				
				map.put("margin", margin.setScale(2).toString());				
			}
			listdata.add(map);
		}	
		return listdata;
	}
	/**
	 * 
	 * @return
	 * @Description:根据报价单序号得到报价支付方式
	 */
	public List<Map<String, String>> getQutotaionPay()
	{
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		String lineid=(String) input.get("lineid");//报价单序号
		
		Dao<E_quotation_pay> dao=DaoFactory.create(E_quotation_pay.class);
		E_quotation_pay pay = new E_quotation_pay();
		E_quotation_head head = new E_quotation_head();
		head.setLineid((lineid));
		pay.setQuotationId(head);
		List<E_quotation_pay> lpay = dao.select(pay);
		
		List<Map<String, String>> listdata= new ArrayList<Map<String,String>>();
		
		for ( int i=0;i<lpay.size();i++)
		{
			E_quotation_pay onepay = lpay.get(i);
			Map<String,String> map = new HashMap<String,String>();
			map.put("lineid", onepay.getLineid().toString());
			
			map.put("paymentmethodid", onepay.getPaymentId().getLineid().toString());
			map.put("paymentmethod", onepay.getPaymentId().getPaymentName());
			map.put("scale", onepay.getScale().toString());
			map.put("cardno",onepay.getCardno());			
			listdata.add(map);
		}	
		return listdata;
	}
	/**
	 * 
	 * @return
	 * @Description:根据报价单序号得到报价其它费用
	 */
	public List<Map<String, String>> getQutotaionCharge()
	{
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		Map<String, Object> input = context.getInput();
		String lineid=(String) input.get("lineid");//报价单序号
		
		Dao<E_other_charge> dao=DaoFactory.create(E_other_charge.class);
		E_other_charge charge = new E_other_charge();
		E_quotation_head head = new E_quotation_head();
		head.setLineid((lineid));
		charge.setQuotationId(head);
		List<E_other_charge> lcharge = dao.select(charge);
		
		List<Map<String, String>> listdata= new ArrayList<Map<String,String>>();
		
		for ( int i=0;i<lcharge.size();i++)
		{
			E_other_charge onecharge = lcharge.get(i);
			Map<String,String> map = new HashMap<String,String>();
			map.put("lineid", onecharge.getLineid().toString());
			
			map.put("chargeid", onecharge.getChargeId().getLineid());
			map.put("chargename", onecharge.getChargeId().getChargeName());
			map.put("chargedesc", onecharge.getChargeDesc());
			map.put("chargetotal", onecharge.getTotal().toString());
			listdata.add(map);
		}	
		return listdata;
	}
	public boolean quotationAuditSetAdd (E_quotation_audit_set data) {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username=context.getUser().getName();
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		Dao<E_quotation_audit_set> quotaDao=DaoFactory.create(E_quotation_audit_set.class);
		E_quotation_audit_set quotation=new E_quotation_audit_set();
		quotation.setType(data.getType());
		quotation.setFlowid(data.getFlowid());
		quotation.setOverflowDown(data.getOverflowDown());
		quotation.setOverflowUp(data.getOverflowUp());
		quotation.setAuditUser(data.getAuditUser());
		quotation.setCreateTime(currdate);
		quotation.setCreateUser(username);
	//	quotation.setAssignuser(data.getAuditRule());
	//	quotation.setAuditRule(data.getAuditRule());
		quotation.setActive(1);//1为可用
	//	quotation.setActiveMemo(data.getActiveMemo());
	//	quotation.setActiveTime(currdate);
	//	quotation.setActiveUser(username);
		quotaDao.insert(quotation);
		return true;
	}
	public boolean quotationAuditSetEdit (String  idList,E_quotation_audit_set data) {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username=context.getUser().getName();
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		Dao<E_quotation_audit_set> quotaDao=DaoFactory.create(E_quotation_audit_set.class);
		E_quotation_audit_set quotation=new E_quotation_audit_set();
		quotation.setLineid(Integer.valueOf(idList));
		quotation.setType(data.getType());
		quotation.setFlowid(data.getFlowid());
		quotation.setOverflowDown(data.getOverflowDown());
		quotation.setOverflowUp(data.getOverflowUp());
		quotation.setAuditUser(data.getAuditUser());
		quotation.setCreateTime(currdate);
		quotation.setCreateUser(username);
	//	quotation.setAssignuser(data.getAuditRule());
	//	quotation.setAuditRule(data.getAuditRule());
	//	quotation.setActive(data.getActive());
	//	quotation.setActiveMemo(data.getActiveMemo());
	//	quotation.setActiveTime(currdate);
	//	quotation.setActiveUser(username);
		quotaDao.update(quotation);
		return true;
	}
	@SuppressWarnings("unused")
	public boolean quotationAuditSetDelete (List<String>  idList) {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username=context.getUser().getName();
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		Dao<E_quotation_audit_set> quotaDao=DaoFactory.create(E_quotation_audit_set.class);
		E_quotation_audit_set quotation=new E_quotation_audit_set();
		for(int i=0;i<idList.size();i++){
			/*quotation.setLineid(Integer.valueOf(idList.get(i)));
			quotation.setActive(0);//0为删除
		//	quotation.setActiveMemo(data.getActiveMemo());
			quotation.setActiveTime(currdate);
			quotation.setActiveUser(username);
			quotaDao.update(quotation);*/
			quotation.setLineid(Integer.valueOf(idList.get(i)));
			quotaDao.delete(quotation);
		}

		return true;
	}
	
	 public static String timestamp2str(Timestamp time, String pattern) {
		  if (time == null) {
		   throw new IllegalArgumentException("Timestamp is null");
		  }
		  if (pattern != null && !"".equals(pattern)) {
		   if (!"yyyyMMddHHmmss".equals(pattern)
		     && !"yyyy-MM-dd HH:mm:ss".equals(pattern)
		     && !"yyyy-MM-dd".equals(pattern)
		     && !"MM/dd/yyyy".equals(pattern)){
		    throw new IllegalArgumentException("Date format ["+pattern+"] is invalid");
		   }
		  }else{
		   //pattern = DEFAULT_PATTERN;
		  }
		  
		  Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		  cal.setTime(time);
		  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		  return sdf.format(cal.getTime());
		 }

}
