package com.unlcn.ils.tps.services;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_levelchange;
import com.unlcn.ils.tps.E_line;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/** 
 * 
 *@Title:
 *@Description:级别审核自定义数据源 
 *@Author:johnn
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class LevelCheckSelectService implements ArrayContentProvider{

/**
 * 根据shipperid获取code
 * @param shipperid
 * @return
 */
	private String getShipperCodeById(String shipperid){
		//ShipperInterface ss=new ShipperInterface();
		//Map<String,String> map=ShipperInterface.getShipperByID(shipperid.toString());
		CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
		Map<String, String> map=crmInformationInterface.getShipperFromCrmById(shipperid.toString());
		return map.get("code");
	}
	
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		Dao<E_currentlevel> dao = DaoFactory.create(E_currentlevel.class) ;
		Dao<E_levelchange> levelchangeDao = DaoFactory.create(E_levelchange.class) ;
		Dao<E_line> lineDao = DaoFactory.create(E_line.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_currentlevel> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_currentlevel> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
		for(int i=0;i<lists.size();i++){
			E_currentlevel e_currentlevel=lists.get(i);
			//if(e_currentlevel.getActive()!=0){
			Map<String, String> map=new HashMap<String, String>();
			map.put("lineid", e_currentlevel.getLineid().toString());
			E_line e_line=lineDao.selectByID(e_currentlevel.getStartCityid());
			List<E_levelchange> levelchanges=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,e_currentlevel.getLineid());
			//if(levelchanges.size()!=0&&e_currentlevel.getActive()!=1)
			if(levelchanges.size()!=0)
			{		int pos=levelchanges.size()-1;
					map.put("level_name", e_currentlevel.getLevelName());
					if(levelchanges.get(pos).getApproveStatus()!=3)
					map.put("afterLevelName", levelchanges.get(pos).getAfterlevelname());
					map.put("approveStatus",levelchanges.get(pos).getApproveStatus().toString());
					map.put("approveUsername", levelchanges.get(pos).getApproveUsername());
					if(levelchanges.get(pos).getApproveMemo()!=null)
					map.put("approveMemo", levelchanges.get(pos).getApproveMemo());
					map.put("resonchangeName", levelchanges.get(pos).getResonchangeName());
					map.put("createUsername", levelchanges.get(pos).getCreateUsername());
					map.put("createTime", levelchanges.get(pos).getCreateTime()+"");
					if(levelchanges.get(pos).getApproveTime()!=null)
					map.put("approveTime", levelchanges.get(pos).getApproveTime().toString());	
					if(levelchanges.get(pos).getStrartDate()!=null)
					map.put("startDate", levelchanges.get(pos).getStrartDate().toString());
					if(levelchanges.get(pos).getEndDate()!=null)
					map.put("endDate", levelchanges.get(pos).getEndDate().toString());
					map.put("otherReason",levelchanges.get(pos).getChangeOtherreson());
					map.put("levelchangeLineId", levelchanges.get(pos).getLineid().toString());
					map.put("levelTime", e_currentlevel.getCreateTime().toString());
					map.put("shipper_lineid", e_currentlevel.getShipperLineid().toString());
					map.put("shipper_name", e_currentlevel.getShipperName());
					map.put("code", getShipperCodeById(e_currentlevel.getShipperLineid()));
					map.put("level_lineid", e_currentlevel.getLevelLineid().toString());
					map.put("startCity", e_line.getStartCity());
					map.put("destProvince", e_line.getDestProvince());
			}
			else{
	
				map.put("approveStatus",0+"");
				map.put("create_username", e_currentlevel.getCreateUsername());
				map.put("levelTime", e_currentlevel.getCreateTime().toString());
				map.put("createTime", "");
				map.put("lineid", e_currentlevel.getLineid().toString());
				map.put("shipper_lineid", e_currentlevel.getShipperLineid().toString());
				map.put("shipper_name", e_currentlevel.getShipperName());
				map.put("code", getShipperCodeById(e_currentlevel.getShipperLineid()));
				map.put("level_lineid", e_currentlevel.getLevelLineid().toString());
				map.put("level_name", e_currentlevel.getLevelName());
				map.put("startCity", e_line.getStartCity());	
				map.put("destProvince", e_line.getDestProvince());
			}
			updateList.add(map);
	//	}
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;	
	}
}
