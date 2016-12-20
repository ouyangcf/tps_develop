package com.unlcn.ils.tps.services;

import java.util.Map; 

import com.unlcn.ils.tps.ininterface.PriceInterface;
/**
 * 
 *@Title:
 *@Description:
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class StandardPriceService {
	//根据车型id，起运地，目的地查询相应的标准价格
		public Map<String,String> getData(String startcityname,
				String destcityname,String styleid)
		{
			
			return PriceInterface.getData(styleid, startcityname, destcityname);
			
		}
		
}
