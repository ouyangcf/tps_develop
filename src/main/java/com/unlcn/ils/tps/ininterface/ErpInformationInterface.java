package com.unlcn.ils.tps.ininterface;
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.unlcn.ils.tps.E_temp_return_status_order;
import com.unlcn.ils.tps.E_temp_vehicle_come_queue;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.erp.dto.CargoDto;
import com.unlcn.ils.erp.dto.ReturnStatusOrderDto;
import com.unlcn.ils.erp.dto.VehicleComeQueueDto;
import com.unlcn.ils.erp.service.VehicleService;
import com.unlcn.sso.service.UserOrgService;
/**
 * 
 *@Title:
 *@Description:erm接口
 *@Author:Administrator 
 *@Since:2016-8-18
 *@Version:1.1.0  
 */  
public class ErpInformationInterface {   
	private static  final Logger log = Logger.getLogger(Logger .class);
	/**
	 * 
	 * @param returnStatusOrderDto
	 * @return
	 * @Description:回单明细
	 */
	private List<ReturnStatusOrderDto> queryReturnStatusOrder(ReturnStatusOrderDto returnStatusOrderDto){ 
		//本地调试
/*		List<ReturnStatusOrderDto> infos=new ArrayList<ReturnStatusOrderDto>();
		ReturnStatusOrderDto dto=new ReturnStatusOrderDto();
		dto.setIfleetid2(1);
		dto.setOrderdate(new Date());
		dto.setShipdate(new Date());
		dto.setDtoutdate(new Date());
		dto.setDtcomedate(new Date());
		dto.setVcstyleno("雪佛兰");
		dto.setVcstartcityname("江陵");
		dto.setVcendcityname("南宁");
		dto.setVcprovince("浙江");
		dto.setVcstartcity("南昌");
		dto.setVccityname("南昌");
		dto.setVcshipno("L15646-x");
		dto.setVccustorderno("L15646-x");
		dto.setReturnoutdate("2015-5");
		dto.setReturntimeout("200");
		dto.setNoreturnreason("xx");
		dto.setBreturn("1");
		dto.setDtreturndate(new Date());
		dto.setVcfleetname("xxx");
		dto.setStartDate("xxx");
		dto.setEndDate("xxxx");
		infos.add(dto);
		infos.add(dto);*/
		//正式调用
		log.info("初始化VehicleService之前");
		VehicleService dbService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		log.info("初始化VehicleService之后");
		if(dbService==null){
			log.info("dbService为空");
		}
		else{
			log.info("dbService不为空");
		}
		return dbService.queryReturnStatusOrder(returnStatusOrderDto);
	}
	/**
	 * 
	 * @param vehicleComeQueueDto
	 * @return
	 * @Description:报班明细
	 */
	private List<VehicleComeQueueDto> queryVehicleComeQueue(VehicleComeQueueDto vehicleComeQueueDto){
		//本地调试
/*		List<VehicleComeQueueDto> infos=new ArrayList<VehicleComeQueueDto>();
		VehicleComeQueueDto dto=new VehicleComeQueueDto();
		dto.setIfleetid(1);
		dto.setVclicense("L156435-x");
		dto.setDtdate(new Date());
		dto.setVccomecityname("xxx");
		dto.setDtrecomedate(new Date());
		dto.setVclocation("xxx");
		dto.setDtComeDate(new Date());
		dto.setIstatus("1");
		dto.setStartDate("2015-1");
		dto.setEndDate("2015-5");
		infos.add(dto);
		infos.add(dto);*/
		//正式调用
		log.info("初始化VehicleService之前");
		VehicleService dbService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		log.info("初始化VehicleService之后");
		if(dbService==null){
			log.info("dbService为空");
		}
		else{
			log.info("dbService不为空");
		}
		return dbService.queryVehicleComeQueue(vehicleComeQueueDto);
	}
	@SuppressWarnings({ "unused", "static-access" })
	public List<Map<String, String>> queryReturnStatusOrderFromErp(ReturnStatusOrderDto returnStatusOrderDto){
		List<Map<String, String>> infos=new ArrayList<Map<String, String>>();
		List<ReturnStatusOrderDto> dtos=queryReturnStatusOrder(returnStatusOrderDto);
		Dao<E_temp_return_status_order> dao=new DaoFactory().create(E_temp_return_status_order.class);
		List<E_temp_return_status_order> insertList=new ArrayList<E_temp_return_status_order>();
		//dao.deleteBatch(dao.selectAll());
		log.info("获取的ReturnStatusOrderDto长度为:"+dtos.size());
		for(int i=0;i<dtos.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_temp_return_status_order return_status_order=new E_temp_return_status_order();
			if(dtos.get(i).getIfleetid2()!=null){
				map.put("ifleetid2",dtos.get(i).getIfleetid2().toString());
				return_status_order.setIfleetid2(dtos.get(i).getIfleetid2());
			}
			if(dtos.get(i).getOrderdate()!=null){
				map.put("orderdate",dtos.get(i).getOrderdate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getOrderdate().getTime());
				return_status_order.setOrderdate(date);
			}				
			if(dtos.get(i).getShipdate()!=null){
				map.put("shipdate",dtos.get(i).getShipdate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getShipdate().getTime());
				return_status_order.setShipdate(date);
			}
			if(dtos.get(i).getDtoutdate()!=null){
				map.put("dtoutdate",dtos.get(i).getDtoutdate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtoutdate().getTime());
				return_status_order.setDtoutdate(date);
			}
			if(dtos.get(i).getDtcomedate()!=null){
				map.put("dtcomedate",dtos.get(i).getDtcomedate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtcomedate().getTime());
				return_status_order.setDtcomedate(date);
			}
			if(dtos.get(i).getVcstyleno()!=null){
				map.put("vcstyleno",dtos.get(i).getVcstyleno());
				return_status_order.setVcstyleno(dtos.get(i).getVcstyleno());
			}
			if(dtos.get(i).getVcstartcityname()!=null){
				map.put("vcstartcityname",dtos.get(i).getVcstartcityname());
				return_status_order.setVcstartcityname(dtos.get(i).getVcstartcityname());
			}
			if(dtos.get(i).getVcendcityname()!=null){
				map.put("vcendcityname",dtos.get(i).getVcendcityname());
				return_status_order.setVcendcityname(dtos.get(i).getVcendcityname());
			}
			if(dtos.get(i).getVcprovince()!=null){
				map.put("vcprovince",dtos.get(i).getVcprovince());
				return_status_order.setVcprovince(dtos.get(i).getVcprovince());
			}
			if(dtos.get(i).getVcstartcity()!=null){
				map.put("vcstartcity",dtos.get(i).getVcstartcity());
				return_status_order.setVcstartcity(dtos.get(i).getVcstartcity());
			}
			if(dtos.get(i).getVccityname()!=null){
				map.put("vccityname",dtos.get(i).getVccityname());
				return_status_order.setVccityname(dtos.get(i).getVccityname());
			}
			if(dtos.get(i).getVcshipno()!=null){
				map.put("vcshipno",dtos.get(i).getVcshipno());
				return_status_order.setVcshipno(dtos.get(i).getVcshipno());
			}
			if(dtos.get(i).getVccustorderno()!=null){
				map.put("vccustorderno",dtos.get(i).getVccustorderno());
				return_status_order.setVccustorderno(dtos.get(i).getVccustorderno());
			}
			if(dtos.get(i).getReturnoutdate()!=null){
				map.put("returnoutdate",dtos.get(i).getReturnoutdate());
				return_status_order.setReturnoutdate(dtos.get(i).getReturnoutdate());
			}
			if(dtos.get(i).getReturntimeout()!=null){
				map.put("returntimeout",dtos.get(i).getReturntimeout());
				return_status_order.setReturntimeout(dtos.get(i).getReturntimeout());
			}
			if(dtos.get(i).getNoreturnreason()!=null){
				map.put("noreturnreason",dtos.get(i).getNoreturnreason());
				return_status_order.setNoreturnreason(dtos.get(i).getNoreturnreason());
			}
			if(dtos.get(i).getBreturn()!=null){
				if(dtos.get(i).getBreturn().equals("0")){
					map.put("breturn","未回");
					return_status_order.setBreturn("未回");
				}
				else{
					map.put("breturn","已回");
					return_status_order.setBreturn("已回");
				}
			}
			if(dtos.get(i).getDtreturndate()!=null){
				map.put("dtreturndate",dtos.get(i).getDtreturndate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtreturndate().getTime());
				return_status_order.setDtreturndate(date);
			}
			if(dtos.get(i).getVcfleetname()!=null){
				map.put("vcfleetname",dtos.get(i).getVcfleetname());
				return_status_order.setVcfleetname(dtos.get(i).getVcfleetname());
			}
			if(dtos.get(i).getStartDate()!=null)
				map.put("startDate",dtos.get(i).getStartDate());
			if(dtos.get(i).getEndDate()!=null)
				map.put("endDate",dtos.get(i).getEndDate());
			infos.add(map);
			insertList.add(return_status_order);
		}
		//dao.insertBatch(insertList);
		Map<String, String> map=new HashMap<String, String>();
		return infos;
	}
	@SuppressWarnings({ "unused", "static-access" })
	public List<Map<String, String>> queryVehicleComeQueueFromErp(VehicleComeQueueDto vehicleComeQueueDto){
		List<Map<String, String>> infos=new ArrayList<Map<String, String>>();//
		List<VehicleComeQueueDto> dtos=queryVehicleComeQueue(vehicleComeQueueDto);
		List<E_temp_vehicle_come_queue> insertList=new ArrayList<E_temp_vehicle_come_queue>();
		Dao<E_temp_vehicle_come_queue> dao=new DaoFactory().create(E_temp_vehicle_come_queue.class);
		//dao.deleteBatch(dao.selectAll());
		log.info("获取的VehicleComeQueueDto长度为:"+dtos.size());
		for(int i=0;i<dtos.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_temp_vehicle_come_queue comeQueueDto=new E_temp_vehicle_come_queue();
			if(dtos.get(i).getIfleetid()!=null){
				map.put("ifleetid",dtos.get(i).getIfleetid().toString());
				comeQueueDto.setIfleetid(dtos.get(i).getIfleetid());
			}
			if(dtos.get(i).getVclicense()!=null){
				map.put("vclicense",dtos.get(i).getVclicense());
				comeQueueDto.setVclicense(dtos.get(i).getVclicense());
			}
			if(dtos.get(i).getDtdate()!=null){
				map.put("dtdate",dtos.get(i).getDtdate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtdate().getTime());
				comeQueueDto.setDtdate(date);
			}
			if(dtos.get(i).getVccomecityname()!=null){
				map.put("vccomecityname",dtos.get(i).getVccomecityname());
				comeQueueDto.setVccomecityname(dtos.get(i).getVccomecityname());
			}
			if(dtos.get(i).getDtrecomedate()!=null){
				map.put("dtrecomedate",dtos.get(i).getDtrecomedate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtrecomedate().getTime());
				comeQueueDto.setDtrecomedate(date);
			}
			if(dtos.get(i).getVclocation()!=null){
				map.put("vclocation",dtos.get(i).getVclocation());
				comeQueueDto.setVclocation(dtos.get(i).getVclocation());
			}
			if(dtos.get(i).getDtComeDate()!=null){
				map.put("dtComeDate",dtos.get(i).getDtComeDate().toString());
				java.sql.Date date=new java.sql.Date(dtos.get(i).getDtComeDate().getTime());
				comeQueueDto.setDtcomedate(date);
			}
			if(dtos.get(i).getIstatus()!=null){
				if(dtos.get(i).getIstatus().equals("0")){
					map.put("istatus","未调度");
					comeQueueDto.setIstatus("未调度");
				}
				else {
					map.put("istatus","已调度");
					comeQueueDto.setIstatus("已调度");
				}
			}
			if(dtos.get(i).getStartDate()!=null)
				map.put("startDate",dtos.get(i).getStartDate());
			if(dtos.get(i).getEndDate()!=null)
				map.put("endDate",dtos.get(i).getEndDate());
			infos.add(map);
			insertList.add(comeQueueDto);
		}
		//dao.insertBatch(insertList);
		return infos;
	}
	
	@SuppressWarnings("unused")
	public List<CargoDto> getCargo(){
		log.info("初始化VehicleService之前");
		VehicleService dbService = (VehicleService)ApplicationContextManager.getContext().getBean("vehicleService");
		UserOrgService ss = (UserOrgService)ApplicationContextManager.getContext().getBean("userOrgService");
		log.info("初始化VehicleService之后");
		if(dbService==null){
			log.info("dbService为空");
		}
		else{
			log.info("dbService不为空");
		}
		CargoDto condition=new CargoDto(); 
		//condition.setCustomerId("1");
		log.info("set到cargodto里面的是"+condition.getCustomerId());
		List<CargoDto> cargoDtos=dbService.queryVehicle(condition);
		if(cargoDtos==null)
		log.info("调用后获取的数据集合为空");
		else{
			log.info("调用后获取的数据集合不为空");
			log.info("集合长度为："+cargoDtos.size());
		}
		return cargoDtos;
	}
}
