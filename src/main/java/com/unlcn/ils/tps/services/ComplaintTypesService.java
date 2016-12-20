package com.unlcn.ils.tps.services;
 
import java.sql.Timestamp;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.unlcn.ils.tps.E_checkrule;
import com.unlcn.ils.tps.E_crm_complaint_type;
import com.unlcn.ils.tps.E_item;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
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
 *@Description:客诉异常标准服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ComplaintTypesService implements ArrayContentProvider{
	private static  final Logger log = Logger.getLogger(Logger .class);
	/**
	 * 
	 * 
	 * @Description:更新客诉异常标准
	 */
	@SuppressWarnings("static-access")
	public void updateComplaintTypes(){
		//先从crm那里获取全部的考核标准
		log.info("调用updateComplaintTypes方法");
		Dao<E_crm_complaint_type> complaintDao=new DaoFactory().create(E_crm_complaint_type.class);
		log.info("初始化CrmInformationInterface类");
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		log.info("调用CrmInformationInterface类的getComplainCategoryFromCrm方法");
		List<Map<String, String>> getInfos=crmInformationInterface.getComplainCategoryFromCrm("3", null);
		//经过筛选后添加到我方数据库
		for(int i=0;i<getInfos.size();i++){
			String crmCompalintId=getInfos.get(i).get("id");
			String Type_category=getInfos.get(i).get("Type_category");
			E_crm_complaint_type t_complaint_type=new E_crm_complaint_type();
			t_complaint_type.setTypeId(crmCompalintId);
			t_complaint_type.setTypeCategory(Type_category);
			
			String selectByCrmId="com.unlcn.ils.tps.E_crm_complaint_typeMapper.selectByCrmId";
			List<E_crm_complaint_type> crm_complaint_types=complaintDao.getSession().selectList(selectByCrmId, t_complaint_type);
			if(crm_complaint_types.size()==0){
				E_crm_complaint_type complaint_type=new E_crm_complaint_type();
				SqlInterface sqlInterface=new SqlInterface();
				complaint_type.setLineid(sqlInterface.getNextVal("SEQ_TPS_CRM_COMPLAIN_TYPES"));
				complaint_type.setTypeId(getInfos.get(i).get("id"));
				complaint_type.setTypeName(getInfos.get(i).get("name"));
				complaint_type.setTypeDesc(getInfos.get(i).get("desc"));
				complaint_type.setParentId(getInfos.get(i).get("parentId"));
				if(getInfos.get(i).get("status").equals("1"))
				complaint_type.setTypeStatus("生效");
				else complaint_type.setTypeStatus("失效");
				complaint_type.setOperUser(getInfos.get(i).get("operUser"));
				complaint_type.setOperTime(getInfos.get(i).get("operTime"));
				complaint_type.setRealateFlag(0);		
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();		
				complaint_type.setCreateUsername(username);
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				complaint_type.setCreateTime(timestampnow);
				complaint_type.setTypeCategory(Type_category);
				complaintDao.insert(complaint_type);
			}
		}
	}

	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_crm_complaint_type> dao = DaoFactory.create(E_crm_complaint_type.class) ;
		Dao<E_checkrule> checkRuleDao = DaoFactory.create(E_checkrule.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_crm_complaint_type> result=dao.selectPageByCondition(conditions,
				condition,context.getPageable() ,context.getSortable(), true);
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		List<E_crm_complaint_type> lists=result.getContents();
		for(int i=0;i<lists.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_crm_complaint_type tempComplaint_type=lists.get(i);
			map.put("Lineid", tempComplaint_type.getLineid());
			map.put("Type_Id", tempComplaint_type.getTypeId());
			map.put("Type_name", tempComplaint_type.getTypeName());
			map.put("Type_desc", tempComplaint_type.getTypeDesc());
			map.put("Parent_id", tempComplaint_type.getParentId());
			map.put("Type_status", tempComplaint_type.getTypeStatus());
			map.put("Oper_user", tempComplaint_type.getOperUser());
			map.put("Oper_time", tempComplaint_type.getOperTime());
			map.put("Realate_Flag", tempComplaint_type.getRealateFlag().toString());
			if(tempComplaint_type.getRealateFlag()==1){
				map.put("Realate_Lineid", tempComplaint_type.getRealateLineid().toString());
				E_checkrule e_checkrule=checkRuleDao.selectByID(tempComplaint_type.getRealateLineid());
				map.put("Realate_Name", e_checkrule.getOperateName());
			}
			map.put("Create_UserName", tempComplaint_type.getCreateUsername());
			map.put("Create_Time", tempComplaint_type.getCreateTime().toString());
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
	 * @param data
	 * @param e_checkrule
	 * @return
	 * @Description:更新客诉异常标准
	 */
	public boolean updateComplaintTypesWithNewoperate(E_crm_complaint_type data, E_checkrule e_checkrule){
		Dao<E_crm_complaint_type> complaintDao = DaoFactory.create(E_crm_complaint_type.class);
		Dao<E_checkrule> checkRuleDao = DaoFactory.create(E_checkrule.class);
		Dao<E_item> itemDao = DaoFactory.create(E_item.class);
		if(data.getRealateFlag()==1){
			Integer itemLineid=Integer.parseInt(data.getTypeName());
			String getMaxIdFullPath2="com.unlcn.ils.tps.E_checkruleMapper.getMaxId";
			List<E_checkrule> maxIdList2=checkRuleDao.getSession().selectList(getMaxIdFullPath2);
			int maxCheckRuleId=1;
			if(maxIdList2.size()!=0){
				maxCheckRuleId=maxIdList2.get(0).getLineid();
				maxCheckRuleId++;
			}
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_checkrule.setLineid(maxCheckRuleId);
			e_checkrule.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_checkrule.setCreateUsername(username);
			e_checkrule.setItemLineid(itemLineid);
			e_checkrule.setItemName(itemDao.selectByID(e_checkrule.getItemLineid()).getItemName());
			e_checkrule.setActive(0);
			checkRuleDao.insert(e_checkrule);
			List<E_checkrule> maxIdList=checkRuleDao.getSession().selectList(getMaxIdFullPath2);
			E_crm_complaint_type complaint_type=complaintDao.selectByID(data.getLineid());
			complaint_type.setRealateFlag(1);
			complaint_type.setRealateLineid(maxIdList.get(0).getLineid());
			complaintDao.update(complaint_type);
		}
		else{
			String itemName=data.getTypeName();
			String getMaxIdFullPath="com.unlcn.ils.tps.E_itemMapper.getMaxId";
			List<E_item> e_items=itemDao.getSession().selectList(getMaxIdFullPath);
			Integer maxid=8000;
			if(e_items.size()!=0){
				maxid=e_items.get(0).getLineid()+1;
			}
			E_item e_item=new E_item();
			e_item.setItemName(itemName);
			e_item.setLineid(maxid);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_item.setCreateUsername(username);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_item.setCreateTime(timestampnow);
			e_item.setActive(0);
			itemDao.insert(e_item);
			List<E_item> e_items1=itemDao.getSession().selectList(getMaxIdFullPath);

			
			Integer itemLineid=e_items1.get(0).getLineid();
			String getMaxIdFullPath2="com.unlcn.ils.tps.E_checkruleMapper.getMaxId";
			List<E_checkrule> maxIdList2=checkRuleDao.getSession().selectList(getMaxIdFullPath2);
			int maxCheckRuleId=1;
			if(maxIdList2.size()!=0){
				maxCheckRuleId=maxIdList2.get(0).getLineid();
				maxCheckRuleId++;
			}
			Timestamp timestampnow1=new Timestamp(System.currentTimeMillis());
			e_checkrule.setLineid(maxCheckRuleId);
			e_checkrule.setCreateTime(timestampnow1);
			e_checkrule.setCreateUsername(username);
			e_checkrule.setItemLineid(itemLineid);
			e_checkrule.setItemName(e_items1.get(0).getItemName());
			e_checkrule.setActive(0);
			checkRuleDao.insert(e_checkrule);
			List<E_checkrule> maxIdList=checkRuleDao.getSession().selectList(getMaxIdFullPath2);
			E_crm_complaint_type complaint_type=complaintDao.selectByID(data.getLineid());
			complaint_type.setRealateFlag(1);
			complaint_type.setRealateLineid(maxIdList.get(0).getLineid());
			complaintDao.update(complaint_type);
		}
		return false;
	}
	/**
	 * 
	 * @param Lineid
	 * @return
	 * @Description:取消关联
	 */
	public boolean cancelRelate(String Lineid){
		Dao<E_crm_complaint_type> complaintDao = DaoFactory.create(E_crm_complaint_type.class);
		E_crm_complaint_type complaint_type=complaintDao.selectByID(Lineid);
		complaint_type.setRealateFlag(0);
		complaint_type.setRealateLineid(null);
		complaintDao.update(complaint_type);
		return false;
	}
}
