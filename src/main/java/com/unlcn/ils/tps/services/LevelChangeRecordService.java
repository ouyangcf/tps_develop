package com.unlcn.ils.tps.services;

import java.util.ArrayList;  
import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_levelchange;
import com.unlcn.ils.tps.E_line;
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
 *@Description:级别调整记录自定义数据源 
 *@Author:johnn
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class LevelChangeRecordService implements ArrayContentProvider{
	private static Logger logger=Logger.getLogger(Logger.class);
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_levelchange> dao = DaoFactory.create(E_levelchange.class) ;
		Dao<E_line> lineDao = DaoFactory.create(E_line.class) ;
		 Dao<E_currentlevel> leveldao = DaoFactory.create(E_currentlevel.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_levelchange> result=dao.selectPageByCondition(conditions,
				condition,context.getPageable() ,context.getSortable(), true);
		List<E_levelchange> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		logger.debug("获取数据源长度为"+lists.size());
		 
			for(int i=0;i<lists.size();i++)
			{
				E_levelchange temp=lists.get(i);
				E_currentlevel templevel=leveldao.selectByID(temp.getLeveltableLineid());
				E_line templine=lineDao.selectByID(templevel.getStartCityid());
				Map<String, String> map=new HashMap<String, String>();
					map.put("shipper_lineid", temp.getShipperLineid().toString());
					map.put("shipper_name", templevel.getShipperName());
					map.put("code",getShipperCodeById(temp.getShipperLineid()));
					map.put("startLineID", templine.getLineid().toString());
					map.put("startCity", templine.getStartCity());
					map.put("destProvince", templine.getDestProvince());
					map.put("level_lineid", temp.getPrelevelid().toString());
					map.put("level_name", temp.getPrelevelname());
					map.put("afterLevelName", temp.getAfterlevelname());
					map.put("approveStatus",temp.getApproveStatus().toString());
					if(temp.getApproveTime()!=null)
					map.put("approveTime",temp.getApproveTime().toString());
					map.put("approveUsername", temp.getApproveUsername());
					map.put("approveMemo", temp.getApproveMemo());
					map.put("resonchangeName", temp.getResonchangeName());
					map.put("otherReason",temp.getChangeOtherreson());
					map.put("createUsername", temp.getCreateUsername());
					map.put("createTime", temp.getCreateTime().toString());
					if(temp.getStrartDate()!=null)
					map.put("startDate", temp.getStrartDate().toString());
					if(temp.getEndDate()!=null)
					map.put("endDate", temp.getEndDate().toString());
					map.put("levelchangeLineId", temp.getLineid().toString());
					updateList.add(map);
			}		
			logger.debug("准备返回数据源");
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
	 * @Description:根据shipperid获取code
	 */
	@SuppressWarnings("unused")
	private String getShipperCodeById(String shipperid){
		ShipperInterface ss=new ShipperInterface();
		Map<String,String> map=ShipperInterface.getShipperByID(shipperid);
		return map.get("code");
	}

}
