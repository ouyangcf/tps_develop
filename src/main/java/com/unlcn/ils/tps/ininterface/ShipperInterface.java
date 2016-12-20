package com.unlcn.ils.tps.ininterface;
  
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.unlcn.ils.tps.E_province;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Rule;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.unlcn.ils.crm.customer.subsupplier.Shipper;
import com.unlcn.ils.crm.dto.Result;
import com.unlcn.ils.crm.service.CrmInformationService;
import org.apache.log4j.Logger;

public class ShipperInterface   

{
	//static CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
	private static  final Logger log = Logger.getLogger(Logger .class);
	public ShipperInterface()
	{ 
		//dbService = 		
	} 
	//转换map
	@SuppressWarnings("unused")
	private static Map<String,String> returnShipper(Shipper shipper)
	{
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		Map<String,String> map = new HashMap<String,String> ();
		log.info("名字为"+shipper.getName()+"的开始转换");
		if (shipper.getName().equals("冯锡范大水果")) {
			log.info("我是"+shipper.getName());
		}
/*		if ( shipper.getCustomerId() ==null) return null;
		map.put("id", shipper.getCustomerId().getId().toString());
		if(shipper.getCode()!=null)
		map.put("code", shipper.getCode().toString());
		if(shipper.getName()!=null)
		map.put("name", shipper.getName());
		if(shipper.getLinkman()!=null)
		map.put("linkman", shipper.getLinkman());
		if(shipper.getPhone()!=null)
		map.put("phone", shipper.getPhone());
		if(shipper.getTel()!=null)
		map.put("tel", shipper.getTel());
		if(shipper.getHasPermit()!=null)
		map.put("hasPermit", shipper.getHasPermit());
		if(shipper.getShortName()!=null)
		map.put("shortName", shipper.getShortName());
		if(shipper.getTaxNo()!=null)
		map.put("taxNo", shipper.getTaxNo());
		if(shipper.getBusinessType()!=null)
		map.put("businessType", shipper.getBusinessType());
		if(shipper.getCarNumber()!=null)
		map.put("carNumber", shipper.getCarNumber().toString());
		if(shipper.getOwner()!=null)
		map.put("owner", shipper.getOwner());
		if(shipper.getCertStatus()!=null)
		map.put("certStatus", shipper.getCertStatus());
		if(shipper.getCertValidTime()!=null)
		map.put("certValidTime", shipper.getCertValidTime().toString());
		if(shipper.getHasInvoice()!=null)
		map.put("hasInovice", shipper.getHasInvoice());
		if(shipper.getDesc()!=null)
		map.put("desc", shipper.getDesc());	*/
		if ( shipper.getCustomerId() ==null) return null;
		map.put("id", shipper.getCustomerId().getId().toString());
		if(shipper.getCustomerId()!=null){
			if (shipper.getCustomerId().getId()!=null) {
				map.put("custominfoId", shipper.getCustomerId().getId());
			}
		}
		log.info("已获取custominfoId");
		if(shipper.getCode()!=null)
		map.put("code", shipper.getCode().toString());
		if(shipper.getName()!=null)
		map.put("name", shipper.getName());
		if(shipper.getLinkman()!=null)
		map.put("linkman", shipper.getLinkman());
		if(shipper.getPhone()!=null)
		map.put("phone", shipper.getPhone());
		if(shipper.getTel()!=null)
		map.put("tel", shipper.getTel());
		if(shipper.getHasPermit()!=null)
		map.put("hasPermit", shipper.getHasPermit());
		if(shipper.getShortName()!=null)
		map.put("shortName", shipper.getShortName());
		if(shipper.getTaxNo()!=null)
		map.put("taxNo", shipper.getTaxNo());
		log.info("完成shipper到map的转换");
		if(shipper.getBusinessType()!=null)
		map.put("businessType", shipper.getBusinessType());
		if(shipper.getCarNumber()!=null)
		map.put("carNumber", shipper.getCarNumber().toString());
		if(shipper.getOwner()!=null)
		map.put("owner", shipper.getOwner());
		if(shipper.getCertStatus()!=null){
			map.put("certStatus", shipper.getCertStatus());
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			//过期
			if (shipper.getCertStatus().equals("0")) {
				map.put("certStatus","认证不通过");
			}
			if (shipper.getCertStatus().equals("1")) {
				map.put("certStatus","认证通过");
				if (shipper.getCertValidTime()!=null) {
					map.put("certValidTime", shipper.getCertValidTime().toString());
				}
				else{
					map.put("certValidTime", "");
				}
			}
			if (shipper.getCertStatus().equals("2")) {
				if(shipper.getCertValidTime()!=null){
					if (shipper.getCertValidTime().before(timestampnow)) {
						map.put("certStatus","认证过期");
						map.put("certValidTime", shipper.getCertValidTime().toString());
					}
					else {
						map.put("certStatus","未认证");
						map.put("certValidTime", shipper.getCertValidTime().toString());
					}
				}
				else{
					map.put("certStatus","未认证");
					map.put("certValidTime", "");
				}
			}
		}
		if(shipper.getHasInvoice()!=null)
		map.put("hasInovice", shipper.getHasInvoice());
		if(shipper.getDesc()!=null)
		map.put("desc", shipper.getDesc());
		if(shipper.getProvince()!=null)
		map.put("province", shipper.getProvince());
		if(shipper.getCity()!=null)
		map.put("city", shipper.getCity());
		if(shipper.getRegAddress()!=null)
		map.put("regAddress", shipper.getRegAddress());
		if(shipper.getCapital()!=null)
		map.put("capital", shipper.getCapital().toString());	
		log.info("名字为"+shipper.getName()+"的结束转换");
		return map;
	}
	
	//根据Rule查询分供方
	@SuppressWarnings({ "static-access", "unused" })
	public static List<Map<String,String>> getDataByRules(Conditions condition)
	{
		Dao<E_province> dao=new DaoFactory().create(E_province.class);
		boolean flag=false;
		int status=-1;
		String shipperName=new String();
		log.info("正在调用getDataByRules函数");
		List<Map<String,String>> listshipper=new ArrayList<Map<String,String>>();
		log.info("初始化类CrmInformationService之前");
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		log.info("初始化类CrmInformationService之后");
		if (dbService!=null) {
			log.info("获取的CrmInformationService实例不为空");
		}
		else{
			log.info("获取的CrmInformationService实例为空");
		}
		Shipper searchshipper=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		searchshipper.setCustomerId(customerInfo);
		log.info("获取Rules之前");
		List<Rule> rules = condition.getRules();
		log.info("进入for循环前");
		for ( int i=0;i<rules.size();i++)
		{//得到查询条件
			Rule rule = rules.get(i);
			String field = rule.getField();
			String data = (String) rule.getData();
			log.info("获取的键值对为"+field+" : "+data);
			String op = rule.getOp();
			if ( field.equals("shippername")){
				if(data==null){
					log.info("获取的shippername为空");
				}
				else{
					log.info("获取的shippername不为空,且名称为"+data);
				}
				//searchshipper.setName(data);
				flag=true;
				shipperName=data;
			}	
			if ( field.equals("cerstatus")){
				if(data==null){
					log.info("获取的cerstatus为空");
				}
				else{
					log.info("获取的cerstatus不为空");
				}
				//认证不通过
				if (data.equals("0")) {
					//认证不通过直接将CertStatus set为0
					searchshipper.setCertStatus("0");	
					log.info("认证状态条件为0");
				}
				//认证通过
				if (data.equals("1")) {
					//认证通过直接将CertStatus set为1
					searchshipper.setCertStatus("1");	
					log.info("认证状态条件为1");
				}
				//未认证
				if (data.equals("2")) {
					//未认证有可能是过期认证的,先将CertStatus set为2,再将得到数据筛选,过期的筛掉
					searchshipper.setCertStatus("2");		
					status=2;
					log.info("认证状态条件为2");
				}	
				//认证过期
				if (data.equals("3")) {
					//未认证有可能是过期认证的,先将CertStatus set为2,再将得到数据筛选,未过期的筛掉
					searchshipper.setCertStatus("2");	
					status=3;
					log.info("认证状态条件为3");
				}				
						
			}
			
			if (field.equals("destProvinceId")) {
				E_province e_province=dao.selectByID(Integer.parseInt(data));
				searchshipper.setProvince(e_province.getProvincename());					
			}
		}
		log.info("进入for循环后");
		log.info("调用CrmInformationService的getShipper函数");
		List<Shipper> objshipper=dbService.getShipper(searchshipper);
		if (flag==true) {
			for(int i=0;i<objshipper.size();i++){
				int index=objshipper.get(i).getName().indexOf(shipperName);
				if(index==-1){
					log.info("相似判断,剔除名字为"+objshipper.get(i).getName()+"的分供方");
					objshipper.remove(i);
					i--;
					
					continue;
				} 
				else{
					log.info("存在类似"+shipperName+"的分供方");
				}
			}
		}
		if (status==2) {
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			for(int i=0;i<objshipper.size();i++){
				//先做过期检测
				Shipper tempShipper=objshipper.get(i);
				if (tempShipper.getCertStatus().equals("2")) {
					log.info("名字为"+tempShipper.getName()+"的状态为"+tempShipper.getCertStatus());
				}
				if(tempShipper.getCertValidTime().before(timestampnow)){
					log.info("名字为"+tempShipper.getName()+"的分供方因为过期被剔除");
					objshipper.remove(i);
					i--;
					continue;
				}
			}
		}
		if (status==3) {
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			for(int i=0;i<objshipper.size();i++){
				//先做过期检测
				Shipper tempShipper=objshipper.get(i);
				if(tempShipper.getCertValidTime().before(timestampnow)){
					log.info("名字为"+tempShipper.getName()+"的分供方因为过期被保留");
					continue;
				}
				else {
					objshipper.remove(i);
					i--;
				}
			}
		}		
		for(int i=0;i<objshipper.size();i++)
		{
			Map<String,String> map=new HashMap<String,String> ();
			Shipper shipper=objshipper.get(i);
			log.info("当前分供方的认证状态为:"+shipper.getCertStatus());
			map = returnShipper(shipper);
			if ( map != null) listshipper.add(map);
		}
		return listshipper;
	}
	public static List<Map<String,String>> getData(Map<String,Object> con)
	{
		List<Map<String,String>> listshipper=new ArrayList<Map<String,String>>();
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		log.info("调用crm的GetShipper之前");
		if(dbService==null){
			log.info("dbService为空");
		}
		else{
			log.info("dbService不为空");
		}
		Shipper shipper_=new Shipper();
		List<Shipper> objshipper=dbService.getShipper(shipper_);
		log.info("调用crm的GetShipper之后");
		log.info("获取到的objshipper长度为:"+objshipper.size());
		//正式调用
		for(int i=0;i<objshipper.size();i++)
		{
			Map<String,String> map=new HashMap<String,String> ();
			//本地调试
			/*E_shipper shipper = objshipper.get(i);*/
			//本地调试
			//正式接口调用
			Shipper shipper=objshipper.get(i);
			//正式接口调用
			map = returnShipper(shipper);		
			if ( map != null) listshipper.add(map);
		}
		return listshipper;

	}
	
	
	public static Map<String,String> getShipperByID(String shipperid)
	{
		Map<String,String> map=new HashMap<String,String>();

		//正式接口调用
		Shipper con=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId(shipperid);
		con.setCustomerId(customerInfo);
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		List<Shipper> shippers=dbService.getShipper(con);
		if(shippers.size()==0)
			return null;

		Shipper shipper=shippers.get(0);
		map = returnShipper(shipper);
		return map;
	}
	//保存分供方认证信息
	public static String saveauth(String shipperid,Timestamp enddate,String authresult)
	{
		//正式调用
		Shipper shipper=new Shipper();
		CustomerInfo customerinfo = new CustomerInfo();
		customerinfo.setId(shipperid);
		shipper.setCustomerId(customerinfo);
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		List<Shipper> shippers=dbService.getShipper(shipper);
		log.info("获取的分供方长度为"+shippers.size());
		Shipper newshipper = shippers.get(0);
		if (newshipper==null) {
			log.info("从接口获取的newshipper为空");
		} 
		else{
			log.info("从接口获取的newshipper不为空");
		}
		log.info("正在确认通过状态");
		if ( authresult.equals("yes") )//认证通过，需要给定起始日期
		{
			newshipper.setCertValidTime(enddate);
			newshipper.setCertStatus("1");
			log.info("状态1");
		}
		else
		{
			newshipper.setCertValidTime(new java.sql.Timestamp(new Date().getTime()));
			newshipper.setCertStatus("0");
			log.info("状态2");
		}
		log.info("准备调用分供方更新接口");
		Result returndata = dbService.updateShipper(newshipper);
		log.info("更新后返回结果状态码"+returndata.getRetCode());
		if ( returndata.getRetCode()!=1)
			return returndata.getErrDesc();
		else return "ok";
	}
	
}
