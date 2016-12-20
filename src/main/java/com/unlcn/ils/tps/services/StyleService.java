package com.unlcn.ils.tps.services;

import java.util.List; 
import java.util.Map;

import com.unlcn.ils.tps.ininterface.StyleInterface;
/**
 * 
 *@Title:
 *@Description:货物型号服务
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class StyleService {
	/**
	 * 
	 * @param customerid
	 * @return
	 * @Description:根据客户id，返回货物型号记录
	 */
	public List<Map<String,String>> getDataByCustomerId(String customerid)
	{
		return StyleInterface.getDataByCustomer(customerid);
	}
	
	public Map<String,String> getStyleById(String styleid)
	{
		Map<String,String> style = StyleInterface.getStyleByStyleID(styleid);	
		return style;
		
	} 
}
