package com.unlcn.ils.tps.excel;

import java.math.BigDecimal; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_pay_period;  
import com.unlcn.ils.tps.E_quotation_head;
import com.unlcn.ils.tps.ininterface.ShipperInterface;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;

public class QuotationDataListModel {    
	@SuppressWarnings("unused")
	private static Logger logger=Logger.getLogger(Logger.class);
	@SuppressWarnings({ "unused", "static-access" })
	public List<Map<String, String>> getCheckDataList(Map<String, String> condition){
		Dao<E_quotation_head> dao=new DaoFactory().create(E_quotation_head.class);
		String custom_select="com.unlcn.ils.tps.E_checkheadMapper.custom_select";
		List<Map<String, String>> returnList=new ArrayList<Map<String, String>>();
		returnList.add(getTitleMap4CheckHead());
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (condition.get("shipperid")!=null) {
			String [] shippers=condition.get("shipperid").split(",");
			for (int i = 0; i < shippers.length; i++) {
				E_quotation_head condition_quotation_head=new E_quotation_head();
				condition_quotation_head.setShipperId(shippers[i]);
				if(condition.get("payperiod")!=null){
					E_pay_period e_pay_period=new E_pay_period();
					e_pay_period.setLineid(Integer.parseInt(condition.get("payperiod")));
					condition_quotation_head.setPayPeriod(e_pay_period);
				}
				if(condition.get("urgency")!=null){
					condition_quotation_head.setUrgent(Integer.parseInt(condition.get("urgency")));
				}
				if(condition.get("quotationno")!=null){
					condition_quotation_head.setQuotationNo(condition.get("quotationno"));
				}
				condition_quotation_head.setActive(1);
				List<E_quotation_head> results=dao.select(condition_quotation_head);
				for (E_quotation_head e_quotation_head : results) {
					Map<String,String> map = new HashMap<String,String>();
					map.put("lineid", e_quotation_head.getLineid().toString());
					map.put("shipperid", e_quotation_head.getShipperId().toString());
					
					Map<String,String> shipper = ShipperInterface.getShipperByID(e_quotation_head.getShipperId().toString());
					if ( shipper == null) continue;
					String shippername = shipper.get("name");
					
					map.put("shippername", shippername);
					map.put("quotationNo", e_quotation_head.getQuotationNo());
					map.put("payperiod", e_quotation_head.getPayPeriod().getLineid().toString());
					map.put("payperiodname",e_quotation_head.getPayPeriod().getPayPeriodName());
					
					String invoiceway ="开票"; 
					if ( e_quotation_head.getInvoiceway()==0)
						invoiceway ="不开票";
					map.put("invoiceway",invoiceway);
					
					map.put("audituser",(e_quotation_head.getAuditUser()==null?"":e_quotation_head.getAuditUser().toString()));
					
					
					map.put("urgent", e_quotation_head.getUrgent().toString());
					String begindate = sdf.format(e_quotation_head.getBeginDate());
					String enddate = sdf.format(e_quotation_head.getEndDate());
					map.put("begindate", begindate);
					map.put("enddate", enddate);
					
					map.put("quotationtype", e_quotation_head.getQuotationType().toString());
					
					map.put("standardpricetotal", removeNullDec(e_quotation_head.getStandardPriceTotal(),2));
					map.put("quotationpricetotal", removeNullDec(e_quotation_head.getQuotationPriceTotal(),2));
					
					Double overflowlv =  Double.parseDouble(removeNullDec(e_quotation_head.getPriceOverflow(),3))*100;
					
					Double overflow = Math.ceil(overflowlv);
					
					map.put("priceoverlfow",overflow.toString()+"%"	);
					map.put("overflowmemo", e_quotation_head.getOverflowMemo());
					
					map.put("checkflag", removeNull(e_quotation_head.getCheckFlag()));
					map.put("quotationflag", removeNull(e_quotation_head.getQuotationFlag()));
					
					map.put("createuser", e_quotation_head.getCreateUser().toString());
					String createtime = sdf.format(e_quotation_head.getCreateTime());
					map.put("createTime", createtime);
					map.put("transwayname", e_quotation_head.getTranswayName());
					map.put("estimate", removeNull(e_quotation_head.getEstimate()));
					
					map.put("isapply", removeNull(e_quotation_head.getIsapply()));
					
					String applydate = "";
					if (e_quotation_head.getApplyDate() !=null ) applydate = sdf2.format(e_quotation_head.getApplyDate());
					map.put("applydate", applydate);
					
					if ( e_quotation_head.getContractId() == null || e_quotation_head.getContractId() == 0 )
						map.put("contractstatus", "未生成");
					else map.put("contractstatus", "已生成");
					
					if ( e_quotation_head.getUrgent() == 0)
						map.put("urgent", "普通");
					else map.put("urgent", "紧急");
					returnList.add(map);
				}
				return returnList; 
			}
		}
		else{
			E_quotation_head condition_quotation_head=new E_quotation_head();
			if(condition.get("payperiod")!=null){
				E_pay_period e_pay_period=new E_pay_period();
				e_pay_period.setLineid(Integer.parseInt(condition.get("payperiod")));
				condition_quotation_head.setPayPeriod(e_pay_period);
			}
			if(condition.get("urgency")!=null){
				condition_quotation_head.setUrgent(Integer.parseInt(condition.get("urgency")));
			}
			if(condition.get("quotationno")!=null){
				condition_quotation_head.setQuotationNo(condition.get("quotationno"));
			}
			condition_quotation_head.setActive(1);
			List<E_quotation_head> results=dao.select(condition_quotation_head);
			for (E_quotation_head e_quotation_head : results) {
				Map<String,String> map = new HashMap<String,String>();
				map.put("lineid", e_quotation_head.getLineid().toString());
				map.put("shipperid", e_quotation_head.getShipperId().toString());
				
				Map<String,String> shipper = ShipperInterface.getShipperByID(e_quotation_head.getShipperId().toString());
				if ( shipper == null) continue;
				String shippername = shipper.get("name");
				
				map.put("shippername", shippername);
				map.put("quotationNo", e_quotation_head.getQuotationNo());
				map.put("payperiod", e_quotation_head.getPayPeriod().getLineid().toString());
				map.put("payperiodname",e_quotation_head.getPayPeriod().getPayPeriodName());
				
				String invoiceway ="开票"; 
				if ( e_quotation_head.getInvoiceway()==0)
					invoiceway ="不开票";
				map.put("invoiceway",invoiceway);
				
				map.put("audituser",(e_quotation_head.getAuditUser()==null?"":e_quotation_head.getAuditUser().toString()));
				
				
				map.put("urgent", e_quotation_head.getUrgent().toString());
				String begindate = sdf.format(e_quotation_head.getBeginDate());
				String enddate = sdf.format(e_quotation_head.getEndDate());
				map.put("begindate", begindate);
				map.put("enddate", enddate);
				
				map.put("quotationtype", e_quotation_head.getQuotationType().toString());
				
				map.put("standardpricetotal", removeNullDec(e_quotation_head.getStandardPriceTotal(),2));
				map.put("quotationpricetotal", removeNullDec(e_quotation_head.getQuotationPriceTotal(),2));
				
				Double overflowlv =  Double.parseDouble(removeNullDec(e_quotation_head.getPriceOverflow(),3))*100;
				
				Double overflow = Math.ceil(overflowlv);
				
				map.put("priceoverlfow",overflow.toString()+"%"	);
				map.put("overflowmemo", e_quotation_head.getOverflowMemo());
				
				map.put("checkflag", removeNull(e_quotation_head.getCheckFlag()));
				map.put("quotationflag", removeNull(e_quotation_head.getQuotationFlag()));
				
				map.put("createuser", e_quotation_head.getCreateUser().toString());
				String createtime = sdf.format(e_quotation_head.getCreateTime());
				map.put("createTime", createtime);
				map.put("transwayname", e_quotation_head.getTranswayName());
				map.put("estimate", removeNull(e_quotation_head.getEstimate()));
				
				map.put("isapply", removeNull(e_quotation_head.getIsapply()));
				
				String applydate = "";
				if (e_quotation_head.getApplyDate() !=null ) applydate = sdf2.format(e_quotation_head.getApplyDate());
				map.put("applydate", applydate);
				
				if ( e_quotation_head.getContractId() == null || e_quotation_head.getContractId() == 0 )
					map.put("contractstatus", "未生成");
				else map.put("contractstatus", "已生成");
				
				if ( e_quotation_head.getUrgent() == 0)
					map.put("urgent", "普通");
				else map.put("urgent", "紧急");
				returnList.add(map);
			}
			return returnList; 
		}
		return returnList; 
	}
	
	private Map<String, String> getTitleMap4CheckHead(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("lineid", "序号");
		map.put("shipperid", "分供方编号");
		map.put("shippername", "分供方名称");
		map.put("quotationNo", "报价单编号");
		map.put("payperiod", "支付帐期");
		map.put("payperiodname", "支付帐期");
		map.put("invoiceway", "开票方式");
		map.put("audituser", "审核人");
		map.put("urgent", "紧急程度");
		map.put("begindate", "有效日期");
		map.put("enddate", "失效日期");
		map.put("quotationtype", "报价单类型");
		map.put("standardpricetotal", "标准总价");
		map.put("quotationpricetotal", "");
		map.put("priceoverlfow", "溢价比例");
		map.put("overflowmemo", "溢价说明");
		map.put("checkflag", "审核状态");
		map.put("quotationflag", "报价方式(按总价/按单台)");
		map.put("createuser", "创建人");
		map.put("createTime", "生成日期");
		map.put("transwayname", "运输方式名称");
		map.put("estimate", "estimate");
		map.put("isapply", "审核状态");
		map.put("applydate", "applydate");
		map.put("contractstatus", "合同状态");
		map.put("urgent", "紧急程度");
		return map;
	}
	
	public String removeNullDec(java.math.BigDecimal data,int flag)
	{
		if (data == null) return "0";
		else 
		{
			return data.setScale(flag,BigDecimal.ROUND_HALF_UP).toString();
		}
	}
	public String removeNull(Integer data)
	{
		if (data == null) return "0";
		else return data.toString();
	}
}
