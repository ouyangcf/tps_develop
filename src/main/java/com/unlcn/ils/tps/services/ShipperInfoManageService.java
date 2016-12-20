package com.unlcn.ils.tps.services;
 
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.unlcn.ils.crm.customer.subsupplier.Shipper;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.tps.E_audit;  
import com.unlcn.ils.tps.E_authentication;
import com.unlcn.ils.tps.E_join_company;
import com.unlcn.ils.tps.E_join_driver;
import com.chinacreator.c2.context.OperationContextHolder; 
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:分供方信息管理服务 
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ShipperInfoManageService implements ArrayContentProvider{

	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		Dao<E_join_driver> driverDao = DaoFactory.create(E_join_driver.class);
		Dao<E_join_company> companyDao = DaoFactory.create(E_join_company.class);
		Dao<E_authentication> authenDao= DaoFactory.create(E_authentication.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_audit> result=auditDao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_audit> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		//下面进行过期检测
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		boolean updateFlag=false;
		for(int i=0;i<lists.size();i++){
			//先做过期检测
			E_audit temp=lists.get(i);
			if(temp.getAuditFlag()==0)
				continue;
			if (temp.getAuditFlag()==3) {
				if(temp.getEndDate().before(timestampnow)){
					temp.setAuditFlag(5);
					auditDao.update(temp);
					//同时更新相应的司机公司表
					if(temp.getJoinFlag()==0){
						E_join_driver tempDriver=driverDao.selectByID(temp.getJoinId());
						tempDriver.setCheckFlag(5);
						driverDao.update(tempDriver);
					}
					if(temp.getJoinFlag()==1){
						E_join_company tempCompany=companyDao.selectByID(temp.getJoinId());
						tempCompany.setCheckFlag(5);
						companyDao.update(tempCompany);
					}
					updateFlag=true;
				}				
			}
		}
		if(updateFlag==true){
			lists.clear();
			result=auditDao.selectPageByCondition(conditions,
			condition,context.getPageable() ,context.getSortable(), true);	
			lists=result.getContents();
		}		
		for(int i=0;i<lists.size();i++){
			E_audit e_temp=lists.get(i);
			Map<String, String> map=new HashMap<String, String>();	
			map.put("lineId", e_temp.getLineid().toString());
			//当是司机时候
			if(e_temp.getJoinFlag()==0){
				E_join_driver driver=driverDao.selectByID(e_temp.getJoinId());
				map.put("joinFlag", "0");
				if(driver.getDriverName()!=null)
				map.put("joinName", driver.getDriverName());
				map.put("joinId", driver.getLineid().toString());
				map.put("province_id", driver.getProvinceId().toString());
				map.put("province", driver.getProvince());
				map.put("city_id", driver.getCityId().toString());
				map.put("city", driver.getCity());
				if(driver.getShipperId()!=null)
				map.put("shipperId", driver.getShipperId().toString());
				E_authentication authen_condition=new E_authentication();
				authen_condition.setShippperId(driver.getShipperId());
				List<E_authentication> results=authenDao.select(authen_condition);
				if (results!=null&&results.size()!=0) {
					Integer cerstatus=results.get(results.size()-1).getAuthenticationFlag();
					if(cerstatus==1){
						map.put("cerstatus", "通过");
					}
					if (cerstatus==2) {
						map.put("cerstatus", "不通过");
					}
				}else{
						map.put("cerstatus", "未审");
				}
			}
			if(e_temp.getJoinFlag()==1){
				E_join_company company=companyDao.selectByID(e_temp.getJoinId());
				if(company.getCompanyName()!=null)
				map.put("joinName", company.getCompanyName());
				map.put("joinFlag", "1");
				map.put("joinId", company.getLineid().toString());
				map.put("province_id", company.getProvinceId().toString());
				map.put("province", company.getProvince());
				map.put("city_id", company.getCityId().toString());
				map.put("city", company.getCity());
				if(company.getShipperId()!=null)
				map.put("shipperId", company.getShipperId().toString());
				E_authentication authen_condition=new E_authentication();
				authen_condition.setShippperId(company.getShipperId());
				List<E_authentication> results=authenDao.select(authen_condition);
				if (results!=null&&results.size()!=0) {
					Integer cerstatus=results.get(results.size()-1).getAuthenticationFlag();
					if(cerstatus==1){
						map.put("cerstatus", "通过");
					}
					if (cerstatus==2) {
						map.put("cerstatus", "不通过");
					}
				}else{
						map.put("cerstatus", "未审");
				}
			}
			if(e_temp.getAuditUser()!=null)
			map.put("audit_user", e_temp.getAuditUser());
			if(e_temp.getAuditTime()!=null)
			map.put("audit_time", e_temp.getAuditTime().toString());	
			if(e_temp.getAuditMemo()!=null)
			map.put("audit_memo", e_temp.getAuditMemo());
			if(e_temp.getBeginDate()!=null)
				map.put("begin_date", e_temp.getBeginDate().toString());
			if(e_temp.getEndDate()!=null)
				map.put("end_date", e_temp.getEndDate().toString());
			if(e_temp.getActive()!=null){
				map.put("active", e_temp.getActive().toString());
			}
			if(e_temp.getActiveUser()!=null){
				map.put("activeuser", e_temp.getActiveUser());
			}
			if(e_temp.getActiveMemo()!=null){
				map.put("activememo", e_temp.getActiveMemo());
			}
			if(e_temp.getActiveTime()!=null){
				map.put("activetime", e_temp.getActiveTime().toString());
			}
			if(e_temp.getAuditFlag()!=null){
				map.put("auditflag", e_temp.getAuditFlag().toString());
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
	 * @param data
	 * @return
	 * @Description:根据条件查询司机
	 */
	@SuppressWarnings("static-access")
	public List<String> selectByCondition(E_join_driver data){
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_audit> auditDao=new DaoFactory().create(E_audit.class);
		E_join_driver tempDriver=new E_join_driver();
		E_join_company tempCompany=new E_join_company();
		List<String> idList=new ArrayList<String>();
		if(data.getDriverName()!=null){
			//String shipperName="%"+data.getDriverName()+"%";
			tempDriver.setDriverName('%'+data.getDriverName()+'%');
			tempCompany.setCompanyName('%'+data.getDriverName()+'%');
		}
		if(data.getProvinceId()!=null){
			Integer provinceId=data.getProvinceId();
			tempDriver.setProvinceId(provinceId);
			tempCompany.setProvinceId(provinceId);
		}
		if(data.getCityId()!=null){
			Integer cityid=data.getCityId();
			tempDriver.setCityId(cityid);
			tempCompany.setCityId(cityid);
		}
		String selectByCondition="com.unlcn.ils.tps.E_join_companyMapper.selectByCondition";
		List<E_join_company> companies=companyDao.getSession().selectList(selectByCondition, tempCompany);
		String selectByCondition1="com.unlcn.ils.tps.E_join_driverMapper.selectByCondition";
		List<E_join_driver> drivers=driverDao.getSession().selectList(selectByCondition1, tempDriver);	
		List<E_audit> conList=new ArrayList<E_audit>();
		for(int i=0;i<drivers.size();i++){
			E_audit tempAudit=new E_audit();
			tempAudit.setJoinFlag(0);
			tempAudit.setJoinId(drivers.get(i).getLineid());
			conList.add(tempAudit);
		}
		for(int i=0;i<companies.size();i++){
			E_audit tempAudit=new E_audit();
			tempAudit.setJoinFlag(1);
			tempAudit.setJoinId(companies.get(i).getLineid());
			conList.add(tempAudit);
		}
		String selectByCondition2="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
		for(int i=0;i<conList.size();i++){
			List<E_audit> tempList=auditDao.getSession().selectList(selectByCondition2, conList.get(i));
			for(int j=0;j<tempList.size();j++){
				idList.add(tempList.get(j).getLineid());
			}
		}
		return idList;
	}
	/**
	 * 
	 * @param idList
	 * @param reason
	 * @return
	 * @Description:分供方禁用
	 */
	@SuppressWarnings("static-access")
	public boolean activeShipper(List<String> idList,String reason){
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_audit> auditDao=new DaoFactory().create(E_audit.class);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		for(int i=0;i<idList.size();i++){
			String id=idList.get(i);
			E_audit audit=auditDao.selectByID(id);
			//更新初审表状态
			audit.setActive(1);
			audit.setActiveMemo(reason);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			audit.setActiveTime(timestampnow);
			audit.setActiveUser(username);
			audit.setAuditFlag(6);
			auditDao.update(audit);
			//更新司机表或者公司表状态
			if(audit.getJoinFlag()==0){
				E_join_driver driver=driverDao.selectByID(audit.getJoinId());
				driver.setActive(1);
				driver.setActiveMemo(reason);
				Timestamp timestampnow1=new Timestamp(System.currentTimeMillis());
				driver.setActiveTime(timestampnow1);
				driver.setActiveUser(username);
				driver.setCheckFlag(6);
				driverDao.update(driver);
				
				Shipper shipper=new Shipper();
				CustomerInfo customerInfo=new CustomerInfo();
				customerInfo.setId(driver.getShipperId());
				shipper.setCustomerId(customerInfo);
				shipper.setCertStatus("0");
				shipper.setDesc(reason);
				dbService.updateShipper(shipper);
			}
			if(audit.getJoinFlag()==1){
				E_join_company company=companyDao.selectByID(audit.getJoinId());
				company.setActive(1);
				company.setActiveMemo(reason);
				Timestamp timestampnow1=new Timestamp(System.currentTimeMillis());
				company.setActiveTime(timestampnow1);
				company.setActiveUser(username);
				company.setCheckFlag(6);
				companyDao.update(company);		
				
				Shipper shipper=new Shipper();
				CustomerInfo customerInfo=new CustomerInfo();
				customerInfo.setId(company.getShipperId());
				shipper.setCustomerId(customerInfo);
				shipper.setCertStatus("0");
				shipper.setDesc(reason);
				dbService.updateShipper(shipper);
			}
		}
		return false;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:重新认证
	 */
	@SuppressWarnings("static-access")
	public boolean reAudit(E_audit data){
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_audit> auditDao=new DaoFactory().create(E_audit.class);
		E_audit audit=auditDao.selectByID(data.getLineid());
		audit.setAuditFlag(3);
		audit.setBeginDate(data.getBeginDate());
		audit.setEndDate(data.getEndDate());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		audit.setAuditTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		audit.setAuditUser(username);
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null) {
			throw new RuntimeException("CrmInformationService初始化失败,请重试");
		}
		auditDao.update(audit);
		if(audit.getJoinFlag()==0){
			E_join_driver driver=driverDao.selectByID(audit.getJoinId());
			driver.setCheckFlag(3);
			driverDao.update(driver);
			Shipper shipper=new Shipper();
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setId(driver.getShipperId());
			shipper.setCustomerId(customerInfo);
			
			Date endDate=audit.getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			Timestamp timestamp=Timestamp.valueOf(endString);
			shipper.setCertValidTime(timestamp);
			dbService.updateShipper(shipper);
		}
		if(audit.getJoinFlag()==1){
			E_join_company company=companyDao.selectByID(audit.getJoinId());
			company.setCheckFlag(3);
			companyDao.update(company);	
			Shipper shipper=new Shipper();
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setId(company.getShipperId());
			shipper.setCustomerId(customerInfo);
			
			Date endDate=audit.getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			Timestamp timestamp=Timestamp.valueOf(endString);
			shipper.setCertValidTime(timestamp);
			dbService.updateShipper(shipper);
		}	

		return false;
	}
	/**
	 * 
	 * @param idList
	 * @param reason
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public boolean disactiveShipper(List<String> idList){
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_audit> auditDao=new DaoFactory().create(E_audit.class);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		for(int i=0;i<idList.size();i++){
			String id=idList.get(i);
			E_audit audit=auditDao.selectByID(id);
			//更新初审表状态
			audit.setActive(0);
			audit.setAuditFlag(3);
			auditDao.update(audit);
			//更新司机表或者公司表状态
			if(audit.getJoinFlag()==0){
				E_join_driver driver=driverDao.selectByID(audit.getJoinId());
				try {
				Shipper shipper=new Shipper();
				CustomerInfo customerInfo=new CustomerInfo();
				customerInfo.setId(driver.getShipperId());
				shipper.setCustomerId(customerInfo);
				shipper.setCertStatus("1");
				shipper.setDesc("  ");
				dbService.updateShipper(shipper);
				driver.setActive(0);
				driver.setCheckFlag(3);
				driverDao.update(driver);					
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("更改失败,请重试");
				}

			}
			if(audit.getJoinFlag()==1){
				E_join_company company=companyDao.selectByID(audit.getJoinId());
				try {
				Shipper shipper=new Shipper();
				CustomerInfo customerInfo=new CustomerInfo();
				customerInfo.setId(company.getShipperId());
				shipper.setCustomerId(customerInfo);
				shipper.setCertStatus("1");
				shipper.setDesc("  ");
				dbService.updateShipper(shipper);
				company.setActive(0);
				company.setCheckFlag(3);
				companyDao.update(company);						
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("更改失败,请重试");
				}

			}
		}
		return false;
	}
	
	public boolean checkShipper(String shipperid){
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null) {
			throw new RuntimeException("CrmInformationService初始化失败,请重试");
		}
		try {
			Shipper shipper=new Shipper();
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setId(shipperid);
			shipper.setCustomerId(customerInfo);
			List<Shipper> shippers=dbService.getShipper(shipper);
			if (shippers.get(0).getCertStatus().equals("2")) {
				return false;
			}
			else return true;			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("网络异常请重试");
		}
	}
}
