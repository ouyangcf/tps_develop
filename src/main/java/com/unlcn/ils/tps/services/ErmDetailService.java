package com.unlcn.ils.tps.services;

import java.text.SimpleDateFormat;   
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.ioc.ApplicationContextManager; 
import com.unlcn.ils.crm.complaint.complainCharge.ComplainCharge;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.erp.dto.ReturnStatusOrderDto;
import com.unlcn.ils.erp.dto.VehicleComeQueueDto;
import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_temp_complain_charge;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.unlcn.ils.tps.ininterface.ErpInformationInterface;


/*import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.complaint.complainCharge.ComplainCharge;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.erp.dto.ReturnStatusOrderDto;
import com.unlcn.ils.erp.dto.VehicleComeQueueDto;
import com.unlcn.ils.tps.E_checkdocument;  
import com.unlcn.ils.tps.E_temp_complain_charge;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.unlcn.ils.tps.ininterface.ErpInformationInterface;*/
/**
 * 
 *@Title:
 *@Description:erm明细服务 
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ErmDetailService { 
	private static  final Logger log = Logger.getLogger(Logger .class);
	/**
	 * 
	 * @param lineid
	 * @return
	 * @Description:获取考核明细
	 */
	@SuppressWarnings({ "unused", "static-access", "unchecked", "rawtypes" })
	public Map getComPlainDetails(String lineid){
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		Dao<E_checkdocument> dao=new DaoFactory().create(E_checkdocument.class);
		E_checkdocument checkdocument=dao.selectByID(lineid);
		Map map=new HashMap();
		//日期换成是创建时间
		Date checkDate=checkdocument.getStartDate();
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(checkDate); 
		Integer year=calendar.get(Calendar.YEAR);
		Integer month=calendar.get(Calendar.MONTH);
		GregorianCalendar startDate=new GregorianCalendar(year,month,1);
		GregorianCalendar endDate=new GregorianCalendar(year,month+1,1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String start=df.format(startDate.getTime());
		String end=df.format(endDate.getTime());
		List<Map<String, String>> datas=getdatas(checkdocument.getShipperLineid(), start, end);
		map.put("datas", datas);
		map.put("shippername", checkdocument.getShipperName().trim());
		return map;
	}
	
	@SuppressWarnings("static-access")
	private List<Map<String, String>> getdatas(String shipperId,String startTime,String endTime){
		log.info("初始化CrmInformationService类");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService==null)
			log.info("初始化后dbService为空");
		else
			log.info("初始化后dbService不为空");
		log.info("调用CrmInformationService的getComplainDetail方法前");
		List<ComplainCharge> complain_charges=dbService.getComplainDetail(shipperId, startTime, endTime);
		log.info("调用CrmInformationService的getComplainDetail方法后，获取的ComplainCharge集合长度为："+complain_charges.size());
		List<E_temp_complain_charge> insertList=new ArrayList<E_temp_complain_charge>();
		Dao<E_temp_complain_charge> dao=new DaoFactory().create(E_temp_complain_charge.class);
		dao.deleteBatch(dao.selectAll());
		List<Map<String, String>> list=new ArrayList<Map<String,String>>();
		for(int i=0;i<complain_charges.size();i++){
			E_temp_complain_charge complain_charge=new E_temp_complain_charge();
			Map<String, String> map=new HashMap<String, String>();
			if(complain_charges.get(i).getId()!=null){
				map.put("Lineid", complain_charges.get(i).getId());
				complain_charge.setId(complain_charges.get(i).getId());
			}
			if (complain_charges.get(i).getComplainId()!=null) {
				map.put("complain_id", complain_charges.get(i).getComplainId());
				complain_charge.setComplainid(complain_charges.get(i).getComplainId());
			}
			if(complain_charges.get(i).getCustomerOrder()!=null){
				map.put("customer_order", complain_charges.get(i).getCustomerOrder());
				complain_charge.setCustomerorder(complain_charges.get(i).getCustomerOrder());
			}
			if (complain_charges.get(i).getCategory()!=null) {
				map.put("category", complain_charges.get(i).getCategory());
				complain_charge.setCategory(complain_charges.get(i).getCategory());
			}
			if(complain_charges.get(i).getCategoryId()!=null){
				map.put("category_id", complain_charges.get(i).getCategoryId());
				complain_charge.setCategoryid(complain_charges.get(i).getCategoryId());
			}
			if(complain_charges.get(i).getCategoryName()!=null){
				map.put("category_name", complain_charges.get(i).getCategoryName());
				complain_charge.setCategoryname(complain_charges.get(i).getCategoryName());
			}
			if(complain_charges.get(i).getChargerType()!=null){
				map.put("charger_type", complain_charges.get(i).getChargerType());
				complain_charge.setChargertype(complain_charges.get(i).getChargerType());
			}
			if(complain_charges.get(i).getChargerId()!=null){
				map.put("charger_id", complain_charges.get(i).getChargerId());
				complain_charge.setChargerid(complain_charges.get(i).getChargerId());
			}
			if(complain_charges.get(i).getChargerName()!=null){
				map.put("charger_name", complain_charges.get(i).getChargerName());
				complain_charge.setChargername(complain_charges.get(i).getChargerName());
			}
			if (complain_charges.get(i).getPercent()!=null) {
				map.put("percent", complain_charges.get(i).getPercent().toString());
				complain_charge.setPercent(complain_charges.get(i).getPercent().toString());
			}
			if(complain_charges.get(i).getMemo()!=null){
				map.put("charge_memo", complain_charges.get(i).getMemo());
				complain_charge.setMemo(complain_charges.get(i).getMemo());
			}
			if(complain_charges.get(i).getChargeTime()!=null){
				map.put("charge_time", complain_charges.get(i).getChargeTime().toString());
				complain_charge.setChargetime(complain_charges.get(i).getChargeTime());
			}
			if(complain_charges.get(i).getCreateUser()!=null){
				map.put("create_user", complain_charges.get(i).getCreateUser());
				complain_charge.setCreateuser(complain_charges.get(i).getCreateUser());
			}
			if (complain_charges.get(i).getCreateTime()!=null) {
				map.put("createTime", complain_charges.get(i).getCreateTime().toString());
				complain_charge.setCreatetime(complain_charges.get(i).getCreateTime());
			}
			list.add(map);
			insertList.add(complain_charge);
		}
		dao.insertBatch(insertList);
		return list;
	}
	/**
	 * 
	 * @param lineid
	 * @return
	 * @Description:获取回单明细
	 */
	@SuppressWarnings("static-access")
	public List<Map<String, String>> getReturnStatusOrder(String lineid){
		log.info("获取的lineid为"+lineid);
		ErpInformationInterface erpInformationInterface=new ErpInformationInterface();
		Dao<E_checkdocument> dao=new DaoFactory().create(E_checkdocument.class);
		E_checkdocument checkdocument=dao.selectByID(lineid);
		if (checkdocument!=null) {
			log.info("根据lineid获取的checkdocument不为空");
		}else{
			log.info("根据lineid获取的checkdocument为空");
		}
		Date checkDate=checkdocument.getStartDate();
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(checkDate);
		Integer year=calendar.get(Calendar.YEAR);
		Integer month=calendar.get(Calendar.MONTH);
		GregorianCalendar startDate=new GregorianCalendar(year,month,1);
		GregorianCalendar endDate=new GregorianCalendar(year,month+1,1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String start=df.format(startDate.getTime());
		String end=df.format(endDate.getTime());
		ReturnStatusOrderDto returnStatusOrderDto=new ReturnStatusOrderDto();
		returnStatusOrderDto.setIfleetid2(Integer.parseInt(checkdocument.getShipperLineid()));
		returnStatusOrderDto.setVccustomername(checkdocument.getShipperName());
		returnStatusOrderDto.setStartDate(start);
		returnStatusOrderDto.setEndDate(end);
		return erpInformationInterface.queryReturnStatusOrderFromErp(returnStatusOrderDto);
	}
	/**
	 * 
	 * @param lineid
	 * @return
	 * @Description:报班明细
	 */
	@SuppressWarnings("static-access")
	public List<Map<String, String>> getVehicleComeQueue(String lineid){
		ErpInformationInterface erpInformationInterface=new ErpInformationInterface();
		Dao<E_checkdocument> dao=new DaoFactory().create(E_checkdocument.class);
		E_checkdocument checkdocument=dao.selectByID(lineid);
		Date checkDate=checkdocument.getStartDate();
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(checkDate);
		Integer year=calendar.get(Calendar.YEAR);
		Integer month=calendar.get(Calendar.MONTH);
		GregorianCalendar startDate=new GregorianCalendar(year,month,1);
		GregorianCalendar endDate=new GregorianCalendar(year,month+1,1);
		endDate.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String start=df.format(startDate.getTime());
		String end=df.format(endDate.getTime());
		VehicleComeQueueDto vehicleComeQueueDto=new VehicleComeQueueDto();
		vehicleComeQueueDto.setIfleetid(Integer.parseInt(checkdocument.getShipperLineid()));
		vehicleComeQueueDto.setStartDate(start);
		vehicleComeQueueDto.setEndDate(end);
		return erpInformationInterface.queryVehicleComeQueueFromErp(vehicleComeQueueDto);
	}
}
