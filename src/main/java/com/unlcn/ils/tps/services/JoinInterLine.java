package com.unlcn.ils.tps.services;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_join_company;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_intentline;

import com.unlcn.ils.tps.ininterface.SqlInterface;


import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
 
public class JoinInterLine implements ArrayContentProvider{
	private static Logger logger=Logger.getLogger(Logger.class );
	@Override
	
	/**
	 * @Description：查看意向线路 
	 */
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_join_intentline> dao = DaoFactory.create(E_join_intentline.class); 
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_join_intentline> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_join_intentline> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++){
			E_join_intentline e_temp=lists.get(i);
			Map<String, String> map=new HashMap<String, String>();	
			map.put("lineid", e_temp.getLineid());
			map.put("start_city", e_temp.getStartCity());
			map.put("dest_province", e_temp.getDestProvince());
			if (e_temp.getPrice()!=null) {
				map.put("price", e_temp.getPrice().toString());
			}
			if (e_temp.getQty()!=null) {
				map.put("qty", e_temp.getQty().toString());
			}
			map.put("join_flag", e_temp.getJoinFlag().toString());
			map.put("join_id", e_temp.getJoinId());		
			updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;
	}
	@SuppressWarnings("static-access")
	public E_join_intentline possessInterLine(E_join_intentline data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setCreateUser(username);
		Timestamp timestamp=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestamp);
		Dao<E_join_intentline> dao=new DaoFactory().create(E_join_intentline.class);
		SqlInterface sqlInterface=new SqlInterface();
		String lineid=sqlInterface.getNextVal("SEW_TPS_TPS_JOIN_INTERLINE");
		data.setLineid(lineid);
		dao.insert(data);
		/*String getMaxIdString="com.unlcn.ils.tps.E_join_intentlineMapper.getMaxId";
		List<E_join_intentline> e_join_intentlines=dao.getSession().selectList(getMaxIdString);
		Dao<E_join_driver> driverDao=new DaoFactory().create(E_join_driver.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		if(e_join_intentlines.get(0).getJoinFlag()==0){
			E_join_driver driver=driverDao.selectByID(e_join_intentlines.get(0).getJoinId());
			E_currentlevel currentlevel=new E_currentlevel();
			currentlevel.setShipperLineid(driver.getShipperId());
			currentlevel.setShipperName(driver.getDriverName());
			currentlevel.setStartCityid(e_join_intentlines.get(0).getLineid());
			String getLowestLevel="com.unlcn.ils.tps.E_levelMapper.getMaxId";
			Dao<E_level> levelDao=DaoFactory.create(E_level.class);
			List<E_level> e_levels=levelDao.getSession().selectList(getLowestLevel);
			currentlevel.setLevelLineid(e_levels.get(0).getLineid());
			currentlevel.setLevelName(e_levels.get(0).getLevelName());
			java.sql.Timestamp timestampnow_c=new java.sql.Timestamp(System.currentTimeMillis());
			currentlevel.setCreateTime(timestampnow_c);
			currentlevel.setCreateUsername(username);
			Dao<E_currentlevel> currentDao=DaoFactory.create(E_currentlevel.class);
			currentDao.insert(currentlevel);
		}
		else{
			E_join_company company=companyDao.selectByID(e_join_intentlines.get(0).getJoinId());
			E_currentlevel currentlevel=new E_currentlevel();
			currentlevel.setShipperLineid(company.getShipperId());
			currentlevel.setShipperName(company.getCompanyName());
			currentlevel.setStartCityid(e_join_intentlines.get(0).getLineid());
			String getLowestLevel="com.unlcn.ils.tps.E_levelMapper.getMaxId";
			 Dao<E_level> levelDao=DaoFactory.create(E_level.class);
			List<E_level> e_levels=levelDao.getSession().selectList(getLowestLevel);
			currentlevel.setLevelLineid(e_levels.get(0).getLineid());
			currentlevel.setLevelName(e_levels.get(0).getLevelName());
			java.sql.Timestamp timestampnow_c=new java.sql.Timestamp(System.currentTimeMillis());
			currentlevel.setCreateTime(timestampnow_c);
			currentlevel.setCreateUsername(username);
			Dao<E_currentlevel> currentDao=DaoFactory.create(E_currentlevel.class);
			currentDao.insert(currentlevel);			
		}*/

		return data;
	}
	
	/**
	 * @Description：删除意向线路
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean delInterLine(List<Integer> idList){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_join_intentline> dao = DaoFactory.create(E_join_intentline.class) ;
		for(int i=0;i<idList.size();i++){
			//E_join_intentline e_join_intentline=new E_join_intentline();
			E_join_intentline dels = dao.selectByID(idList.get(i));
			//e_join_intentline.setLineid(idList.toString());
			dao.delete(dels);
		}
		//删除线路以后，该意向线路的等级全部被禁用
		//查找该意向线路所在的当前等级
/*		String selectByInterLineId="com.unlcn.ils.tps.E_currentlevelMapper.selectByRout_LineID";
		Dao<E_currentlevel> currentlevelDao=new DaoFactory().create(E_currentlevel.class);
		Dao<E_levelchange> levelchangeDao=new DaoFactory().create(E_levelchange.class);
		List<E_currentlevel> currentlevels=currentlevelDao.getSession().selectList(selectByInterLineId, id);
		for(int i=0;i<currentlevels.size();i++){
			E_currentlevel currentlevel=currentlevels.get(i);
			currentlevel.setActive(1);
			currentlevel.setActiveMemo("因分供方意向线路被删除而禁用");
			java.sql.Timestamp timestampnow_c=new java.sql.Timestamp(System.currentTimeMillis());
			currentlevel.setActiveTime(timestampnow_c);
			currentlevel.setActiveUser(username);
			currentlevelDao.update(currentlevel);
			String selectByCurrentLevelLineid="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
			List<E_levelchange> levelchanges=levelchangeDao.getSession().selectList(selectByCurrentLevelLineid, currentlevel.getLineid());
			for(int j=0;j<levelchanges.size();j++){
				E_levelchange e_levelchange=levelchanges.get(j);
				e_levelchange.setActive(1);
				e_levelchange.setActiveUser(username);
				e_levelchange.setActiveMemo("因分供方意向线路被删除而禁用");
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_levelchange.setActiveTime(timestampnow);
				levelchangeDao.update(e_levelchange);
			}
		}*/
		return false;
	}
	/**
	 * 
	 * @param shipperid
	 * @return
	 * @Description:根据shipperid获取意向线路
	 */
	public List<Object> selectInterLine(String shipperid){
		/*Dao<E_authentication> dao = DaoFactory.create(E_authentication.class) ;
		String selectByShipperIDFullPath="com.unlcn.ils.tps.E_authenticationMapper.selectByShipperID";
		List<E_authentication> auList=dao.getSession().selectList(selectByShipperIDFullPath,shipperid);
		List<Object> returnList=new ArrayList<Object>();
		if(auList.size()!=0)
		{	
			if(auList.get(0).getJoinFlag()==0||auList.get(0).getJoinFlag()==1)
			returnList.add(auList.get(0).getJoinFlag());
			if(auList.get(0).getJoinId()!=null)
			returnList.add(auList.get(0).getJoinId());
		}
		
		return returnList;
	}*/
		Dao<E_join_company> companyDao=DaoFactory.create(E_join_company.class);
		Dao<E_join_driver> driverDao=DaoFactory.create(E_join_driver.class);
		String selectByComShipperIDFullPath="com.unlcn.ils.tps.E_join_companyMapper.selectByShipperID";
		String selectByDriShipperIDFullPath="com.unlcn.ils.tps.E_join_driverMapper.selectByShipperIDs";
		logger.info("开始了");
		List<E_join_company> comList=companyDao.getSession().selectList(selectByComShipperIDFullPath,shipperid);
		List<E_join_driver> driList=driverDao.getSession().selectList(selectByDriShipperIDFullPath,shipperid);
		logger.info("个人表长度"+driList.size());
		List<Object> returnList=new ArrayList<Object>();
		if(comList.size()!=0){
			returnList.add(1);
			returnList.add(comList.get(0).getLineid());
		}
		if(driList.size()!=0){
			returnList.add(0);
			returnList.add(driList.get(0).getLineid());
		}
		return returnList;
	}	
}
