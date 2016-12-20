package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_deduct;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.E_substract;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 *  
 *@Title:
 *@Description:考核单明细服务 
 *@Author:Administrator  
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class CheckDetailService implements ArrayContentProvider{
	private static Logger logger=Logger.getLogger(Logger.class);
	/**
	 * 
	 * @param idList
	 * @Description:更新考核状态
	 */
	@SuppressWarnings("static-access")
	public void updateArgueStatus(List<Integer> idList){
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		
		for(int i=0;i<idList.size();i++){
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, idList.get(i));
			for(int j=0;j<checkdetails.size();j++){
				E_checkdetail e_checkdetail=checkdetails.get(j);
				if(e_checkdetail.getArgueStatus()==0)
				e_checkdetail.setArgueStatus(2);
				checkdetailDao.update(e_checkdetail);
			}
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_deduct> deductDao = DaoFactory.create(E_deduct.class) ;
		Dao<E_substract> sbustractDao = DaoFactory.create(E_substract.class) ;
		Dao<E_revise> dao = DaoFactory.create(E_revise.class) ;
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		//获取所有的明细表
		Page<E_checkdetail> result=checkdetailDao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);	
		List<E_checkdetail> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		//获取明细表下的申诉单
		String getArgueFromDetail="com.unlcn.ils.tps.E_argueMapper.selectByCheckDetailLineid";
		//获取申诉成功时的减免单
		String getSubstractFromArgue="com.unlcn.ils.tps.E_substractMapper.selectByArgueLineid";
		//获取申诉不成功时的整改单
		String getReviseFromArgue="com.unlcn.ils.tps.E_reviseMapper.selectByCheckDetailLineid";
		//获取整改成功时的减免单
		String getSubstractFromRevise="com.unlcn.ils.tps.E_substractMapper.selectByReviseLineid";
		//获取整改不成功时的扣款单
		String getDeductFromRevise="com.unlcn.ils.tps.E_deductMapper.selectByReviseLineid";
		//检查是不是过期
		@SuppressWarnings("unused")
		boolean flag=false;
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		List<E_checkdetail> checklists=new ArrayList<E_checkdetail>();
		
		for(int i=0;i<lists.size();i++){
			E_checkdetail tempCheckdetail=lists.get(i);
			E_checkhead e_checkhead=checkheadDao.selectByID(lists.get(i).getCheckheadLineid());
			if(e_checkhead.getArgueTime().before(timestampnow)){
				if (tempCheckdetail.getArgueStatus()==0) {
					tempCheckdetail.setArgueStatus(2);
					checkdetailDao.update(tempCheckdetail);					
				}

			}
			checklists.add(tempCheckdetail);
		}
		for(int i=0;i<checklists.size();i++){
			E_checkhead e_checkhead=checkheadDao.selectByID(checklists.get(i).getCheckheadLineid());
			Map<String, String> map=new HashMap<String, String>();
			map.put("lineid", checklists.get(i).getLineid());
			map.put("checkHeadLineid", e_checkhead.getLineid());
			map.put("countMonth", e_checkhead.getCountmonth());
			map.put("shipperName", e_checkhead.getShipperName());
			map.put("itemName", checklists.get(i).getItemName());
			map.put("checkMoney", checklists.get(i).getCheckMoney().toString());
			map.put("operateName", checklists.get(i).getOperateName());
			map.put("checkMemo", checklists.get(i).getCheckMemo());
			map.put("createTime", checklists.get(i).getCreateTime().toString());
			map.put("checkDocLineid", checklists.get(i).getCheckdocLineid().toString());
			map.put("argueStatus1", checklists.get(i).getArgueStatus().toString());
			if(e_checkhead.getFactSubmoney()!=null)
			map.put("factSubMoney", e_checkhead.getFactSubmoney().toString());
			String checkDetailid=checklists.get(0).getLineid();
			//获取明细表下的申诉单
			List<E_argue> e_argue_as=argueDao.getSession().selectList(getArgueFromDetail, checkDetailid);
			List<E_substract> e_substracts=new ArrayList<E_substract>();
			List<E_revise> e_revises=new ArrayList<E_revise>();
			List<E_substract> e_substracts1=new ArrayList<E_substract>();
			List<E_deduct> e_deducts=new ArrayList<E_deduct>();
			for(int j=0;j<e_argue_as.size();j++){
				//获取申诉成功时的减免单
				if(e_argue_as.get(j).getArgueStatus()==2){
					List<E_substract> temps=sbustractDao.getSession().selectList(getSubstractFromArgue, e_argue_as.get(j).getLineid());
					for(int k=0;k<temps.size();k++){
						e_substracts.add(temps.get(k));
					}
				}
				//获取申诉不成功时的整改单
				if(e_argue_as.get(j).getArgueStatus()==3){
					List<E_revise> temps=dao.getSession().selectList(getReviseFromArgue, checkDetailid);
					for(int k=0;k<temps.size();k++){
						e_revises.add(temps.get(k));
					}
				}
			}
			for(int l=0;l<e_revises.size();l++){
				//获取整改成功时的减免单
				if(e_revises.get(l).getReviseStatus()==3){
					List<E_substract> temps=sbustractDao.getSession().selectList(getSubstractFromRevise, e_revises.get(l).getLineid());
					for(int k=0;k<temps.size();k++){
						e_substracts1.add(temps.get(k));
					}					
				}
				//获取整改不成功时的扣款单
				if(e_revises.get(l).getReviseStatus()==4){
					List<E_deduct> temps=deductDao.getSession().selectList(getDeductFromRevise, e_revises.get(l).getLineid());
					for(int k=0;k<temps.size();k++){
						e_deducts.add(temps.get(k));
					}					
				}
			}

		if(e_argue_as.size()!=0){
			map.put("argueId", e_argue_as.get(0).getLineid());
			map.put("argueReason", e_argue_as.get(0).getArgueReson());
			map.put("argueStatus", e_argue_as.get(0).getArgueStatus().toString());
		}
		String getMaxId="com.unlcn.ils.tps.E_argueMapper.getMaxIdDesc";
		logger.info("当前明细表主键为："+checklists.get(i).getLineid());
		List<E_argue> max=argueDao.getSession().selectList(getMaxId,checklists.get(i).getLineid());
		if(max.size()==0){
			map.put("real_argue_status", "0");
		}
		else{
			map.put("real_argue_status", max.get(0).getArgueStatus().toString());
			map.put("argueStatus", "0");
		}
		map.put("argueTime", e_checkhead.getArgueTime().toString());
		if(e_substracts.size()!=0){
			map.put("substractId", e_substracts.get(0).getLineid());
			map.put("substractMoney", e_substracts.get(0).getSubMoney().toString());
			map.put("substractReason", e_substracts.get(0).getSubReson());
			map.put("substractCreateTime", e_substracts.get(0).getCreateTime().toString());
		}
		if(e_substracts1.size()!=0){
			map.put("substractId", e_substracts1.get(0).getLineid());
			map.put("substractMoney", e_substracts1.get(0).getSubMoney().toString());
			map.put("substractReason", e_substracts1.get(0).getSubReson());
			map.put("substractCreateTime", e_substracts1.get(0).getCreateTime().toString());
		}
		if(e_revises.size()!=0){
			map.put("reviseId", e_revises.get(0).getLineid());
			map.put("ReviseContent", e_revises.get(0).getReviseContent());
			map.put("DeadDate", e_revises.get(0).getDeadDate().toString());
			map.put("ReviseDescription",e_revises.get(0).getReviseDescription());
			map.put("Reson",e_revises.get(0).getReson());
			map.put("Tempcontent",e_revises.get(0).getTempcontent());
			map.put("Longcontent",e_revises.get(0).getLongcontent());
			map.put("Precontent",e_revises.get(0).getPrecontent());
			map.put("Verifyconten",e_revises.get(0).getVerifycontent());
			map.put("Contact",e_revises.get(0).getContact());
			map.put("ExecuteUsername",e_revises.get(0).getExecuteUsername());
		}
		if(e_deducts.size()!=0){
			map.put("deductId", e_deducts.get(0).getLineid());
			map.put("DeductMoney", e_deducts.get(0).getDeductMoney().toString());
			map.put("getDeductReson", e_deducts.get(0).getDeductReson());
			map.put("DeductCreateTime", e_deducts.get(0).getCreateTime().toString());
		}
		updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;	
	}
	/**
	 * 
	 * @param ShipperId
	 * @return
	 * @Description:根据分供方编号获取考核单明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByShipper(String ShipperId){
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);		
		String selectByShipperLineidFullPath="com.unlcn.ils.tps.E_checkheadMapper.selectByShipperLineid";
		String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		List<String> idList=new ArrayList<String>();
		List<E_checkhead> checkheads=checkheadDao.getSession().selectList(selectByShipperLineidFullPath, ShipperId);
		for(int i=0;i<checkheads.size();i++){
			E_checkhead tempCheckhead=checkheads.get(i);
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, tempCheckhead.getLineid());
			for(int j=0;j<checkdetails.size();j++){
				idList.add(checkdetails.get(j).getLineid());
			}
		}
		return idList;
	}
	/**
	 * 
	 * @param ItemLineid
	 * @return
	 * @Description:根据考核项目主键获取考核单明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByItemLineid(Integer ItemLineid){
		String selectByItemLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByItemLineid";
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByItemLineidFullPath, ItemLineid);
		List<String> idList=new ArrayList<String>();
		for(int i=0;i<checkdetails.size();i++){
			String tempId=checkdetails.get(i).getLineid();
			idList.add(tempId);
		}
		return idList;
	}
	/**
	 * 
	 * @param OperateLineid
	 * @return
	 * @Description:根据考核类型主键获取考核单明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByOperateLineid(Integer OperateLineid){
		String selectByOperateLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByOperateLineid";
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByOperateLineidFullPath, OperateLineid);
		List<String> idList=new ArrayList<String>();
		for(int i=0;i<checkdetails.size();i++){
			String tempId=checkdetails.get(i).getLineid();
			idList.add(tempId);
		}
		return idList;
	}
	/**
	 * 
	 * @param ItemLineid
	 * @param ShipperId
	 * @return
	 * @Description:根据考核项目主键，分供方主键获取考核单明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByItemLineidWithShipperId(Integer ItemLineid,String ShipperId){
		String selectByItemLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByItemLineid";
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByItemLineidFullPath, ItemLineid);
		List<String> idList=new ArrayList<String>();
		for(int i=0;i<checkdetails.size();i++){
			@SuppressWarnings("unused")
			String tempId=checkdetails.get(i).getLineid();
			//下面进行筛选,根据考核单表头来中是不是等于该分供方编号
			E_checkhead tempCheckhead=checkheadDao.selectByID(checkdetails.get(i).getCheckheadLineid());
			if(tempCheckhead!=null&&(!tempCheckhead.getShipperLineid().equals(ShipperId))){
				checkdetails.remove(i);
				i=i-1;
			}
		}
		for(int i=0;i<checkdetails.size();i++){
			String tempId=checkdetails.get(i).getLineid();
			idList.add(tempId);
		}		
		return idList;
	}
	/**
	 * 
	 * @param OperateLineid
	 * @param ShipperId
	 * @return
	 * @Description:根据考核类型主键，分供方主键获取考核单明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByOperateLineidWithShipperId(Integer OperateLineid,String ShipperId){
		String selectByOperateLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByOperateLineid";
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByOperateLineidFullPath, OperateLineid);
		List<String> idList=new ArrayList<String>();
		for(int i=0;i<checkdetails.size();i++){
			@SuppressWarnings("unused")
			String tempId=checkdetails.get(i).getLineid();
			//下面进行筛选,根据考核单表头来中是不是等于该分供方编号
			E_checkhead tempCheckhead=checkheadDao.selectByID(checkdetails.get(i).getCheckheadLineid());
			if(tempCheckhead!=null&&(!tempCheckhead.getShipperLineid().equals(ShipperId))){
				checkdetails.remove(i);
				i=i-1;
			}
		}
		for(int i=0;i<checkdetails.size();i++){
			String tempId=checkdetails.get(i).getLineid();
			idList.add(tempId);
		}	
		return idList;
	}
	/**
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 * @Description:根据时间段获取考核明细主键
	 */
	@SuppressWarnings("static-access")
	public List<String> getByTime(String startTime,String endTime){
		String selectByTimeullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByTime";
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		E_checkdetail e_checkdetail=new E_checkdetail();
		Timestamp startTimes=new Timestamp(Long.parseLong(startTime));
		Timestamp endTimes=new Timestamp(Long.parseLong(endTime));
		e_checkdetail.setCreateTime(startTimes);
		e_checkdetail.setActiveTime(endTimes);
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByTimeullPath, e_checkdetail);
		List<String> idList=new ArrayList<String>();
		for(int i=0;i<checkdetails.size();i++){
			String tempId=checkdetails.get(i).getLineid();
			idList.add(tempId);
		}
		return idList;
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @Description:根据分供方id获取考核明细主键
	 */
	@SuppressWarnings("static-access")
	private List<String> getCheckDetialByShipperId(String id){
		Dao<E_checkhead> checkHeadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> detailDao=new DaoFactory().create(E_checkdetail.class);
		E_checkhead condition=new E_checkhead();
		E_checkdetail detailCondition=new E_checkdetail();
		condition.setShipperLineid(id);
		if(id==null){
			condition.setLineid("-1");
		}
		List<E_checkhead> checkheadResult=checkHeadDao.select(condition);
		List<String> returnList=new ArrayList<String>();
		for(int i=0;i<checkheadResult.size();i++){
			detailCondition.setCheckheadLineid(checkheadResult.get(i).getLineid());
			List<E_checkdetail> tempList=detailDao.select(detailCondition);
			for(int j=0;j<tempList.size();j++)
				returnList.add(tempList.get(j).getLineid());
		}
		return returnList;
	}
	
	public List<String> getCheckDetialByShipperId(){
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		String id=crmInformationInterface.getShipperIdByUser();
		return getCheckDetialByShipperId(id);
	}
}
