package com.unlcn.ils.tps.service.impl;

import java.sql.Timestamp;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkhead; 
import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_levelchange; 
import com.unlcn.ils.tps.E_line;
import com.unlcn.ils.tps.ininterface.ShipperInterface;


import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.service.TpsService;
import com.unlcn.ils.tps.shipper.CheckDetail;
import com.unlcn.ils.tps.shipper.CheckInfo;
import com.unlcn.ils.tps.shipper.LevelChange;
import com.unlcn.ils.tps.shipper.Route;

/**
 * 
 *@Title: 
 *@Description:tps接口提供者实现  
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class TpsServiceImpl implements TpsService{   

	@SuppressWarnings("static-access")
	@Override 
	public List<CheckInfo> queryCheck(String shipperID, String startTime, String endTime) {
		// TODO Auto-generated method stub 
		String shipperLineid=shipperID;
		String start=startTime+" 0:0:0";
		String end=endTime+" 0:0:0";
		Timestamp startDate=Timestamp.valueOf(start);
		Timestamp endDate=Timestamp.valueOf(end);
		List<CheckInfo> checkInfos=new ArrayList<CheckInfo>();
		String selectByCondition="com.unlcn.ils.tps.E_checkheadMapper.selectByCondition";
		Dao<E_checkhead> checkHeadDao=new DaoFactory().create(E_checkhead.class);
		E_checkhead checkhead=new E_checkhead(); 
		checkhead.setShipperLineid(shipperLineid);
		checkhead.setCreateTime(startDate);
		checkhead.setActiveTime(endDate);
		List<E_checkhead> checkheads=checkHeadDao.getSession().selectList(selectByCondition, checkhead);
		for(int i=0;i<checkheads.size();i++){
			CheckInfo tempCheckInfo=new CheckInfo();
			tempCheckInfo.setLineid(checkheads.get(i).getLineid());
			tempCheckInfo.setShipperLineid(checkheads.get(i).getShipperLineid());
			tempCheckInfo.setShipperName(checkheads.get(i).getShipperName());
			tempCheckInfo.setCountmonth(checkheads.get(i).getCountmonth());
			if(checkheads.get(i).getFactSubmoney()!=null)
			tempCheckInfo.setFactSubmoney(checkheads.get(i).getFactSubmoney());
			tempCheckInfo.setArgueStatus(checkheads.get(i).getArgueStatus());
			tempCheckInfo.setStandardSubmoney(checkheads.get(i).getStandardSubmoney());
			tempCheckInfo.setCheckMoney(checkheads.get(i).getCheckMoney());
			if(checkheads.get(i).getSubMoney()!=null)
			tempCheckInfo.setSubMoney(checkheads.get(i).getSubMoney());
			if(checkheads.get(i).getDeductMoney()!=null){
				tempCheckInfo.setDeductMoney(checkheads.get(i).getDeductMoney());
			}
			if(checkheads.get(i).getFronzenMoney()!=null){
				tempCheckInfo.setFronzenMoney(checkheads.get(i).getFronzenMoney());
			}
			tempCheckInfo.setCollectStatus(checkheads.get(i).getCollectStatus());
			tempCheckInfo.setCheckStatus(checkheads.get(i).getCheckStatus());
			tempCheckInfo.setCreateUsername(checkheads.get(i).getCreateUsername());
			tempCheckInfo.setCreateTime(checkheads.get(i).getCreateTime());
			checkInfos.add(tempCheckInfo);
		}
		return checkInfos;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<CheckDetail> queryCheckDetail(String checkHeadID) {
		// TODO Auto-generated method stub
		Dao<E_checkdetail> checkDetailDao=new DaoFactory().create(E_checkdetail.class);
		int checkHeadLineid=Integer.parseInt(checkHeadID);
		String selectByCheckHeadlineid="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		List<CheckDetail> checkDetails=new ArrayList<CheckDetail>();
		List<E_checkdetail> checkdetails=checkDetailDao.getSession().selectList(selectByCheckHeadlineid, checkHeadLineid);
		for(int i=0;i<checkdetails.size();i++){
			CheckDetail tempCheckDetail=new CheckDetail();
			tempCheckDetail.setLineid(checkdetails.get(i).getLineid());
			tempCheckDetail.setCheckheadLineid(checkdetails.get(i).getCheckheadLineid());
			tempCheckDetail.setCheckdocLineid(checkdetails.get(i).getCheckdocLineid());
			tempCheckDetail.setItemLineid(checkdetails.get(i).getItemLineid());
			tempCheckDetail.setItemName(checkdetails.get(i).getItemName());
			tempCheckDetail.setOperateLineid(checkdetails.get(i).getOperateLineid());
			tempCheckDetail.setOperateName(checkdetails.get(i).getOperateName());
			tempCheckDetail.setArgueStatus(checkdetails.get(i).getArgueStatus());
			tempCheckDetail.setCheckNumber(checkdetails.get(i).getCheckNumber());
			tempCheckDetail.setCheckMoney(checkdetails.get(i).getCheckMoney());
			tempCheckDetail.setCheckMemo(checkdetails.get(i).getCheckMemo());
			tempCheckDetail.setCreateTime(checkdetails.get(i).getCreateTime());
			tempCheckDetail.setCreateUsername(checkdetails.get(i).getCreateUsername());
			checkDetails.add(tempCheckDetail);
		}
		return checkDetails;
	}

	@SuppressWarnings("static-access")
	@Override
	public List<Route> queryRoute() {
		// TODO Auto-generated method stub
		Dao<E_line> lineDao=new DaoFactory().create(E_line.class);
		List<E_line> lines=lineDao.selectAll();
		List<Route> routes=new ArrayList<Route>();
		for(int i=0;i<lines.size();i++){
			Route tempRoute=new Route();
			tempRoute.setLineid(lines.get(i).getLineid());
			tempRoute.setStartCity(lines.get(i).getStartCity());
			tempRoute.setDestProvince(lines.get(i).getDestProvince());
			routes.add(tempRoute);
		}
		return routes;
	}

	@SuppressWarnings({ "rawtypes", "unused", "static-access", "unchecked" })
	@Override
	public Map queryShipperLevel(String shipperID, String routID, String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		int shipperId=Integer.parseInt(shipperID);
		int routId=Integer.parseInt(routID);
		String start=startTime+" 0:0:0";
		String end=endTime+" 0:0:0";
		Timestamp startTimestamp=Timestamp.valueOf(start);
		Timestamp endTimestamp=Timestamp.valueOf(end);
		Dao<E_levelchange> levelChangeDao=new DaoFactory().create(E_levelchange.class);
		Dao<E_currentlevel> currentDao=new DaoFactory().create(E_currentlevel.class);
		Dao<E_line> lineDao=new DaoFactory().create(E_line.class);
		//levelLineid
		//levelName
		String selectByCondition="com.unlcn.ils.tps.E_currentlevelMapper.selectByCondition";
		E_currentlevel e_currentlevel=new E_currentlevel();
		e_currentlevel.setShipperLineid(shipperID);
		e_currentlevel.setStartCityid(routID);
		List<E_currentlevel> currentlevels=currentDao.getSession().selectList(selectByCondition, e_currentlevel);
		map.put("levelLineid", currentlevels.get(0).getLevelLineid());
		map.put("levelName", currentlevels.get(0).getLevelName());
		//changeHistory
		
		String selectByCondition1="com.unlcn.ils.tps.E_levelchangeMapper.selectByCondition";
		E_levelchange e_levelchange=new E_levelchange();
		e_levelchange.setShipperLineid(shipperID);
		e_levelchange.setRoutLineid(routID);
		e_levelchange.setCreateTime(startTimestamp);
		e_levelchange.setActiveTime(endTimestamp);
		List<E_levelchange> levelchanges=levelChangeDao.getSession().selectList(selectByCondition1, e_levelchange);
		List<LevelChange> levelChanges=new ArrayList<LevelChange>();
		for(int i=0;i<levelchanges.size();i++){
			LevelChange levelChange=new LevelChange();
			levelChange.setLineid(levelchanges.get(i).getLineid());
			levelChange.setLevelLineid(levelchanges.get(i).getLeveltableLineid());
			E_currentlevel currentlevel=currentDao.selectByID(levelchanges.get(i).getLeveltableLineid());
			//
			levelChange.setLevelName(currentlevel.getLevelName());
			levelChange.setShipperLineid(levelchanges.get(i).getShipperLineid());
			//
			ShipperInterface shipperInterface=new ShipperInterface();
			//
			Map<String,String> map1=shipperInterface.getShipperByID(levelchanges.get(i).getShipperLineid().toString());
			//
			levelChange.setShipperName(map1.get("name"));
			//
			levelChange.setRoutLineid(levelchanges.get(i).getRoutLineid());
			E_line e_line=lineDao.selectByID(levelchanges.get(i).getRoutLineid());
			levelChange.setStartCity(e_line.getStartCity());
			levelChange.setDestProvince(e_line.getDestProvince());
			levelChange.setPrelevelid(levelchanges.get(i).getPrelevelid());
			levelChange.setPrelevelname(levelchanges.get(i).getPrelevelname());
			levelChange.setAfterlevelid(levelchanges.get(i).getAfterlevelid());
			levelChange.setAfterlevelname(levelchanges.get(i).getAfterlevelname());
			levelChange.setResonchangeLineid(levelchanges.get(i).getResonchangeLineid());
			levelChange.setResonchangeName(levelchanges.get(i).getResonchangeName());
			if(levelchanges.get(i).getChangeOtherreson()!=null)
			levelChange.setChangeOtherreson(levelchanges.get(i).getChangeOtherreson());
			levelChange.setStrartDate(levelchanges.get(i).getStrartDate());
			levelChange.setEndDate(levelchanges.get(i).getEndDate());
			if(levelchanges.get(i).getApproveUsername()!=null)
			levelChange.setApproveUsername(levelchanges.get(i).getApproveUsername());
			if(levelchanges.get(i).getApproveMemo()!=null)
			levelChange.setApproveMemo(levelchanges.get(i).getApproveMemo());
			if(levelchanges.get(i).getApproveTime()!=null)
			levelChange.setApproveTime(levelchanges.get(i).getApproveTime());
			if(levelchanges.get(i).getApproveStatus()!=null){
				levelChange.setApproveStatus(levelchanges.get(i).getApproveStatus());		
			}
			levelChange.setCreateTime(levelchanges.get(i).getCreateTime());
			levelChanges.add(levelChange);
		}
		map.put("changeHistory", levelChanges);
		return map;
	}

}
