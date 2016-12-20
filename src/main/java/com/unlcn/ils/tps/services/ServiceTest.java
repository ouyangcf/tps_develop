package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;


import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.customer.account.Account;
import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.unlcn.ils.crm.customer.subsupplier.Shipper;
import com.unlcn.ils.crm.dto.Result;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.erp.dto.FleetDto;
import com.unlcn.ils.erp.dto.PriceDto;
import com.unlcn.ils.erp.service.VehicleService;

public class ServiceTest {
	//private final static Logger logger= Logger.getLogger(PriceDto.class);
	//private final static Logger logger1= Logger.getLogger(FleetDto.class);
	private static Logger logger=Logger.getLogger(Logger.class);
	@SuppressWarnings("unused")
	public PriceDto getComplaintypesTest(){
		VehicleService vehicleService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		 PriceDto  priceDto=new PriceDto();
			priceDto.setStartCityI("深圳");
			priceDto.setEndCityI("上海");
			priceDto.setCustomerI("比亚迪深圳");
			priceDto.setCargoI("比亚迪S7");
			priceDto.setPayPeriodI("N+1");
			priceDto.setPayWayI("C");   
	        Calendar rightNow = Calendar.getInstance();
	        rightNow.add(Calendar.DAY_OF_YEAR,-600);//日期加10天 
			 priceDto.setDateI(rightNow.getTime());
			 PriceDto dto= vehicleService.queryPrice(priceDto);
		List<Map<String, String>> maps=new ArrayList<Map<String,String>>();
		Map<String, String> map=new HashMap<String, String>();
		map.put("cargoi", dto.getCargoI());
		map.put("CustomerI", dto.getCustomerI());
		map.put("CustomerI", dto.getCustomerI());
		map.put("EndCityI", dto.getEndCityI());
		map.put("StartCityI", dto.getStartCityI());
		map.put("StartCityI", dto.getStartCityI());
		//logger.debug("查询标准价出参"+JSON.toJSONString(dto));
		return dto;
	}

	public List<FleetDto> getComplaintypesTest1(){
		VehicleService vehicleService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		FleetDto  fleetDto=new FleetDto();
		 fleetDto.setVcfleetname("中联南昌队");
		  fleetDto.setStartDate("2014-12-1") ;
		 fleetDto.setEndDate("2014-12-12") ;
		 List<FleetDto> dtos= vehicleService.queryFleetDto(fleetDto);
	//	logger1.debug("查询标准价出参"+JSON.toJSONString(dtos));
		return dtos;
	}
	
	public Map<String, String> getAccounts(){
		logger.info("初始化CrmInformationService前");
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		logger.info("初始化CrmInformationService后");
		if(dbService==null){
			logger.info("dbService为空");
		}
		else{
			logger.info("dbService不为空");
		}
		try {
			Account account=new Account();
			String customid="339";
			CustomerInfo customerInfo=new CustomerInfo();
			customerInfo.setId(customid);
			account.setCustomerId("339");
			List<Account> accounts=dbService.getAccountByid(account);
			if(accounts.size()!=0){
				Map<String, String> map=new HashMap<String, String>();
				map.put("name", accounts.get(0).getName());
				return map;
			}
			else{
				Map<String, String> map=new HashMap<String, String>();
				map.put("name", "none");
				return map;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("调用dbService.getAccountByid(account)发生错误");
			return null;
		}

	}
	
	@SuppressWarnings("unused")
	public boolean updateShipperTest(){
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
/*		Dao<E_join_driver> dao=new DaoFactory().create(E_join_driver.class);
		E_join_driver driver=new E_join_driver();
		driver.setShipperId("3050");
		List<E_join_driver> testDrivers=dao.select(driver);
		Result result=crmInformationInterface.updateShipper2Crm_d(testDrivers.get(0));*/
		Shipper arg0=new Shipper();
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setId("3060");
		arg0.setCustomerId(customerInfo);
		List<Shipper> shipper=dbService.getShipper(arg0);
		logger.info("获取的shipper长度为："+shipper.size());
		logger.info("获取的shipper名称为："+shipper.get(0).getName());
		Result result=dbService.updateShipper(shipper.get(0));
		logger.info("从接口测试可以知道："+result.getRetCode());
		logger.info(result.getErrDesc());
		return false;
	}
	
	public PriceDto priceDtoTest(){
		logger.info("正在初始化PriceDto");
		VehicleService dbService=(VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		if(dbService==null){
			logger.info("初始化后dbService为空");
		}
		else{
			logger.info("初始化后dbService不为空");
		}
		PriceDto condition=new PriceDto();
		condition.setCargoI("58");
		condition.setEndCityI("长沙");
		condition.setStartCityI("深圳");
		PriceDto result=dbService.queryPrice(condition);
		if(result==null){
			logger.info("获取结果为空");
		}
		else{
			logger.info("获取结果不为空");
		}
		return result;
	}
	
	public Result addShipperTest(){
		CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		Shipper shipper=new Shipper();
		shipper.setBusinessType("1");
		shipper.setCapital(1000);
		shipper.setCarNumber(0);
		shipper.setCertStatus("2");
		shipper.setCity("");
		shipper.setCode("a546");
		shipper.setCreateUser("aaa");
		Timestamp createTime=new Timestamp(System.currentTimeMillis());
		shipper.setCreateTime(createTime);
		shipper.setDesc("xxx");
		shipper.setHasInvoice("1");
		shipper.setHasPermit("1");
		shipper.setLinkman("xxxx");
		shipper.setName("认证状态测试0909");
		shipper.setShortName("认证状态测试0909");
		shipper.setOwner("xxxx");
		shipper.setPhone("13870883851");
		shipper.setProvince("");
		shipper.setRegAddress("");
		shipper.setShipperProp("xxx");
		shipper.setTaxNo("xxxx");
		shipper.setTel("6216185");
		CustomerInfo customerInfo=new CustomerInfo();
		customerInfo.setAddress("xxxx");
		customerInfo.setCity("");
		customerInfo.setCode("a5646");
		customerInfo.setIdNumber("444");
		customerInfo.setIdType("1");
		customerInfo.setLinkman("xxxx");
		customerInfo.setName("xxxxyyyy");
		customerInfo.setOperTime(createTime);
		customerInfo.setStatus("1");
		customerInfo.setPhone("6216185");
		customerInfo.setProvince("");
		customerInfo.setTel("138270833851");
		customerInfo.setType("1");
		shipper.setCustomerId(customerInfo);
		return dbService.addShipper(shipper, null, null);
	}
	
}
