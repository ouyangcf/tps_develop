package com.unlcn.ils.tps.services;

import java.math.BigDecimal; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_attached;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_deduct;
import com.unlcn.ils.tps.E_frozen_detail;
import com.unlcn.ils.tps.E_frozenhead;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.E_substract;
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
 *@Description:考核单表头服务
 *@Author:Administrator
 *@Since:2016-8-18 
 *@Version:1.1.0
 */
public class CheckHeadService implements ArrayContentProvider{
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:创建考核单表头
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public boolean createCheckHead(List<String> idList){
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdocument> checkdocmentDao=new DaoFactory().create(E_checkdocument.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_frozenhead> frozenheadDao=new DaoFactory().create(E_frozenhead.class);
		Dao<E_frozen_detail> frozendetailDao=new DaoFactory().create(E_frozen_detail.class);
		List<E_checkdocument> checkdocuments=new ArrayList<E_checkdocument>();
		String getMaxIdFullPath="com.unlcn.ils.tps.E_checkheadMapper.getMaxId";
		//获取所有需要产生考核单表头的数据
		for(int i=0;i<idList.size();i++){
			checkdocuments.add(checkdocmentDao.selectByID(idList.get(i)));
		}
		//先生成表头，再生成明细表
		List<E_checkdocument> tempCheckdocuments=new ArrayList<E_checkdocument>();
		Calendar cal = Calendar.getInstance();
		// 当前年  
	    int year = cal.get(Calendar.YEAR);  
	    // 当前月  
	    int month = (cal.get(Calendar.MONTH)) + 1; 
	    String checkTime=year+"-"+month;
	    Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
	    cal.set(year, (cal.get(Calendar.MONTH)), (cal.get(Calendar.DATE)) + 3);
	    
	    Timestamp argueTime=new Timestamp(cal.getTimeInMillis());
		while(checkdocuments.size()!=0){
			//先搜集同一个分供方的记录表于临时容器中
			String shipperLineid=checkdocuments.get(0).getShipperLineid().toString();
			for(int i=0;i<checkdocuments.size();i++){
				if(shipperLineid.equals(checkdocuments.get(i).getShipperLineid()))
				{
					tempCheckdocuments.add(checkdocuments.get(i));
					checkdocuments.remove(i);
					i=i-1;
				}		
			}
			E_checkhead e_checkhead=new E_checkhead();
			e_checkhead.setShipperLineid(shipperLineid);
			e_checkhead.setShipperName(tempCheckdocuments.get(0).getShipperName());
			e_checkhead.setCountmonth(checkTime);
			Integer StandardSubvalue=0;
			Integer StandardValue=0;
			BigDecimal StandardSubmoney=new BigDecimal(0);
			BigDecimal CheckMoney=new BigDecimal(0);
			for(int i=0;i<tempCheckdocuments.size();i++){			
				
				//总扣分
				StandardSubvalue=tempCheckdocuments.get(i).getCheckScore()+StandardSubvalue;
				//总标准扣分
				StandardValue=tempCheckdocuments.get(i).getCheckScoreStandard()+StandardValue;
				//总标准考核金额
				StandardSubmoney=tempCheckdocuments.get(i).getCheckMoneyStandard().add(StandardSubmoney);
				//总考核金额
				CheckMoney=tempCheckdocuments.get(i).getCheckMoney().add(CheckMoney);
			}
			

			//本月扣分
			e_checkhead.setStandardSubvalue(StandardSubvalue);
			//标准扣分
			e_checkhead.setStandardValue(StandardValue);	
			e_checkhead.setStandardSubmoney(StandardSubmoney);
			e_checkhead.setCheckMoney(CheckMoney);
			e_checkhead.setArgueTime(argueTime);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_checkhead.setCreateUsername(username);
			e_checkhead.setCreateTime(timestampnow);
			e_checkhead.setCollectStatus(0);
			e_checkhead.setCheckStatus(0);
			e_checkhead.setArgueStatus(0);
			SqlInterface sqlInterface=new SqlInterface();
			String checkHeadLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_CHECKHEAD");
			e_checkhead.setLineid(checkHeadLineid);
			checkheadDao.insert(e_checkhead);
			//生成冻结单表头
			E_frozenhead e_frozenhead=new E_frozenhead();
			e_frozenhead.setCheckheadLineid(checkHeadLineid);
			e_frozenhead.setFrozenvalue(CheckMoney);
			e_frozenhead.setStartDate(timestampnow);
			e_frozenhead.setEndDate(argueTime);
			e_frozenhead.setCreateUsername("xxx");
			e_frozenhead.setCreateTime(timestampnow);
			e_frozenhead.setFrozenStatus(0);
			String frozenheadMaxid=sqlInterface.getNextVal("SEQ_TPS_TPS_FROZEN_HEAD");
			e_frozenhead.setLineid(frozenheadMaxid);
			frozenheadDao.insert(e_frozenhead);
			//String frozenheadMaxid=maxIdFrozenheads.get(0).getLineid();
			//创建临时表中的明细表以及冻结单明细表
			for(int i=0;i<tempCheckdocuments.size();i++){
				E_checkdetail e_checkdetail=new E_checkdetail();
				e_checkdetail.setCheckheadLineid(checkHeadLineid);
				e_checkdetail.setCheckdocLineid(tempCheckdocuments.get(i).getLineid());
				e_checkdetail.setItemLineid(tempCheckdocuments.get(i).getItemLineid());
				e_checkdetail.setItemName(tempCheckdocuments.get(i).getItemName());
				e_checkdetail.setOperateLineid(tempCheckdocuments.get(i).getOperateLineid());
				e_checkdetail.setOperateName(tempCheckdocuments.get(i).getOperateName());
				e_checkdetail.setCheckNumber(tempCheckdocuments.get(i).getCheckNumber());
				e_checkdetail.setCheckMoney(tempCheckdocuments.get(i).getCheckMoney());
				e_checkdetail.setCheckMemo(tempCheckdocuments.get(i).getCheckMemo());
				e_checkdetail.setCreateTime(timestampnow);
				e_checkdetail.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_CHECK_DETAIL"));
				e_checkdetail.setCreateUsername(username);
				e_checkdetail.setArgueStatus(0);
				checkdetailDao.insert(e_checkdetail);
				//更新记录单的状态
				//更新checkdoc状态
				tempCheckdocuments.get(i).setFlag(1);
				checkdocmentDao.update(tempCheckdocuments.get(i));
				//创建冻结单明细
				E_frozen_detail e_frozen_detail=new E_frozen_detail();
				e_frozen_detail.setFrozenLineid(frozenheadMaxid);
				e_frozen_detail.setFrozenStatus(0);
				e_frozen_detail.setCheckdocLineid(tempCheckdocuments.get(i).getLineid());
				e_frozen_detail.setItemLineid(tempCheckdocuments.get(i).getItemLineid());
				e_frozen_detail.setItemName(tempCheckdocuments.get(i).getItemName());
				e_frozen_detail.setOperateLineid2(tempCheckdocuments.get(i).getOperateLineid());
				e_frozen_detail.setOperateName(tempCheckdocuments.get(i).getOperateName());
				e_frozen_detail.setCheckNumber(tempCheckdocuments.get(i).getCheckNumber());
				e_frozen_detail.setCheckMoney(tempCheckdocuments.get(i).getCheckMoney());
				e_frozen_detail.setCheckMemo(tempCheckdocuments.get(i).getCheckMemo());
				e_frozen_detail.setCreateUsername(username );
				e_frozen_detail.setCreateTime(timestampnow);
				e_frozen_detail.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_FROZEN_DETAIL"));
				frozendetailDao.insert(e_frozen_detail);
			}
			//更新附件表
			String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
			String selectByCheckDocLineidFullPath1="com.unlcn.ils.tps.E_attachedMapper.selectByCheckDocLineid";
			Dao<E_attached> attachedDao=new DaoFactory().create(E_attached.class);
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, checkHeadLineid);
			for(int i=0;i<checkdetails.size();i++){
				E_checkdocument e_checkdocument=checkdocmentDao.selectByID(checkdetails.get(i).getCheckdocLineid());
				List<E_attached> attacheds=attachedDao.getSession().selectList(selectByCheckDocLineidFullPath1, e_checkdocument.getLineid());
				for(int j=0;j<attacheds.size();j++){
					E_attached e_attached=attacheds.get(j);
					e_attached.setCheckheadLineid(checkHeadLineid);
					attachedDao.update(e_attached);
				}
			}
			//清空临时表
			tempCheckdocuments.clear();
		}
		return true;
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:考核单汇总
	 */
	@SuppressWarnings("static-access")
	public boolean collect(List<String> idList){
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_substract> substractDao=new DaoFactory().create(E_substract.class);
		Dao<E_deduct> deductDao=new DaoFactory().create(E_deduct.class);
		Dao<E_checkhead> checkHeadDao=new DaoFactory().create(E_checkhead.class);
		String getFromSubstract="com.unlcn.ils.tps.E_substractMapper.selectByCheckHeadLineid";
		String getFromDeduct="com.unlcn.ils.tps.E_deductMapper.selectByCheckHeadLineid";
		String getFromCheckHeadLineid="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		for(int i=0;i<idList.size();i++){
			//减免金额汇总等于所有减免单的金额之和
			//扣款金额汇总等于所有冻结金额汇总减去减免金额汇总，同时生成扣款单
			String checkheadlineid=idList.get(i);
			List<E_substract> substracts=substractDao.getSession().selectList(getFromSubstract, checkheadlineid);
			BigDecimal Sub_Money=new BigDecimal(0);
			for(int j=0;j<substracts.size();j++){
				//汇总减免金额
				Sub_Money=substracts.get(j).getSubMoney().add(Sub_Money);
			}
			E_checkhead e_checkhead=checkHeadDao.selectByID(checkheadlineid);
			e_checkhead.setSubMoney(Sub_Money);
			BigDecimal Deduct_Money=new BigDecimal(0);
			BigDecimal Frozen_Money=new BigDecimal(0);
			//汇总冻结金额
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(getFromCheckHeadLineid, checkheadlineid);
			for(int j=0;j<checkdetails.size();j++){
				Frozen_Money=Frozen_Money.add(checkdetails.get(j).getCheckMoney());
			}
			//冻结金额汇总
			e_checkhead.setFronzenMoney(Frozen_Money);
			Deduct_Money=e_checkhead.getFronzenMoney().subtract(Sub_Money);
			//扣款汇总=汇总冻结-汇总减免
			e_checkhead.setFactSubmoney(Deduct_Money);
			e_checkhead.setDeductMoney(Deduct_Money);
			//检查是否之前存在过扣款单，有就更新，没有就创建
			List<E_deduct> deducts=deductDao.getSession().selectList(getFromDeduct, e_checkhead.getLineid());
			if(deducts.size()==0){
				E_deduct e_deduct=new E_deduct();
				e_deduct.setCheckheadLineid(checkheadlineid);
				e_deduct.setDeductMoney(Deduct_Money);
				e_deduct.setDeductReson("系统自动生成");
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				e_deduct.setCreateTime(timestampnow);
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();
				e_deduct.setCreateUser(username );
				SqlInterface sqlInterface=new SqlInterface();
				e_deduct.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_DEDUCT"));
				deductDao.insert(e_deduct);
			}
			else{
				E_deduct e_deduct=deducts.get(0);
				e_deduct.setDeductMoney(Deduct_Money);
				deductDao.update(e_deduct);
			}
			e_checkhead.setCollectStatus(1);
			checkHeadDao.update(e_checkhead);
		}
		return true;
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:考核单表头提交
	 */
	 @SuppressWarnings("static-access")
	public boolean submitCheck(List<String> idList){
		 Dao<E_checkhead> checkHeadDao=new DaoFactory().create(E_checkhead.class);
		 for(int i=0;i<idList.size();i++){
			 E_checkhead e_checkhead=checkHeadDao.selectByID(idList.get(i));
			 e_checkhead.setCheckStatus(1);
			 checkHeadDao.update(e_checkhead);
		 }
		 return false;
	 }
	 /**
	  * 
	  * @param idList
	  * @param data
	  * @return
	  * @Description:考核单审批
	  */
	 @SuppressWarnings("static-access")
	public boolean setCheck(List<String> idList,E_checkhead data){
		 String getFromFrozen="com.unlcn.ils.tps.E_frozenheadMapper.selectByCheckHeadLineid";
		 String getFromFrozenDetail="com.unlcn.ils.tps.E_frozen_detailMapper.selectByFrozenlineid";
		 String getFromCheckDetail="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckDocLineid";
		 String getFromArgue="com.unlcn.ils.tps.E_argueMapper.selectByCheckDetailLineid";
		 String getFromRevise="com.unlcn.ils.tps.E_reviseMapper.selectByCheckDetailLineid";
		 String getFromSubstractByReviseId="com.unlcn.ils.tps.E_substractMapper.selectByReviseLineid";
		 String getFromSubstractByArgueId="com.unlcn.ils.tps.E_substractMapper.selectByArgueLineid";
		 
		 Dao<E_frozenhead> frozenheadDao=new DaoFactory().create(E_frozenhead.class);
		 Dao<E_substract> substractDao=new DaoFactory().create(E_substract.class);
		 Dao<E_checkhead> checkHeadDao=new DaoFactory().create(E_checkhead.class);
		 Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		 Dao<E_frozen_detail> frozen_detailDao=new DaoFactory().create(E_frozen_detail.class);
		 Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		 Dao<E_revise> reviseDao=new DaoFactory().create(E_revise.class);
		 for(int i=0;i<idList.size();i++){
			 E_checkhead e_checkhead=checkHeadDao.selectByID(idList.get(i));
			 e_checkhead.setCheckStatus(data.getCheckStatus());
			 
			 checkHeadDao.update(e_checkhead);
			 if(data.getCheckStatus()==2){
				 List<E_frozenhead> frozenheads=frozenheadDao.getSession().selectList(getFromFrozen, idList.get(i)); 
				 for(int j=0;j<frozenheads.size();j++){
					 E_frozenhead e_frozenhead=frozenheads.get(j);
					 e_frozenhead.setCancelfrozenvalue(e_checkhead.getSubMoney());
					 e_frozenhead.setFrozenStatus(1);
					 frozenheadDao.update(e_frozenhead);
					 //获取该冻结单下所有的冻结单明细
					 List<E_frozen_detail> frozendetails=frozen_detailDao.getSession().selectList(getFromFrozenDetail, e_frozenhead.getLineid());
					 for(int k=0;k<frozendetails.size();k++){
						 List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(getFromCheckDetail, frozendetails.get(k).getCheckdocLineid());
						 E_checkdetail e_checkdetail=checkdetails.get(0);
						 //找到明细以后找它所有的申诉单
						 List<E_argue>  e_argue_as=argueDao.getSession().selectList(getFromArgue, e_checkdetail.getLineid());
						 for(int l=0;l<e_argue_as.size();l++){
							 if(e_argue_as.get(l).getArgueStatus()==2){
								 List<E_substract> tempE_substracts=substractDao.getSession().selectList(getFromSubstractByArgueId, e_argue_as.get(l).getLineid());
								 E_frozen_detail temp=frozendetails.get(k);
								 if(tempE_substracts.size()!=0){
								 temp.setCancelfrozenvalue(tempE_substracts.get(0).getSubMoney());
								 temp.setFrozenStatus(1);
								 frozen_detailDao.update(temp);									 
								 }
							 }
						 }
						 //找到明细以后找它所有的整改单
						 List<E_revise>  e_revises=reviseDao.getSession().selectList(getFromRevise, e_checkdetail.getLineid());
						 for(int l=0;l<e_revises.size();l++){
							 if(e_revises.get(l).getReviseStatus()==3){
								 List<E_substract> tempE_substracts=substractDao.getSession().selectList(getFromSubstractByReviseId, e_revises.get(l).getLineid());
								 E_frozen_detail temp=frozendetails.get(k);
								 if(tempE_substracts.size()!=0){
									 temp.setCancelfrozenvalue(tempE_substracts.get(0).getSubMoney());
									 temp.setFrozenStatus(1);
									 frozen_detailDao.update(temp);								  
								 }

							 }
						 }						 
						 
					 }
				 }
			 }
		 }
		 return true;
	 }

	@Override
	public Page<E_checkhead> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_checkhead> dao = DaoFactory.create(E_checkhead.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_checkhead> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("static-access")
	public boolean cancelCollect(String id){
		Dao<E_checkhead> dao=new DaoFactory().create(E_checkhead.class);
		E_checkhead checkhead=dao.selectByID(id);
		checkhead.setCollectStatus(0);
		dao.update(checkhead);
		return false;
	}
}
