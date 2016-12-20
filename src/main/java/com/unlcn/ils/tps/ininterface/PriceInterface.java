package com.unlcn.ils.tps.ininterface;
  
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.erp.dto.PriceDto;
import com.unlcn.ils.erp.service.VehicleService;

public class PriceInterface { 
	private static Logger logger=Logger.getLogger(Logger.class);
	static VehicleService dbService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
	public static Map<String,String> getData(String styleid,String startcityname,
			String destcityname)
	{     
		//接口数据不源方式
		String datasouce = ConfigManager.getInstance().getConfig("datasouce");
		if (!datasouce.equals("local"))
		{ 
			PriceDto price = new PriceDto(); 
			price.setStartCityI(startcityname);
			price.setEndCityI(destcityname);
			price.setCargoI(styleid);
			PriceDto data= dbService.queryPrice(price);
			if(data==null){
				logger.info("获取价格的实体为空");
			}
			else{
				logger.info("获取价格的实体不为空");			
			}
			Map<String,String> map=new HashMap<String,String> ();
			if (data==null) {
				map.put("price", "0");
				map.put("kilometer", "0");
			}
			else{
				logger.info("获取的公里数"+data.getKmO());
				map.put("price", data.getPriceO());
				map.put("kilometer", data.getKmO());				
			}

			return map;
		}
		else
		{
			Map<String,String> map=new HashMap<String,String> ();
			map.put("price","1.2");
			map.put("kilometer", "1000");
			return map;
		}
	}
}
