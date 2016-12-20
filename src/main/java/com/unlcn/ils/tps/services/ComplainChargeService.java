package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_checkrule;
import com.unlcn.ils.tps.E_crm_complain_charge;
import com.unlcn.ils.tps.E_crm_complaint_type;
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
 *@Description:客诉异常明细服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ComplainChargeService implements ArrayContentProvider{

	@SuppressWarnings("static-access")
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_crm_complain_charge> dao = DaoFactory.create(E_crm_complain_charge.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_crm_complain_charge> result=dao.selectPageByCondition(conditions,
				condition,context.getPageable() ,context.getSortable(), true);
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		List<E_crm_complain_charge> lists=result.getContents();
		boolean updateFlag=false;
		String selectByCrmId="com.unlcn.ils.tps.E_crm_complaint_typeMapper.selectByCrmId";
		Dao<E_crm_complaint_type> complaintDao=new DaoFactory().create(E_crm_complaint_type.class);
		for(int i=0;i<lists.size();i++){
			//先做关联
			E_crm_complain_charge temp=lists.get(i);
			E_crm_complaint_type t_complaint_type=new E_crm_complaint_type();
			t_complaint_type.setTypeId(temp.getCategoryId());
			t_complaint_type.setTypeCategory(temp.getCategory());
			List<E_crm_complaint_type> crm_complaint_types=complaintDao.getSession().selectList(selectByCrmId, t_complaint_type);
			if(crm_complaint_types.size()!=0){
				if(crm_complaint_types.get(0).getRealateFlag()==1){
					temp.setComplainTypeRelateFlag(1);
					temp.setComplainTypeRealateLineid(crm_complaint_types.get(0).getRealateLineid());
					dao.update(temp);
					updateFlag=true;
				}				
			}

		}
		if(updateFlag==true){
			lists.clear();
			result=dao.selectPageByCondition(conditions,
					condition,context.getPageable() ,context.getSortable(), true);	
			lists=result.getContents();
		}
		for(int i=0;i<lists.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_crm_complain_charge tempComplain_charge=lists.get(i);
			map.put("LineID", tempComplain_charge.getLineid());
			map.put("complain_charge_Lineid", tempComplain_charge.getComplainChargeLineid());
			map.put("complain_id", tempComplain_charge.getComplainId());
			map.put("customer_order", tempComplain_charge.getCustomerOrder());
			map.put("category", tempComplain_charge.getCategory());
			map.put("category_id", tempComplain_charge.getCategoryId());
			map.put("category_name", tempComplain_charge.getCategoryName());
			map.put("charger_type", tempComplain_charge.getChargerType());
			map.put("charger_id", tempComplain_charge.getChargerId());
			map.put("charger_name", tempComplain_charge.getChargerName());
			map.put("percent", tempComplain_charge.getPercent());
			if(tempComplain_charge.getChargeMemo()!=null)
			map.put("charge_memo", tempComplain_charge.getChargeMemo());
			map.put("charge_time", tempComplain_charge.getChargeTime());
			map.put("create_user", tempComplain_charge.getCreateUser());
			map.put("createTime", tempComplain_charge.getCreatetime().toString());
			map.put("Realate_Flag", tempComplain_charge.getRealateFlag().toString());
			if(tempComplain_charge.getRealateFlag()==1){
				map.put("Realate_Lineid", tempComplain_charge.getRealateLineid());
			}
			map.put("Create_UserName", tempComplain_charge.getCreateUsername());
			map.put("Create_Time", tempComplain_charge.getCreateTime().toString());
			map.put("type_realateflag", tempComplain_charge.getComplainTypeRelateFlag().toString());
			if(tempComplain_charge.getComplainTypeRelateFlag()==1){
				map.put("type_realate_lineid", tempComplain_charge.getComplainTypeRealateLineid().toString());
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
	 * @param shipperId
	 * @param startTime
	 * @param endTime
	 * @Description:客诉异常明细更新
	 */
	@SuppressWarnings("static-access")
	public void updateComplaintCharges(String shipperId, String startTime,String endTime){
		//先从crm那里获取全部的考核标准
		Dao<E_crm_complain_charge> complaintDao=new DaoFactory().create(E_crm_complain_charge.class);
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		List<Map<String, String>> getInfos=crmInformationInterface.getComplainDetailFromCrm(shipperId, startTime, endTime);
		//经过筛选后添加到我方数据库
		for(int i=0;i<getInfos.size();i++){
			String crmCompalintId=getInfos.get(i).get("Lineid");
			String selectByCrmId="com.unlcn.ils.tps.E_crm_complain_chargeMapper.selectByCrmId";
			List<E_crm_complain_charge> crm_complain_charges=complaintDao.getSession().selectList(selectByCrmId, crmCompalintId);
			if(crm_complain_charges.size()==0){
				E_crm_complain_charge complain_charge=new E_crm_complain_charge();
				complain_charge.setComplainChargeLineid(getInfos.get(i).get("Lineid"));
				complain_charge.setComplainId(getInfos.get(i).get("complain_id"));	
				complain_charge.setCustomerOrder(getInfos.get(i).get("customer_order"));
				complain_charge.setCategory(getInfos.get(i).get("category"));
				complain_charge.setCategoryId(getInfos.get(i).get("category_id"));
				complain_charge.setCategoryName(getInfos.get(i).get("category_name"));
				complain_charge.setChargerType(getInfos.get(i).get("charger_type"));
				complain_charge.setChargerId(getInfos.get(i).get("charger_id"));
				complain_charge.setChargerName(getInfos.get(i).get("charger_name"));
				complain_charge.setPercent(getInfos.get(i).get("percent"));
				if(getInfos.get(i).get("charge_memo")!=null)
				complain_charge.setChargeMemo(getInfos.get(i).get("charge_memo"));
				complain_charge.setChargeTime(getInfos.get(i).get("charge_time"));
				complain_charge.setCreateUser(getInfos.get(i).get("create_user"));
				complain_charge.setCreatetime(Timestamp.valueOf(getInfos.get(i).get("createTime")));
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();		
				complain_charge.setCreateUsername(username);
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				complain_charge.setCreateTime(timestampnow);
				complain_charge.setRealateFlag(0);
				
				//检查该客诉异常标准是否已经存在于字典中，若没有，则添加该类型
				String category=getInfos.get(i).get("category");
				String category_id=getInfos.get(i).get("category_id");
				E_crm_complaint_type t_complaint_type=new E_crm_complaint_type();
				t_complaint_type.setTypeId(category_id);
				t_complaint_type.setTypeCategory(category);
				String selectByCrmId1="com.unlcn.ils.tps.E_crm_complaint_typeMapper.selectByCrmId";
				List<E_crm_complaint_type> crm_complaint_types=complaintDao.getSession().selectList(selectByCrmId1, t_complaint_type);
				if(crm_complaint_types.size()==0){
					//不存在时候设标志位为0
					complain_charge.setComplainTypeRelateFlag(0);
				}
				else{
					//存在时候检查是否已经关联，若没关联也同样设置为0
					//关联时加入关联的类型主键号
					if(crm_complaint_types.get(0).getRealateFlag()==0){
						complain_charge.setComplainTypeRelateFlag(0);
					}
					else{
						complain_charge.setComplainTypeRelateFlag(1);
						complain_charge.setComplainTypeRealateLineid(crm_complaint_types.get(0).getRealateLineid());
					}
				}
				SqlInterface sqlInterface=new SqlInterface();
				complain_charge.setLineid(sqlInterface.getNextVal("SEQ_TPS_CRM_COMPLAIN_CHARGE"));
				complaintDao.insert(complain_charge);
			}
		}
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:创建考核单
	 */
	@SuppressWarnings("static-access")
	public boolean createCheckDoc(List<String> idList){
		Dao<E_crm_complain_charge> complaintDao=new DaoFactory().create(E_crm_complain_charge.class);
		Dao<E_checkrule> checkRuleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_checkdocument> checkdocDao=new DaoFactory().create(E_checkdocument.class);
		for(int i=0;i<idList.size();i++){
			E_crm_complain_charge e_crm_complain_charge=complaintDao.selectByID(idList.get(i));
			E_checkdocument e_checkdocument=new E_checkdocument();
			e_checkdocument.setShipperLineid(e_crm_complain_charge.getChargerId());
			e_checkdocument.setShipperName(e_crm_complain_charge.getChargerName());
			E_checkrule e_checkrule=checkRuleDao.selectByID(e_crm_complain_charge.getComplainTypeRealateLineid());
			e_checkdocument.setItemLineid(e_checkrule.getItemLineid());
			e_checkdocument.setItemName(e_checkrule.getItemName());
			e_checkdocument.setOperateLineid(e_checkrule.getLineid());
			e_checkdocument.setOperateName(e_checkrule.getOperateName());
			e_checkdocument.setStartDate(Timestamp.valueOf(e_crm_complain_charge.getChargeTime()));
			e_checkdocument.setCheckNumber(1);
			e_checkdocument.setCheckMoney(e_checkrule.getSubmoney());
			e_checkdocument.setCheckMoneyStandard(e_checkrule.getSubmoney());
			e_checkdocument.setCheckScore(e_checkrule.getSubvalue());
			e_checkdocument.setCheckScoreStandard(e_checkrule.getSubvalue());
			e_checkdocument.setFlag(0);
			if(e_crm_complain_charge.getChargeMemo()!=null)
			e_checkdocument.setCheckMemo(e_crm_complain_charge.getChargeMemo());
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();		
			e_checkdocument.setCreateUsername(username);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_checkdocument.setCreateTime(timestampnow);
			SqlInterface sqlInterface=new SqlInterface();
			e_checkdocument.setLineid(sqlInterface.getNextVal("SEQ_TPS_CHECK_DOCUMENT"));
			checkdocDao.insert(e_checkdocument);
			CheckDocumentService checkDocumentService=new CheckDocumentService();
			String lastId=checkDocumentService.getMaxCheckDocId();
			e_crm_complain_charge.setRealateFlag(1);
			e_crm_complain_charge.setRealateLineid(lastId);
			complaintDao.update(e_crm_complain_charge);
		}
		return false;
	}
}
