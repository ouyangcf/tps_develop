package com.unlcn.ils.tps.services;

import java.math.BigDecimal; 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkdocument;   
import com.unlcn.ils.tps.E_checkrule;

import com.unlcn.ils.tps.E_configure_detail;
import com.unlcn.ils.tps.E_configure_head;
import com.unlcn.ils.tps.E_crm_complaint_type;
import com.unlcn.ils.tps.E_item;
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
 *@Description:考核项目服务  
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ItemService implements ArrayContentProvider{
	/**
	 * 
	 * @return
	 * @Description:获取所有项目
	 */
	@SuppressWarnings({ "static-access", "unused", "rawtypes" })
	private List<E_item> getAllItems(){
		List<Map>  returnList=new ArrayList<Map>();
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		Dao<E_configure_detail> configuredetailDao=new DaoFactory().create(E_configure_detail.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		String getMaxIdFullPath="com.unlcn.ils.tps.E_configure_headMapper.getMaxId";
		String selectByConfigure_idFullPath="com.unlcn.ils.tps.E_itemMapper.selectByConfigure_id";
		//listItems 这张表装了所有的item名称以及编号
		List<E_item> listItems=itemDao.selectAll();
		return listItems;
	}
	/**
	 * 
	 * @return
	 * @Description:获取规范类项目
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public List<Map> getOperateItems(){
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		List<E_item> listItems=itemDao.selectAll();
		List<Map> returnList=new ArrayList<Map>();
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0&&listItems.get(i).getFlag()==2){
				map.put(listItems.get(i).getLineid().toString(),listItems.get(i).getItemName());
				returnList.add(map);				
			}
		}
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取绩效类项目
	 */
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public List<Map> getItems(){
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		List<E_item> listItems=itemDao.selectAll();
		List<Map> returnList=new ArrayList<Map>();
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0&&listItems.get(i).getFlag()==1){
				map.put(listItems.get(i).getLineid().toString(),listItems.get(i).getItemName());
				returnList.add(map);				
			}
		}
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取考核类型
	 */
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public List<Map> getTypes(){
		List<Map>  returnList=new ArrayList<Map>();
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		List<E_item> listItems=getAllItems();
		String selectByItemLineID="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0){
				List<E_checkrule> operateList=checkruleDao.getSession().selectList(selectByItemLineID,listItems.get(i).getLineid());
				List<Map> tempList=new ArrayList<Map>();
				for(int j=0;j<operateList.size();j++){		
					Map map2=new HashMap();
					if(operateList.get(j).getActive()!=null&&operateList.get(j).getActive()==0){
						map2.put(operateList.get(j).getLineid().toString(), operateList.get(j).getOperateName());
						tempList.add(map2);						
					}
				}
				map.put("contents", tempList);
				returnList.add(map);				
			}

		}
		
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取考核项目id
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public List<Integer> getItemsId(){
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		Dao<E_configure_detail> configuredetailDao=new DaoFactory().create(E_configure_detail.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		List<Integer> returnList=new ArrayList<Integer>();
		List<E_item> listItems=itemDao.selectAll();
		for(int i=0;i<listItems.size();i++){
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0){
				returnList.add(listItems.get(i).getLineid());				
			}
		}
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取考核类型id
	 */
	@SuppressWarnings({ "static-access", "unused", "rawtypes", "unchecked" })
	public List<Map> getTypesId(){
		List<Map>  returnList=new ArrayList<Map>();
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		Dao<E_configure_detail> configuredetailDao=new DaoFactory().create(E_configure_detail.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		//listItems 这张表装了所有的item名称以及编号
		List<E_item> listItems=itemDao.selectAll();
		String selectByItemLineID="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0){
				List<E_checkrule> operateList=checkruleDao.getSession().selectList(selectByItemLineID,listItems.get(i).getLineid());
				List<Integer> tempList=new ArrayList<Integer>();
				for(int j=0;j<operateList.size();j++){		
					if(operateList.get(j).getActive()!=null&&operateList.get(j).getActive()==0){
					tempList.add(operateList.get(j).getLineid());
					}
				}
				map.put("contents", tempList);
				returnList.add(map);				
			}

		}
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取考核标准金额
	 */
	@SuppressWarnings({ "unchecked", "static-access", "unused", "rawtypes" })
	public List<Map> getStandardMoney(){
		List<Map>  returnList=new ArrayList<Map>();
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		Dao<E_configure_detail> configuredetailDao=new DaoFactory().create(E_configure_detail.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		//listItems 这张表装了所有的item名称以及编号
		List<E_item> listItems=itemDao.selectAll();
		String selectByItemLineID="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0){
				List<E_checkrule> operateList=checkruleDao.getSession().selectList(selectByItemLineID,listItems.get(i).getLineid());
				List<Map> tempList=new ArrayList<Map>();
				for(int j=0;j<operateList.size();j++){	
					if(operateList.get(j).getActive()!=null&&operateList.get(j).getActive()==0){
					Map map2=new HashMap();
					map2.put(operateList.get(j).getLineid().toString(), operateList.get(j).getSubmoney());
					tempList.add(map2);
					}
				}
				map.put("contents", tempList);
				returnList.add(map);				
			}

		}
		
		return returnList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取考核标准扣分
	 */
	@SuppressWarnings({ "static-access", "unused", "unchecked", "rawtypes" })
	public List<Map> getStandardValue(){
		List<Map>  returnList=new ArrayList<Map>();
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		Dao<E_configure_detail> configuredetailDao=new DaoFactory().create(E_configure_detail.class);
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
		//listItems 这张表装了所有的item名称以及编号
		List<E_item> listItems=itemDao.selectAll();
		String selectByItemLineID="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();
			if(listItems.get(i).getActive()!=null&&listItems.get(i).getActive()==0){
			List<E_checkrule> operateList=checkruleDao.getSession().selectList(selectByItemLineID,listItems.get(i).getLineid());
			List<Map> tempList=new ArrayList<Map>();
			for(int j=0;j<operateList.size();j++){		
				Map map2=new HashMap();
				if(operateList.get(j).getActive()!=null&&operateList.get(j).getActive()==0){
				map2.put(operateList.get(j).getLineid().toString(), operateList.get(j).getSubvalue());
				tempList.add(map2);
				}
			}
			map.put("contents", tempList);
			returnList.add(map);
		}
	}
		return returnList;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:检验考核项目
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public E_checkdocument checkItems(E_checkdocument data){
		data.setFlag(0);
		//情况一，输入了考核项目，考核类型
		if((data.getItemOther().equals(""))&&(data.getOperateOther().equals("")))
		{
			ShipperService shipperService=new ShipperService();
			List<Map<String,String>> shipperList=shipperService.getShipperInfo();
			String shippername=null;
			String shipper_lineid=data.getShipperLineid().toString();
			for(int i=0;i<shipperList.size();i++){
				String getid = shipperList.get(i).get("id");
				if(getid.equals(shipper_lineid))
				{
					shippername=shipperList.get(i).get("name");
					break;
				}
			}
			data.setShipperName(shippername);
			Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
			data.setItemName(itemDao.selectByID(data.getItemLineid()).getItemName());
			Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
			data.setOperateName(checkruleDao.selectByID(data.getOperateLineid()).getOperateName());
			data.setCheckMoneyStandard(checkruleDao.selectByID(data.getOperateLineid()).getSubmoney());
			data.setCheckScoreStandard(checkruleDao.selectByID(data.getOperateLineid()).getSubvalue());

			Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			data.setCreateTime(timestamp);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			data.setCreateUsername(username);
			BigDecimal tempBigDecimal=new BigDecimal(data.getCheckNumber());
			data.setCheckMoney(data.getCheckMoney().multiply(tempBigDecimal));
			SqlInterface sqlInterface=new SqlInterface();
			data.setLineid(sqlInterface.getNextVal("SEQ_TPS_CHECK_DOCUMENT"));
			return data;
		}
		//情况二，输入了考核项目，其他考核类型
		if((data.getItemOther().equals(""))&&(!data.getOperateOther().equals("")))
		{
			Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
			Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
			E_item e_item=itemDao.selectByID(data.getItemLineid());
			E_checkrule e_checkrule=new E_checkrule();
			//获取配置明细里面最大的id值
			String getMaxIdFullPath2="com.unlcn.ils.tps.E_checkruleMapper.getMaxId";
			List<E_checkrule> maxIdList2=checkruleDao.getSession().selectList(getMaxIdFullPath2);
			int maxCheckRuleId=1;
			if(maxIdList2.size()!=0){
				maxCheckRuleId=maxIdList2.get(0).getLineid();
				maxCheckRuleId++;
			}
			//植入值
			e_checkrule.setLineid(maxCheckRuleId);
			e_checkrule.setItemLineid(data.getItemLineid());
			e_checkrule.setItemName(e_item.getItemName());
			e_checkrule.setOperateName(data.getOperateOther());
			e_checkrule.setSubvalue(data.getCheckScore());
			e_checkrule.setSubmoney(data.getCheckMoney());
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_checkrule.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_checkrule.setCreateUsername(username);
			e_checkrule.setActive(0);
			checkruleDao.insert(e_checkrule);
			
			ShipperService shipperService=new ShipperService();
			List<Map<String,String>> shipperList=shipperService.getShipperInfo();
			String shippername=null;
			String shipper_lineid=data.getShipperLineid().toString();
			for(int i=0;i<shipperList.size();i++){
				String getid = shipperList.get(i).get("id");
				if(getid.equals(shipper_lineid))
				{
					shippername=shipperList.get(i).get("name");
					break;
				}
			}
			
			data.setShipperName(shippername);
			data.setItemName(itemDao.selectByID(data.getItemLineid()).getItemName());
			data.setOperateName(data.getOperateOther());
			data.setCheckMoneyStandard(data.getCheckMoney());
			data.setCheckScoreStandard(data.getCheckScore());
			data.setCreateTime(timestampnow);
			data.setCreateUsername(username);
			BigDecimal tempBigDecimal=new BigDecimal(data.getCheckNumber());
			data.setCheckMoney(data.getCheckMoney().multiply(tempBigDecimal));
			SqlInterface sqlInterface=new SqlInterface();
			data.setLineid(sqlInterface.getNextVal("SEQ_TPS_CHECK_DOCUMENT"));
			return data;
		}
		//情况三，输入了其他考核项目，其他考核类型
		if((!data.getItemOther().equals(""))&&(!data.getOperateOther().equals("")))
		{
			Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
			//获取最新的考核配置表表头序号
			String getMaxIdFullPath1="com.unlcn.ils.tps.E_configure_headMapper.getMaxId";
			List<E_configure_head> maxIdList1=configureheadDao.getSession().selectList(getMaxIdFullPath1);
			
			String maxConfigureId=maxIdList1.get(0).getLineid();		
			E_item newItem=new E_item();
			String getMaxIdFullPath="com.unlcn.ils.tps.E_itemMapper.getMaxId";
			Dao<E_item> itemDao=new DaoFactory().create(E_item.class);
			List<E_item> maxIdList=itemDao.getSession().selectList(getMaxIdFullPath);
			Integer maxID=maxIdList.get(0).getLineid();
			maxID++;
			newItem.setLineid(maxID);
			newItem.setItemName(data.getItemOther());
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			newItem.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			newItem.setCreateUsername(username);		
			newItem.setActive(0);
			itemDao.insert(newItem);
			//在新的项目下新建新的类型
			E_checkrule e_checkrule=new E_checkrule();
			//获取配置明细里面最大的id值
			String getMaxIdFullPath2="com.unlcn.ils.tps.E_checkruleMapper.getMaxId";
			Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
			List<E_checkrule> maxIdList2=checkruleDao.getSession().selectList(getMaxIdFullPath2);
			int maxCheckRuleId=1;
			if(maxIdList2.size()!=0){
				maxCheckRuleId=maxIdList2.get(0).getLineid();
				maxCheckRuleId++;			
			}
			//植入值
			e_checkrule.setLineid(maxCheckRuleId);
			e_checkrule.setItemLineid(maxID);
			e_checkrule.setItemName(data.getItemOther());
			e_checkrule.setOperateName(data.getOperateOther());
			e_checkrule.setSubvalue(data.getCheckScore());
			e_checkrule.setSubmoney(data.getCheckMoney());
			e_checkrule.setCreateTime(timestampnow);

			e_checkrule.setCreateUsername(username);
			e_checkrule.setActive(0);
			checkruleDao.insert(e_checkrule);
			
			ShipperService shipperService=new ShipperService();
			List<Map<String,String>> shipperList=shipperService.getShipperInfo();
			String shippername=null;
			String shipper_lineid=data.getShipperLineid().toString();
			for(int i=0;i<shipperList.size();i++){
				String getid = shipperList.get(i).get("id");
				if(getid.equals(shipper_lineid))
				{
					shippername=shipperList.get(i).get("name");
					break;
				}
			}
			data.setShipperName(shippername);
			data.setItemLineid(maxID);
			data.setItemName(data.getItemOther());
			data.setOperateLineid(maxCheckRuleId);
			data.setOperateName(data.getOperateOther());
			data.setCheckMoneyStandard(data.getCheckMoney());
			data.setCheckScoreStandard(data.getCheckScore());
			data.setCreateTime(timestampnow);
			data.setCreateUsername(username);
			BigDecimal tempBigDecimal=new BigDecimal(data.getCheckNumber());
			data.setCheckMoney(data.getCheckMoney().multiply(tempBigDecimal));
			SqlInterface sqlInterface=new SqlInterface();
			data.setLineid(sqlInterface.getNextVal("SEQ_TPS_CHECK_DOCUMENT"));
			return data;
		}

		return null;
	}
	/**
	 * 
	 * @param e_item
	 * @return
	 * @Description:创建新的项目
	 */
	@SuppressWarnings("static-access")
	public boolean newItem(E_item e_item){
		String getMaxIdFullPath="com.unlcn.ils.tps.E_itemMapper.getMaxId";
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);  
		List<E_item> e_items=itemDao.getSession().selectList(getMaxIdFullPath);
		Integer maxid=8000;
		if(e_items.size()!=0){
			maxid=e_items.get(0).getLineid()+1;
		}
		e_item.setLineid(maxid);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		e_item.setCreateUsername(username);
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_item.setCreateTime(timestampnow);
		e_item.setActive(0);
		itemDao.insert(e_item);
		return true;
	}
	@Override
	public Page<E_item> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_item> dao = DaoFactory.create(E_item.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_item> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:更新新的项目
	 */
	@SuppressWarnings("static-access")
	public E_item updateCheckRule(E_item data){
		String selectByItemLineID="com.unlcn.ils.tps.E_checkruleMapper.selectByItemLineID";
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		List<E_checkrule> operateList=checkruleDao.getSession().selectList(selectByItemLineID,data.getLineid());
		for(int i=0;i<operateList.size();i++){
			E_checkrule temp=operateList.get(i);
			temp.setItemName(data.getItemName());
			checkruleDao.update(temp);
		}
		return data;
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:删除项目
	 */
	@SuppressWarnings({ "unused", "static-access" })
	public boolean delItems(List<Integer> idList){
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);  
		for (int i = 0; i < idList.size(); i++) {
			Integer itemId=idList.get(i);
			E_checkrule checkruleCondition=new E_checkrule();
			checkruleCondition.setItemLineid(itemId);
			List<E_checkrule> resultCheckrules=checkruleDao.select(checkruleCondition);
			for (int j = 0; j < resultCheckrules.size(); j++) {
				
			}
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	public boolean activeItems(List<Integer> idList){
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);  
		for (int i = 0; i < idList.size(); i++) {
			Integer itemId=idList.get(i);
			E_checkrule checkruleCondition=new E_checkrule();
			checkruleCondition.setItemLineid(itemId);
			List<E_checkrule> resultCheckrules=checkruleDao.select(checkruleCondition);
			for (int j = 0; j < resultCheckrules.size(); j++) {
				resultCheckrules.get(j).setActive(1);
			}
			E_item e_item=itemDao.selectByID(itemId);
			e_item.setActive(1);
			itemDao.update(e_item);
			checkruleDao.updateBatch(resultCheckrules);
		}
		return false;		
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	public boolean activeCheckRule(List<Integer> idList){
		Dao<E_checkrule> checkruleDao=new DaoFactory().create(E_checkrule.class);
		Dao<E_item> itemDao=new DaoFactory().create(E_item.class);  
		Dao<E_crm_complaint_type> crmDao=new DaoFactory().create(E_crm_complaint_type.class);  
		for (int i = 0; i < idList.size(); i++) {
			Integer checkRuleID=idList.get(i);
			E_checkrule checkrule=checkruleDao.selectByID(checkRuleID);
			checkrule.setActive(1);
			checkruleDao.update(checkrule);
			E_crm_complaint_type condition=new E_crm_complaint_type();
			condition.setRealateLineid(checkRuleID);
			List<E_crm_complaint_type> results=crmDao.select(condition);
			for (int j = 0; j < results.size(); j++) {
				E_crm_complaint_type complaint_type=results.get(j);
				if(complaint_type.getRealateFlag()==1){
					complaint_type.setRealateFlag(0);
					crmDao.update(complaint_type);
				}
			}
		}
		return false;		
	}
}
