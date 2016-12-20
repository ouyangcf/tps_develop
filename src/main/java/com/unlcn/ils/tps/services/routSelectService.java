package com.unlcn.ils.tps.services;

import java.util.ArrayList;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_intentline;
import com.unlcn.ils.tps.ininterface.ShipperInterface;

import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:线路选择服务  
 *@Author:Administrator
 *@Since:2016-8-19
 *@Version:1.1.0
 */
public class routSelectService implements ArrayContentProvider {
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_join_intentline> dao = DaoFactory.create(E_join_intentline.class) ;
		Dao<E_join_driver> driveDao = DaoFactory.create(E_join_driver.class);
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_join_intentline> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		
		List<E_join_intentline> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		String selectByJoinIdFullPath="com.unlcn.ils.tps.E_auditMapper.selectByJoinId";
		
			for(int i=0;i<lists.size();i++){
				//E_join_driver tempLine=lists.get(i);
				E_join_intentline e_temp=lists.get(i);
				Map<String, String> map=new HashMap<String, String>();					
				if(e_temp.getJoinFlag()==0){//个人
					map.put("lineId", e_temp.getLineid().toString());
					map.put("joinId", e_temp.getJoinId().toString());
					map.put("startCity", e_temp.getStartCity());
					map.put("destProvince", e_temp.getDestProvince());
					map.put("price", e_temp.getPrice().toString());
					map.put("qty", e_temp.getQty().toString());
					E_join_driver d=driveDao.selectByID(e_temp.getJoinId());
					map.put("lineid", d.getLineid().toString());
					map.put("drivername", d.getDriverName());
					map.put("licenseno", d.getLicenseno());
					map.put("vehiclelicenseno", d.getVehicleLicenseno());
					map.put("cardno", d.getCardno());
					map.put("mobileno", d.getMobileno());
					map.put("province", d.getProvince());
					map.put("cityid", d.getCityId().toString());
					map.put("city", d.getCity());
					//map.put("checkflag", d.getCheckFlag().toString());
					map.put("createtime", d.getCreateTime().toString());
				//}
				List<E_audit>  audits=auditDao.getSession().selectList(selectByJoinIdFullPath, lists.get(i).getJoinId());
				if(audits.size()!=0){
					E_audit e_audit=audits.get(0);
						map.put("audituser",e_audit.getAuditUser());
						if(e_audit.getAuditTime()!=null)
						map.put("audittime", e_audit.getAuditTime().toString());
						map.put("auditflag", e_audit.getAuditFlag().toString());
						map.put("auditmemo", e_audit.getAuditMemo());
						
					
							
				}
					updateList.add(map);	
			
			
			}
				
		}	
		
	Page<Map<String,String>> result1=new Page<Map<String,String>>();
	result1.setPageIndex(result.getPageIndex());
	result1.setPageSize(result.getPageSize());
	result1.setTotal(result.getTotal());
	result1.setContents(updateList);
	return result1;
	}
	/**
	 * 
	 * @param shipperid
	 * @return
	 * @Description:根据分供方编号获取
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public List<String> getByShipperid(Integer shipperid){

		String selectByItemLineidFullPath="com.unlcn.ils.tps.e_shipper";
		ShipperInterface shipper=new ShipperInterface();
		Map<String,String> shipperMap=shipper.getShipperByID(shipperid.toString());
		String shippername=shipperMap.get("shipname");
		List<String> idList=new ArrayList<String>();
		idList.add(shippername);
		return idList;
		}
}
