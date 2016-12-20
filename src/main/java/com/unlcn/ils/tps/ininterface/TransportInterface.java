package com.unlcn.ils.tps.ininterface;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.erp.dto.FleetDto;
import com.unlcn.ils.erp.service.VehicleService;
  
public class TransportInterface {      
	/**
	 * 
	 * @param fleetDto
	 * @return
	 * @Description:从接口获取历史承运信息
	 */
	public List<FleetDto> queryFleetDto(FleetDto fleetDto){
		
		/*List<FleetDto> returnList=new ArrayList<FleetDto>();
		FleetDto dto=new FleetDto();
		dto.setVcfleetname("分供方0");
		dto.setVcendcity("吉安");
		returnList.add(dto);*/
		VehicleService vehicleService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		List<FleetDto> returnList=vehicleService.queryFleetDto(fleetDto);
		return returnList;
	}
	/**
	 * 
	 * @param fleetDto
	 * @return
	 * @Description:将从接口得到的历史承运信息输出
	 */
	public List<Map<String, String>> getTransByidFromCrm(FleetDto fleetDto){
		List<FleetDto> lists=queryFleetDto(fleetDto);
		List<Map<String,String>> returnlist=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++)
		{
			Map<String,String> map=new HashMap<String,String> ();
			FleetDto translist = lists.get(i);
			if(translist.getVcendcity()!=null)
			map.put("endcity", translist.getVcendcity());
			if( translist.getVcstartcity()!=null)
			map.put("startcity", translist.getVcstartcity());
			if(translist.getVcendprovince()!=null)
			map.put("endprovince", translist.getVcendprovince());
			map.put("number", translist.getDctotalship()+"");
			if(translist.getVcmonthStatic()!=null)
			map.put("date", translist.getVcmonthStatic()+"");
			returnlist.add(map);
	}
		return returnlist;
	}

}
