package com.unlcn.ils.tps.services;

import java.util.List; 
import java.util.Map;

import com.unlcn.ils.tps.service.impl.TpsServiceImpl;
import com.unlcn.ils.tps.shipper.CheckDetail;
import com.unlcn.ils.tps.shipper.CheckInfo;
import com.unlcn.ils.tps.shipper.Route;
/**
 * 
 *@Title:
 *@Description:tps方提供接口服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ImplementsTestService {
	public List<CheckInfo> getCheckInfos(){
		return new TpsServiceImpl().queryCheck("10","2016-08-01","2016-08-31");
	}
	
	public List<CheckDetail> getCheckDetails(){
		return new TpsServiceImpl().queryCheckDetail("10");
	}
	
	public List<Route> getqueryRoute(){
		return new TpsServiceImpl().queryRoute();
	}
	
	@SuppressWarnings("rawtypes")
	public Map getChanges(){
		return new TpsServiceImpl().queryShipperLevel("10", "PKI8qLA1RJO1HjB6j5wICQ", "2016-08-01","2016-08-31");
	} 
}
