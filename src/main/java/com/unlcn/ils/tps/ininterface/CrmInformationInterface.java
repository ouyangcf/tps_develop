package com.unlcn.ils.tps.ininterface;
 
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.unlcn.ils.tps.E_account;  
import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_city;
import com.unlcn.ils.tps.E_join_company;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_file;
import com.unlcn.ils.tps.E_join_intentline;
import com.unlcn.ils.tps.E_linkman;
import com.unlcn.ils.tps.E_province;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.basic.org.dto.OrgDTO;
import com.chinacreator.asp.comp.sys.core.security.service.AccessControlService;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.complaint.complainCharge.ComplainCharge;
import com.unlcn.ils.crm.complaint.complaintType.ComplaintType;
import com.unlcn.ils.crm.customer.account.Account;
import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.unlcn.ils.crm.customer.linkman.Linkman;
import com.unlcn.ils.crm.customer.subsupplier.Shipper;
import com.unlcn.ils.crm.dto.Result;
import com.unlcn.ils.crm.service.CrmInformationService;
 /**
  * 
  *@Title: 
  *@Description:crm接口   
  *@Author:Administrator 
  *@Since:2016-8-18
  *@Version:1.1.0  
  */  
public class CrmInformationInterface { 
	private static  final Logger log = Logger.getLogger(Logger .class);
	/**
	 *  
	 * @param type
	 * @param complaintType
	 * @return
	 * @Description:获取客诉异常标准
	 */
	private List<Map<String, String>> getComplainCategory(String type, String complaintType){
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null)
			log.info("初始化后dbService为空");
		else
			log.info("初始化后dbService不为空");
		List<ComplaintType> complaintTypes=dbService.getComplainCategory(null,"3");
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<complaintTypes.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			map.put("id", complaintTypes.get(i).getId().toString());
			map.put("name", complaintTypes.get(i).getName());
			map.put("desc", complaintTypes.get(i).getDesc());
			map.put("parentId", complaintTypes.get(i).getParentId());
			map.put("status", complaintTypes.get(i).getStatus());
			map.put("operUser", complaintTypes.get(i).getOperUser());
			map.put("Type_category", complaintTypes.get(i).getCategory());
			map.put("operTime", complaintTypes.get(i).getOperTime().toString());
			list.add(map); 
		}
		return list;
	}
	
	public List<Map<String, String>> getComplainCategoryFromCrm(String type, String complaintType){
		return getComplainCategory(type,null);
	}
	/**
	 * 
	 * @param shipperId
	 * @param startTime
	 * @param endTime
	 * @return
	 * @Description:获取客诉异常明细
	 */
	private List<Map<String, String>> getComplainDetail(String shipperId,String startTime,String endTime){
		//Dao<E_complain_charge> complainDao=new DaoFactory().create(E_complain_charge.class);
		//List<E_complain_charge> complain_charges=complainDao.selectAll();
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null)
			log.info("初始化后dbService为空");
		else
			log.info("初始化后dbService不为空");
		log.info("调用CrmInformationService的getComplainDetail方法前");
		List<ComplainCharge> complain_charges=dbService.getComplainDetail(shipperId, startTime, endTime);
		log.info("调用CrmInformationService的getComplainDetail方法后，获取的ComplainCharge集合长度为："+complain_charges.size());
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<complain_charges.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			map.put("Lineid", complain_charges.get(i).getId());
			map.put("complain_id", complain_charges.get(i).getComplainId());
			map.put("customer_order", complain_charges.get(i).getCustomerOrder());
			map.put("category", complain_charges.get(i).getCategory());
			map.put("category_id", complain_charges.get(i).getCategoryId());
			map.put("category_name", complain_charges.get(i).getCategoryName());
			map.put("charger_type", complain_charges.get(i).getChargerType());
			map.put("charger_id", complain_charges.get(i).getChargerId());
			map.put("charger_name", complain_charges.get(i).getChargerName());
			map.put("percent", complain_charges.get(i).getPercent().toString());
			if(complain_charges.get(i).getMemo()!=null)
			map.put("charge_memo", complain_charges.get(i).getMemo());
			map.put("charge_time", complain_charges.get(i).getChargeTime().toString());
			map.put("create_user", complain_charges.get(i).getCreateUser());
			map.put("createTime", complain_charges.get(i).getCreateTime().toString());
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String, String>> getComplainDetailFromCrm(String shipperId, String startTime,String endTime){
		return getComplainDetail(shipperId,startTime,endTime);
	}
	/**
	 * 
	 * @param shipper
	 * @return
	 * @Description:获取分供方
	 */
	@SuppressWarnings({ "static-access" })
	private Map<String,String> getShipper(Shipper shipper){
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null)
			log.info("初始化后dbService为空");
		else
			log.info("初始化后dbService不为空");
		log.info("调用CrmInformationService的getShipper方法前");
		if(dbService==null) {
			throw new RuntimeException("CrmInformationService初始化失败,请重试");
		}
		try {
		List<Shipper> shippers=dbService.getShipper(shipper);
		log.info("调用CrmInformationService的getShipper方法后，获取的集合长度为："+shippers.size());
		Shipper shipper3=shippers.get(0);
		Map<String,String> map=new HashMap<String, String>();
		if(shipper3.getId()!=null)
		map.put("id", shipper3.getId().toString());
		if(shipper3.getCustomerId().getId()!=null)
		map.put("custominfoId", shipper3.getCustomerId().getId());
		if(shipper3.getCode()!=null)
		map.put("code", shipper3.getCode().toString());
		if(shipper3.getName()!=null)
		map.put("name", shipper3.getName());
		if(shipper3.getLinkman()!=null)
		map.put("linkman", shipper3.getLinkman());
		if(shipper3.getPhone()!=null)
		map.put("phone", shipper3.getPhone());
		if(shipper3.getTel()!=null)
		map.put("tel", shipper3.getTel());
		if(shipper3.getHasPermit()!=null)
		map.put("hasPermit", shipper3.getHasPermit());
		if(shipper3.getShortName()!=null)
		map.put("shortName", shipper3.getShortName());
		if(shipper3.getTaxNo()!=null)
		map.put("taxNo", shipper3.getTaxNo());
		log.info("完成shipper到map的转换");
		if(shipper3.getBusinessType()!=null)
		map.put("businessType", shipper3.getBusinessType());
		if(shipper3.getCarNumber()!=null)
		map.put("carNumber", shipper3.getCarNumber().toString());
		if(shipper3.getOwner()!=null)
		map.put("owner", shipper3.getOwner());
		if(shipper3.getCertStatus()!=null)
		map.put("certStatus", shipper3.getCertStatus());
		if(shipper3.getCertValidTime()!=null)
		map.put("certValidTime", shipper3.getCertValidTime().toString());
		if(shipper3.getHasInvoice()!=null)
		map.put("hasInovice", shipper3.getHasInvoice());
		if(shipper3.getDesc()!=null)
		map.put("desc", shipper3.getDesc());
		if(shipper3.getProvince()!=null){
			Dao<E_province> dao=new DaoFactory().create(E_province.class);
			E_province condition=new E_province();
			condition.setProvincename(shipper3.getProvince());
			List<E_province> results=dao.select(condition);
			if (results.size()!=0) {
				map.put("province", results.get(0).getLineid().toString());
			}
		}
		if(shipper3.getCity()!=null){
			Dao<E_city> dao=new DaoFactory().create(E_city.class);
			E_city condition=new E_city();
			if (shipper3.getProvince()!=null) {
				condition.setProvincename(shipper3.getProvince());
			}
			condition.setCityname(shipper3.getCity());
			List<E_city> results=dao.select(condition);
			if (results.size()!=0) {
				map.put("city", results.get(0).getLineid().toString());
			}
		}
		if(shipper3.getRegAddress()!=null)
		map.put("regAddress", shipper3.getRegAddress());
		if(shipper3.getCapital()!=null)
		map.put("capital", shipper3.getCapital().toString());
		if(shipper3.getShipperProp()!=null)
		map.put("shipperprop", shipper3.getShipperProp().toString());
		if(shipper3.getRoadLicence()!=null){
			map.put("roadLicence",shipper3.getRoadLicence().toString());
		}
		if(shipper3.getRoadLicenceFilepath()!=null){
			map.put("roadLicenceFilepath", shipper3.getRoadLicenceFilepath().toString());
		}
		if(shipper3.getDispatchUser()!=null){
			map.put("dispatchUser", shipper3.getDispatchUser());
		}
		if(shipper3.getDispatchPhone()!=null){
			map.put("dispatchPhone", shipper3.getDispatchPhone().toString());
		}
		if(shipper3.getGpsUser()!=null){
			map.put("gpsUser", shipper3.getGpsUser());
		}
		if(shipper3.getGpsPhone()!=null){
			map.put("gpsPhone", shipper3.getGpsPhone().toString());
		}
		if(shipper3.getReceiptUser()!=null){
			map.put("receiptUser", shipper3.getReceiptUser());
		}
		if(shipper3.getReceiptPhone()!=null){
			map.put("receiptPhone", shipper3.getReceiptPhone().toString());
		}
		if(shipper3.getAccountUser()!=null){
			map.put("accountUser", shipper3.getAccountUser());
		}
		if(shipper3.getAccountPhone()!=null){
			map.put("accountPhone", shipper3.getAccountPhone().toString());
		}
		if(shipper3.getCreateUser()!=null){
			map.put("createUser", shipper3.getCreateUser());
		}
		if(shipper3.getCreateTime()!=null){
			map.put("createTime", shipper3.getCreateTime().toString());
		}
		log.info("完成shipper到map的转换");
		return map;			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("网络原因未能推送成功,请重新点击保存按钮");
		}

	}
	/**
	 * 
	 * @param shipperId
	 * @return
	 * @Description:根据id获取分供方
	 */
	public  Map<String,String> getShipperFromCrmById(String shipperId){
		log.info("正在调用服务s_getShipperFromCrmById,传入的参数为："+shipperId);
		Shipper shipper=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId(shipperId);
		shipper.setCustomerId(customerInfo);
		return getShipper(shipper);
	}
	/**
	 * 
	 * @param shipper
	 * @return
	 * @Description:更新分供方
	 */
	private Result updateShipper(Shipper shipper){
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		log.info("调用CrmInformationService的updateShipper方法");
		if(dbService == null) {
			log.info("db为空");
		} else {
			log.info("db不为空 ");
		} 
		Result result=dbService.updateShipper(shipper);
		return result;
/*		Dao<E_shipper> shipperDao=new DaoFactory().create(E_shipper.class);
		E_shipper e_shipper=shipperDao.selectByID(shipper.getCustomerId().getId());
		if(shipper.getCode()!=null){
			e_shipper.setCode(Integer.parseInt(shipper.getCode()));
		}
		if(shipper.getName()!=null){
			e_shipper.setName(shipper.getName());
		}
		if(shipper.getLinkman()!=null){
			e_shipper.setLinkman(shipper.getLinkman());
		}
		if(shipper.getPhone()!=null){
			e_shipper.setPhone(shipper.getPhone());
		}
		if(shipper.getTel()!=null){
			e_shipper.setTel(shipper.getTel());
		}
		if(shipper.getHasPermit()!=null){
			e_shipper.setHasPermit(shipper.getHasPermit());
		}
		if(shipper.getShortName()!=null){
			e_shipper.setShortName(shipper.getShortName());
		}
		if(shipper.getTaxNo()!=null){
			e_shipper.setTaxNo(shipper.getTaxNo());
		}
		if(shipper.getBusinessType()!=null){
			e_shipper.setBusinessType(shipper.getBusinessType());
		}
		if(shipper.getCarNumber()!=null){
			e_shipper.setCarNumber(shipper.getCarNumber());
		}
		if(shipper.getOwner()!=null){
			e_shipper.setOwner(shipper.getOwner());
		}
		if(shipper.getCertStatus()!=null){
			e_shipper.setCertStatus(shipper.getCertStatus());
		}
		if(shipper.getCertValidTime()!=null){
			e_shipper.setCertValidTime(shipper.getCertValidTime());
		}
		if(shipper.getHasInvoice()!=null){
			e_shipper.setHasInovice(shipper.getHasInvoice());
		}
		if(shipper.getDesc()!=null){
			e_shipper.setDesc(shipper.getDesc());
		}
		shipperDao.update(e_shipper);
		Result result=new Result(1, "", "");
		return result;*/
	}
	/**
	 * 
	 * @param map
	 * @param con
	 * @return
	 * @Description:更新分供方
	 */
	@SuppressWarnings({ "static-access", "rawtypes" })
	public Result updateShipper2Crm(Map map,Map con){
		//return updateShipper(shipper);
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null) {
			throw new RuntimeException("CrmInformationService初始化失败,请重试");
		}
		Integer flag=Integer.parseInt(con.get("joinFlag").toString());
		String id=con.get("joinId").toString();
		String auditid=con.get("auditId").toString();
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		Dao<E_audit> auditDao=new DaoFactory().create(E_audit.class);
		Shipper shipperCondition=new Shipper();
		CustomerInfo customerInfoCondition=new CustomerInfo();
		customerInfoCondition.setId(map.get("id").toString());
		shipperCondition.setCustomerId(customerInfoCondition);
		List<Shipper> shipperResult=dbService.getShipper(shipperCondition);
		Shipper shipper=shipperResult.get(0);
		log.info("看看看"+map.get("code").toString());
		log.info("看看看"+map.get("name").toString());
		log.info("看看看"+map.get("name").toString());
		log.info("ididid"+shipper.getCustomerId().getId());
		if(map.get("code")!=null)
			shipper.setCode(map.get("code").toString());
		if(map.get("name")!=null)
			shipper.setName(map.get("name").toString());

		if(map.get("linkman")!=null)
			shipper.setLinkman(map.get("linkman").toString());

		if(map.get("phone")!=null)
			shipper.setPhone(map.get("phone").toString());

		if(map.get("tel")!=null)
			shipper.setTel(map.get("tel").toString());

		if(map.get("hasPermit")!=null)
			shipper.setHasPermit(map.get("hasPermit").toString());

		if(map.get("shortName")!=null)
			shipper.setShortName(map.get("shortName").toString());

		if(map.get("taxNo")!=null)
			shipper.setTaxNo(map.get("taxNo").toString());

		if(map.get("businessType")!=null)
			shipper.setBusinessType(map.get("businessType").toString());

		if(map.get("carNumber")!=null)
			shipper.setCarNumber(Integer.parseInt(map.get("carNumber").toString()));

		if(map.get("owner")!=null)
			shipper.setOwner(map.get("owner").toString());

		if(map.get("certStatus")!=null)
			shipper.setCertStatus(map.get("certStatus").toString());

		if(map.get("certValidTime")!=null)
			shipper.setCertValidTime(Timestamp.valueOf(map.get("certValidTime").toString()));

		if(map.get("hasInovice")!=null)
			shipper.setHasInvoice(map.get("hasInovice").toString());

		if(map.get("desc")!=null)
			shipper.setDesc(map.get("desc").toString());

		if (map.get("province")!=null) {
			Dao<E_province> dao=new DaoFactory().create(E_province.class);
			E_province province=dao.selectByID(map.get("province"));
			shipper.setProvince(province.getProvincename());
		}

		if (map.get("city")!=null) {
			Dao<E_city> dao=new DaoFactory().create(E_city.class);
			E_city city=dao.selectByID(map.get("city"));
			shipper.setCity(city.getCityname());
		}

		if (map.get("regAddress")!=null) {
			shipper.setRegAddress((String) map.get("regAddress"));
		}

		if (map.get("capital")!=null) {
			String string=(String) map.get("capital");
			shipper.setCapital( Integer.parseInt(string));
		}

		if (map.get("shipperprop")!=null) {
			shipper.setShipperProp((String) map.get("shipperprop"));
		}
		if(map.get("roadLicence")!=null){
			shipper.setRoadLicence((String) map.get("roadLicence"));
		}

		if(map.get("roadLicenceFilepath")!=null){
			shipper.setRoadLicenceFilepath((String) map.get("roadLicenceFilepath"));
		}

		if(map.get("dispatchUser")!=null){
			shipper.setDispatchUser((String) map.get("dispatchUser"));
		}
		if(map.get("dispatchPhone")!=null){
			shipper.setDispatchPhone((String) map.get("dispatchPhone"));
		}
		if(map.get("gpsUser")!=null){
			shipper.setGpsUser((String) map.get("gpsUser"));
		}
		if(map.get("gpsPhone")!=null){
			shipper.setGpsPhone((String) map.get("gpsPhone"));
		}
		if(map.get("receiptUser")!=null){
			shipper.setReceiptUser((String) map.get("receiptUser"));
		}
		if(map.get("receiptPhone")!=null){
			shipper.setReceiptPhone((String) map.get("receiptPhone"));
		}
		if(map.get("accountUser")!=null){
			shipper.setAccountUser((String) map.get("accountUser"));
		}
		if(map.get("accountPhone")!=null){
			shipper.setAccountPhone((String) map.get("accountPhone"));
		}
		log.info("时间，任务"+map.get("createUser")+map.get("createTime"));
		shipper.setCreateUser((String) map.get("createUser"));
		shipper.setCreateTime(Timestamp.valueOf(map.get("createTime").toString()));
		Timestamp now=new Timestamp(System.currentTimeMillis());
		//shipper.setCreateTime(now);
		shipper.setUpdateTime(now);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		shipper.setUpdateUser(username);
		Result result=new Result(-1, "", "网络问题未能更新,请重试");
		try {
			result=updateShipper(shipper);
			log.info("更新结果为"+result.getRetCode()); 
			if (result.getRetCode()==-1) {
				log.info("错误描述为"+result.getErrDesc());
			}
			else{
				log.info("更新成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("网络问题未能更新,请重试");
		}
		//同时更新我方司机、企业表
		if(flag==0){
			E_join_driver driver=driverDao.selectByID(id);
			E_audit audit=auditDao.selectByID(auditid);
			if(map.get("name")!=null)
				driver.setDriverName(map.get("name").toString());
			if(map.get("taxNo")!=null)
				driver.setCardno(map.get("taxNo").toString());
			if(map.get("certStatus")!=null){
				Integer certStatus=Integer.parseInt(map.get("certStatus").toString());
				if(certStatus==0){
					driver.setCheckFlag(0);
				}
				if(certStatus==1){
					driver.setCheckFlag(3);
				}
			}
			if (map.get("province")!=null) {
				Dao<E_province> dao=new DaoFactory().create(E_province.class);
				E_province province=dao.selectByID(map.get("province"));
				driver.setProvince(province.getProvincename());
				driver.setProvinceId(Integer.parseInt((String) map.get("province")));
			}
			if (map.get("city")!=null) {
				Dao<E_city> dao=new DaoFactory().create(E_city.class);
				E_city city=dao.selectByID(map.get("city"));
				driver.setCity(city.getCityname());
				driver.setCityId(Integer.parseInt((String) map.get("city")));
			}
			driverDao.update(driver);
			if(map.get("certStatus")!=null){
				Integer certStatus=Integer.parseInt(map.get("certStatus").toString());
				if(certStatus==0){
					audit.setAuditFlag(driver.getCheckFlag());
				}
				if(certStatus==1){
					audit.setAuditFlag(driver.getCheckFlag());
				}
			}				
			if(map.get("certValidTime")!=null)
				audit.setAuditTime(Timestamp.valueOf(map.get("certValidTime").toString()));
			auditDao.update(audit);
		}
		if(flag==1){
			E_join_company company=companyDao.selectByID(id);
			E_audit audit=auditDao.selectByID(auditid);
			if(map.get("name")!=null)
				company.setCompanyName(map.get("name").toString());
			if(map.get("linkman")!=null)
				company.setContractName(map.get("linkman").toString());
			if(map.get("phone")!=null)
				company.setMobileno(map.get("phone").toString());
			if(map.get("tel")!=null)
				company.setContract(map.get("tel").toString());
			if(map.get("taxNo")!=null)
				company.setTaxNo(map.get("taxNo").toString());
			if(map.get("owner")!=null)
				company.setLegalUser(map.get("owner").toString());
			if(map.get("certStatus")!=null){
				Integer certStatus=Integer.parseInt(map.get("certStatus").toString());
				if(certStatus==0){
					company.setCheckFlag(0);
				}
				if(certStatus==1){
					company.setCheckFlag(3);
				}
			}
			if (map.get("province")!=null) {
				Dao<E_province> dao=new DaoFactory().create(E_province.class);
				E_province province=dao.selectByID(map.get("province"));
				company.setProvince(province.getProvincename());
				company.setProvinceId(Integer.parseInt((String) map.get("province")));
			}
			if (map.get("city")!=null) {
				Dao<E_city> dao=new DaoFactory().create(E_city.class);
				E_city city=dao.selectByID(map.get("city"));
				company.setCity(city.getCityname());
				company.setCityId(Integer.parseInt((String) map.get("city")));
			}
			if (map.get("capital")!=null) {
				String string=(String) map.get("capital");
				company.setCapital(new BigDecimal(string));
			}
			companyDao.update(company);
			if(map.get("certStatus")!=null){
				Integer certStatus=Integer.parseInt(map.get("certStatus").toString());
				if(certStatus==0){
					audit.setAuditFlag(company.getCheckFlag());
				}
				if(certStatus==1){
					audit.setAuditFlag(company.getCheckFlag());
				}
			}	
			if(map.get("certStatus")!=null)
				audit.setAuditFlag(company.getCheckFlag());
			if(map.get("certValidTime")!=null)
				audit.setAuditTime(Timestamp.valueOf(map.get("certValidTime").toString()));
			auditDao.update(audit);
		}

		//return updateShipper(shipper);
		return result;
	}
	/**
	 * 
	 * @param shipper
	 * @return
	 * @Description:添加分供方
	 */
	private Result addShipper( Shipper shipper,List<Linkman>linkmanslist,List<Account>accountslist){
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null){
			log.info("初始化后，dbService为空");
		}
		else{
			log.info("初始化后，dbService不为空");
		}
/*		Dao<E_shipper> shipperDao=new DaoFactory().create(E_shipper.class);
		E_shipper e_shipper=new E_shipper();
		e_shipper.setName(shipper.getName());
		e_shipper.setTaxNo(shipper.getTaxNo());
		e_shipper.setBusinessType(shipper.getBusinessType());
		e_shipper.setCarNumber(shipper.getCarNumber());
		e_shipper.setHasInovice(shipper.getHasInvoice());
		shipperDao.insert(e_shipper);
		String getMaxiId="com.unlcn.ils.tps.E_shipperMapper.getMaxId";
		List<E_shipper> e_shippers=shipperDao.getSession().selectList(getMaxiId);
		Result result=new Result(1, "", "");
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", e_shippers.get(0).getId().toString());
		result.setMap(map);*/
		try {
			
			log.info("推送到crm前的认证状态为"+shipper.getCertStatus());
			//shipper.setCertStatus(null);
			Result result=dbService.addShipper(shipper, linkmanslist, accountslist);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("网络原因未能推送成功,请重新点击保存按钮");
		}
		 
	}
	/**
	 * 
	 * @param driver
	 * @return
	 * @Description:添加分供方（加盟）
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public Result addShipper2Crm_d(E_join_driver driver,List<E_account>accountlist,List<E_linkman>linkmanlist,List<E_join_intentline>joininterlinelist,String idFilePath){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		log.info("构建分供方新增信息");
		CustomerInfo customerInfo=new CustomerInfo();
		Shipper shipper=new Shipper();
		//后期增加
		String select="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
		Dao<E_audit> dao=new DaoFactory().create(E_audit.class); 
		Dao<E_join_file>joinfileDao = DaoFactory.create(E_join_file.class);
		E_audit condition=new E_audit();
		condition.setJoinFlag(0);
		condition.setJoinId(driver.getLineid());
		List<E_audit> results=dao.getSession().selectList(select, condition);
		customerInfo.setCode(results.get(0).getLineid());
		customerInfo.setType("1"); 
		customerInfo.setIdType("1");
		customerInfo.setIdNumber(driver.getCardno());
		//身份证照片
		//customerInfo.setIdFilepath(idFilePath);
		customerInfo.setIdFilepath("xxx");
		
		customerInfo.setName(driver.getDriverName());
		customerInfo.setLinkman(driver.getDriverName());
		if (driver.getMobileno()!=null) {
			log.info("获取司机的手机号码"+driver.getMobileno());
		}
		else{
			log.info("获取司机的手机号码为空");
		}
		//传入省份和城市
		customerInfo.setProvince(driver.getProvince());
		customerInfo.setCity(driver.getCity());
		customerInfo.setPhone(driver.getMobileno());
		//后期增加
		customerInfo.setType("1");
		shipper.setCustomerId(customerInfo);
		shipper.setName(driver.getDriverName());
		shipper.setTaxNo(driver.getCardno());
		shipper.setBusinessType("1");
		shipper.setCarNumber(driver.getCarNumber());
		shipper.setHasInvoice("1");
		//后期添加
		shipper.setLinkman("");
		shipper.setPhone(driver.getMobileno());
		shipper.setHasPermit("1");
		shipper.setShortName(driver.getDriverName());
		shipper.setShipperProp("0");
		shipper.setOwner(driver.getDriverName());
		log.info("set的城市是"+driver.getCity());
		shipper.setCity(driver.getCity());
		shipper.setProvince(driver.getProvince());
		log.info("set的省份是"+driver.getProvince());
		//意向线路
		String interString="";
		for (E_join_intentline e_join_intentline : joininterlinelist) {
			interString=interString.concat(e_join_intentline.getStartCity()+"-"+e_join_intentline.getDestProvince().concat("，"));
		}
		interString = interString.toString();
		interString = interString.substring(0, interString.length()-1);
		log.info("输出的意向线路是："+interString);
		shipper.setDesc(interString);
		shipper.setRoadLicence("");
		shipper.setRoadLicenceFilepath("");
		
		shipper.setCarNumber(driver.getCarNumber());
		shipper.setLinkman(customerInfo.getLinkman());
		shipper.setCreateUser(driver.getCreateUser());
		Timestamp now=new Timestamp(System.currentTimeMillis());
		shipper.setShortName(driver.getDriverName());
		shipper.setTel(driver.getMobileno());
		shipper.setCapital(0);
		shipper.setRegAddress("无");
		shipper.setCreateTime(driver.getCreateTime());
		shipper.setUpdateTime(now);
		shipper.setUpdateUser(username);
		//后期添加
		
		//联系人信息
		List<Linkman>linkmanslist=new ArrayList<Linkman>();
		if(linkmanlist!=null && linkmanlist.size()>0){
		for (E_linkman linkman : linkmanlist) {
			Linkman linkmans = new Linkman();
			linkmans.setCustomerId(customerInfo);
			//linkmans.setServiceType(linkman.getServiceType());
			linkmans.setServiceType("");
			linkmans.setDepartment(linkman.getDepartment());
			linkmans.setJob(linkman.getJob());
			linkmans.setName(linkman.getName());
			linkmans.setPhone(linkman.getPhone());
			linkmans.setTel(linkman.getTel());
			linkmans.setEmail(linkman.getEmail());
			linkmans.setQq(linkman.getQq());
			linkman.setOperUser(linkman.getOperUser());
			linkmans.setOperTime(linkman.getOperTime());
			linkmanslist.add(linkmans);
		}
		}
		//账户信息
		List<Account>accountslist = new ArrayList<Account>();
		if(accountlist!=null && accountlist.size()>0){
		for (E_account account : accountlist) {
			Account accounts = new Account();
			//待定行
			accounts.setCustomerId(customerInfo.getId());
			if(account.getOwnerType().equals("个人")){
				account.setOwnerType("1");
			}else if(account.getOwnerType().equals("企业")){
				account.setOwnerType("2");
			}
			if(account.getType().equals("一般")){
				account.setType("1");
			}else if(account.getType().equals("专用")){
				account.setType("2");
			}
			accounts.setOwnerType(account.getOwnerType());
			accounts.setName(account.getName());
			accounts.setBank(account.getBank());
			accounts.setAccountNumber(account.getAccountNumber());
			//accounts.setAccountFilepath(account.getAccountFilepath());
			accounts.setAccountFilepath("xxx");
			accounts.setBankOpenBranch(account.getBankOpenBranch());
			accounts.setOwnerName(account.getOwnerName());
			accounts.setDesc(account.getDesc());
			accounts.setStartTime(account.getStartTime());
			accounts.setEndTime(account.getEndTime());
			accounts.setStatus(account.getStatus());
			accounts.setType(account.getType());
			accounts.setOperUser(account.getOperUser());
			accounts.setOperTime(account.getOperTime());
			accountslist.add(accounts);
		}
		}
		
		if(driver.getCheckFlag()==3){
			//shipper.setCertStatus("1");
			shipper.setCertStatus("2");
			String selectByCondition="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
			E_audit audit=new E_audit();
			audit.setJoinFlag(0);
			audit.setJoinId(driver.getLineid().toString());
			Dao<E_audit> audit_dao=new DaoFactory().create(E_audit.class);
			List<E_audit> audits=dao.getSession().selectList(selectByCondition, audit);
			Date endDate=audits.get(0).getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			log.info("要生成的失效日期为："+endString);
			shipper.setCertValidTime(Timestamp.valueOf(endString));
			//后期添加
			shipper.setCode(audits.get(0).getLineid());
			//后期添加
		}
		else{
			shipper.setCertStatus("0");
		}
		log.info("准备调用addshipper接口");
		return addShipper(shipper, linkmanslist, accountslist);
	}
	/**
	 * 
	 * @param company
	 * @return
	 * @Description:添加分供方（合作）
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public Result addShipper2Crm_c(E_join_company company,List<E_linkman>linkmanlist,List<E_account>accountlist,List<E_join_intentline>joininterlinelist,String idFilePath,String roadLicencePath){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		CustomerInfo customerInfo=new CustomerInfo();
		Shipper shipper=new Shipper();
		customerInfo.setType("2");
		shipper.setCustomerId(customerInfo);
		shipper.setName(company.getCompanyName());
		//后期增加
		String select="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
		Dao<E_audit> audit_dao=new DaoFactory().create(E_audit.class);
		E_audit condition=new E_audit();
		condition.setJoinFlag(1);
		condition.setJoinId(company.getLineid());
		List<E_audit> results=audit_dao.getSession().selectList(select, condition);
		customerInfo.setCode(results.get(0).getLineid());
		customerInfo.setType("2");
		customerInfo.setIdType("2");
		customerInfo.setIdNumber(company.getTaxNo());
		//营业执照照片
		customerInfo.setIdFilepath("");
		//customerInfo.setIdFilepath(idFilePath);
		customerInfo.setName(company.getCompanyName());
		customerInfo.setLinkman(company.getContractName());
		customerInfo.setPhone(company.getContract());
		customerInfo.setTel(company.getContract());
		//传入城市和省份
		customerInfo.setProvince(company.getProvince());
		customerInfo.setCity(company.getCity());
		//后期增加
		shipper.setTaxNo(company.getTaxNo());
		shipper.setShortName(company.getCompanyName());
		shipper.setCapital(company.getCapital().intValue());
		shipper.setBusinessType("1");
		shipper.setCarNumber(company.getCarNumber());
		Timestamp now=new Timestamp(System.currentTimeMillis());
		shipper.setUpdateTime(now);
		shipper.setUpdateUser(username);
		//注册资金
		//shipper.setCapital(Integer.valueOf(company.getCapital().toString()));
		shipper.setHasInvoice("1");
		shipper.setLinkman(company.getContractName());
		shipper.setPhone(company.getMobileno());
		shipper.setLinkman(customerInfo.getLinkman());
		shipper.setHasPermit("0");
		shipper.setCity(company.getCity());
		log.info("set的城市是"+company.getCity());
		shipper.setProvince(company.getProvince());
		log.info("set的省份是"+company.getProvince());
		shipper.setTel(company.getContract());
		if(company.getContract()!=null)
		shipper.setTel(company.getContract());
		shipper.setOwner(company.getLegalUser());
		
		//道路经营许可证及照片
		shipper.setRoadLicence(company.getRoadLicence());
		shipper.setRoadLicenceFilepath("");
		//shipper.setRoadLicenceFilepath(roadLicencePath);
		//意向线路
		String interString="";
		for (E_join_intentline e_join_intentline : joininterlinelist) {
			interString=interString.concat(e_join_intentline.getStartCity()+"-"+e_join_intentline.getDestProvince().concat("，"));
		}
		interString = interString.toString();
		interString = interString.substring(0, interString.length()-1);
		log.info("输出的意向线路是："+interString);
		shipper.setDesc(interString);
		
		
		
		shipper.setRegAddress("无");
		
		//联系人信息
		List<Linkman>linkmanslist=new ArrayList<Linkman>();
		if(linkmanlist!=null && linkmanlist.size()>0){
			for (E_linkman linkman : linkmanlist) {
				Linkman linkmans = new Linkman();
				linkmans.setCustomerId(customerInfo);
				//linkmans.setServiceType(linkman.getServiceType());
				linkmans.setServiceType("");
				linkmans.setDepartment(linkman.getDepartment());
				linkmans.setJob(linkman.getJob());
				linkmans.setName(linkman.getName());
				linkmans.setPhone(linkman.getPhone());
				linkmans.setTel(linkman.getTel());
				linkmans.setEmail(linkman.getEmail());
				linkmans.setQq(linkman.getQq());
				linkman.setOperUser(linkman.getOperUser());
				linkmans.setOperTime(linkman.getOperTime());
				linkmanslist.add(linkmans);
			}
		}
		//账户信息
		List<Account>accountslist = new ArrayList<Account>();
		if(accountlist!=null && accountlist.size()>0){
			for (E_account account : accountlist) {
				Account accounts = new Account();
				//待定行
				accounts.setCustomerId(customerInfo.getId());
				//
				if(account.getOwnerType().equals("个人")){
					account.setOwnerType("1");
				}else if(account.getOwnerType().equals("企业")){
					account.setOwnerType("2");
				}
				if(account.getType().equals("一般")){
					account.setType("1");
				}else if(account.getType().equals("专用")){
					account.setType("2");
				}
				accounts.setOwnerType(account.getOwnerType());
				accounts.setName(account.getName());
				accounts.setBank(account.getBank());
				accounts.setAccountNumber(account.getAccountNumber());
				accounts.setAccountFilepath(account.getAccountFilepath());
				accounts.setBankOpenBranch(account.getBankOpenBranch());
				accounts.setOwnerName(account.getOwnerName());
				accounts.setDesc(account.getDesc());
				accounts.setStartTime(account.getStartTime());
				accounts.setEndTime(account.getEndTime());
				accounts.setStatus(account.getStatus());
				accounts.setType(account.getType());
				accounts.setOperUser(account.getOperUser());
				accounts.setOperTime(account.getOperTime());
				accountslist.add(accounts);
			}
		}
				
		if(company.getCheckFlag()==3){ 
			//shipper.setCertStatus("1");
			shipper.setCertStatus("2");
			String selectByCondition="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
			E_audit audit=new E_audit();
			audit.setJoinFlag(1);
			audit.setJoinId(company.getLineid().toString());
			Dao<E_audit> dao=new DaoFactory().create(E_audit.class);
			List<E_audit> audits=dao.getSession().selectList(selectByCondition, audit);
			Date date=audits.get(0).getEndDate();
			Timestamp timestamp=new Timestamp(date.getTime());
			Date endDate=audits.get(0).getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			shipper.setCertValidTime(Timestamp.valueOf(endString));
			//后期添加
			shipper.setCode(audits.get(0).getLineid());
		}
		else{
			shipper.setCertStatus("0");
		}
		return addShipper(shipper, linkmanslist, accountslist);
	}
	/**
	 * 
	 * @param driver
	 * @return
	 * @Description:更新分供方
	 */
	@SuppressWarnings({ "unused", "static-access" })
	public Result updateShipper2Crm_d(E_join_driver driver){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		log.info("构建分供方更新信息");
		Shipper shipper=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId("1");
		customerInfo.setCode("1");
		customerInfo.setName("1");
		customerInfo.setType("1");
		customerInfo.setIdType("1");
		customerInfo.setIdNumber("1");
		customerInfo.setProvince("1");
		customerInfo.setCity("1");
		customerInfo.setAddress("1");
		customerInfo.setLinkman("1");
		customerInfo.setPhone("1");
		customerInfo.setTel("1");
		customerInfo.setOperTime(new Timestamp(0L));
		customerInfo.setOperUser("1");
		customerInfo.setStatus("1");

		shipper.setCustomerId(customerInfo);
		shipper.setName("1");
		shipper.setShipperProp("1");
		shipper.setId("1");
		shipper.setProvince("1");
		shipper.setCity("1");
		shipper.setLinkman("1");
		shipper.setPhone("1");
		shipper.setTel("1");
		shipper.setRegAddress("1");
		shipper.setCapital(0);
		shipper.setHasPermit("1");
		shipper.setTaxNo("1");
		shipper.setBusinessType("1");
		shipper.setCarNumber(0);
		shipper.setOwner("1");
		shipper.setCertStatus("1");
		shipper.setCertValidTime(new Timestamp(0L));
		shipper.setHasInvoice("1");
		shipper.setDesc("1");
		shipper.setCreateTime(new Timestamp(0L));
		shipper.setCreateUser("1");
		shipper.setUpdateTime(new Timestamp(0L));
		shipper.setUpdateUser("1");
		shipper.setCode("1");    
		shipper.setShortName("1");

		customerInfo.setId(driver.getShipperId());

		//后期增加
		String select="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
		Dao<E_audit> dao=new DaoFactory().create(E_audit.class);
		E_audit condition=new E_audit();
		condition.setJoinFlag(0);
		condition.setJoinId(driver.getLineid());
		List<E_audit> results=dao.getSession().selectList(select, condition);
		customerInfo.setCode(results.get(0).getLineid());
		customerInfo.setIdType("1");
		customerInfo.setIdNumber(driver.getCardno());
		customerInfo.setName(driver.getDriverName());
		customerInfo.setLinkman(driver.getDriverName());
		if (driver.getMobileno()!=null) {
			log.info("获取司机的手机号码"+driver.getMobileno());
		}
		else{
			log.info("获取司机的手机号码为空");
		}
		customerInfo.setPhone(driver.getMobileno());
		//后期增加
		customerInfo.setType("1");
		
		shipper.setCustomerId(customerInfo);
		shipper.setName(driver.getDriverName());
		shipper.setTaxNo(driver.getCardno());
		shipper.setBusinessType("1");
		shipper.setCarNumber(0);
		shipper.setHasInvoice("1");
		//后期添加
		shipper.setLinkman("");
		shipper.setCreateTime(driver.getCreateTime());
		shipper.setPhone(driver.getMobileno());
		shipper.setHasPermit("1");
		shipper.setShortName(driver.getDriverName());
		shipper.setShipperProp("0");
		shipper.setOwner(driver.getDriverName());
		log.info("set的城市是"+driver.getCity());
		shipper.setCity(driver.getCity());
		shipper.setProvince(driver.getProvince());
		log.info("set的省份是"+driver.getProvince());
		shipper.setDesc(" ");
		shipper.setLinkman(customerInfo.getLinkman());
		shipper.setCreateUser(driver.getCreateUser());
		Timestamp now=new Timestamp(System.currentTimeMillis());
		shipper.setUpdateTime(now);
		shipper.setUpdateUser(username);
		shipper.setShortName(driver.getDriverName());
		shipper.setTel(driver.getMobileno());
		//后期添加
		if(driver.getCheckFlag()==3){
			shipper.setCertStatus("2");
			String selectByCondition="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
			E_audit audit=new E_audit();
			audit.setJoinFlag(0);
			audit.setJoinId(driver.getLineid().toString());
			Dao<E_audit> audit_dao=new DaoFactory().create(E_audit.class);
			List<E_audit> audits=dao.getSession().selectList(selectByCondition, audit);
			Date endDate=audits.get(0).getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			log.info("要生成的失效日期为："+endString);
			shipper.setCertValidTime(Timestamp.valueOf(endString));
			//后期添加
			shipper.setCode(audits.get(0).getLineid());
			//后期添加
		}
		else{
			shipper.setCertStatus("0");
		}
		log.info("准备调用updateShipper接口");
		return updateShipper(shipper);
	}
	/**
	 * 
	 * @param company
	 * @return
	 * @Description:更新分供方
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public Result updateShipper2Crm_c(E_join_company company){
		Shipper shipper=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId(company.getShipperId());
/*		shipper.setName(company.getCompanyName());
		shipper.setTaxNo(company.getTaxNo());
		shipper.setBusinessType("1");
		shipper.setCarNumber(0);
		shipper.setHasInvoice("1");
		shipper.setLinkman(company.getContractName());
		shipper.setPhone(company.getMobileno());
		if(company.getContract()!=null)
		shipper.setTel(company.getContract());
		shipper.setOwner(company.getLegalUser());
		if(company.getCheckFlag()==3){
			shipper.setCertStatus("1");
			String selectByDriver="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
			E_audit condition=new E_audit();
			condition.setJoinFlag(1);
			condition.setJoinId(company.getLineid().toString());
			Dao<E_audit> dao=new DaoFactory().create(E_audit.class);
			List<E_audit> audits=dao.getSession().selectList(selectByDriver, condition);
			Date endDate=audits.get(0).getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			shipper.setCertValidTime(Timestamp.valueOf(endString));
		}
		else{
			shipper.setCertStatus("0");
		}*/
		customerInfo.setType("2");
		shipper.setCustomerId(customerInfo);
		shipper.setName(company.getCompanyName());
		//后期增加
		String select="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
		Dao<E_audit> audit_dao=new DaoFactory().create(E_audit.class);
		E_audit condition=new E_audit();
		condition.setJoinFlag(1);
		condition.setJoinId(company.getLineid());
		List<E_audit> results=audit_dao.getSession().selectList(select, condition);
		customerInfo.setCode(results.get(0).getLineid());
		customerInfo.setIdType("2");
		customerInfo.setIdNumber(company.getTaxNo());
		customerInfo.setName(company.getCompanyName());
		customerInfo.setLinkman(company.getContractName());
		customerInfo.setPhone(company.getContract());
		customerInfo.setTel(company.getContract());
		//后期增加
		shipper.setTaxNo(company.getTaxNo());
		shipper.setShortName(company.getCompanyName());
		shipper.setBusinessType("1");
		shipper.setCarNumber(0);
		shipper.setHasInvoice("1");
		shipper.setLinkman(company.getContractName());
		shipper.setPhone(company.getMobileno());
		shipper.setLinkman(customerInfo.getLinkman());
		shipper.setHasPermit("0");
		shipper.setCapital(company.getCapital().intValue());
		shipper.setTel("");
		if(company.getContract()!=null)
		shipper.setTel(company.getContract());
		shipper.setOwner(company.getLegalUser());
		log.info("set的城市是"+company.getCity());
		shipper.setCity(company.getCity());
		shipper.setProvince(company.getProvince());
		log.info("set的省份是"+company.getProvince());
		if(company.getCheckFlag()==3){
			shipper.setCertStatus("2");
			String selectByCondition="com.unlcn.ils.tps.E_auditMapper.selectByCondition";
			E_audit audit=new E_audit();
			audit.setJoinFlag(1);
			audit.setJoinId(company.getLineid().toString());
			Dao<E_audit> dao=new DaoFactory().create(E_audit.class);
			List<E_audit> audits=dao.getSession().selectList(selectByCondition, audit);
			Date date=audits.get(0).getEndDate();
			Timestamp timestamp=new Timestamp(date.getTime());
			Date endDate=audits.get(0).getEndDate();
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String endString=df.format(endDate);
			shipper.setCertValidTime(Timestamp.valueOf(endString));
			//后期添加
			shipper.setCode(audits.get(0).getLineid());
		}
		else{
			shipper.setCertStatus("0");
		}
		return updateShipper(shipper);
	}
	/**
	 * 
	 * @return
	 * @Description:根据当前登录用户获取分供方id
	 */
	public String getShipperIdByUser(){
		log.info("正在调用方法getShipperIdByUser");
		AccessControlService accessControlService = ApplicationContextManager.getContext().getBean(AccessControlService.class);
		log.info("已初始化AccessControlService");
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		log.info("已初始化UserService");
		UserDTO userDTO=accessControlService.getUser();
		OrgDTO orgDTO=userService.queryMainOrg(userDTO.getUserId());
		String Id=orgDTO.getRemark1();
		if (Id!=null) {
			log.info("获取的客户id为: "+Id);
		}
		else{
			log.info("获取的客户为空");
		}
		return Id;
	}
}
