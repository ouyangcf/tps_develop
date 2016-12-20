package com.unlcn.ils.tps.services;

import java.sql.Timestamp;  
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_level;
import com.unlcn.ils.tps.E_levelchange;
import com.unlcn.ils.tps.E_levelchange_reson;
import com.unlcn.ils.tps.E_line;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.unlcn.ils.tps.ininterface.SqlInterface;



import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
/**
 * 
 *@Title:
 *@Description:分级调整模块服务 
 *@Author:johnn
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class LevelService {
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	/**
	 * 
	 * @return
	 * @Description:获取所有级别作为查询时的下拉选项
	 */
	public List<E_level> getlevelname(){
			
			Dao<E_level> dao = DaoFactory.create(E_level.class);
			return dao.selectAll();
		} 
	/**
	 * @param data
	 * @return
	 * @Description:设置E_levelchange的lineid
	 */
	public E_levelchange setLineid(E_levelchange data){
		SqlInterface sqlInterface=new SqlInterface();
		String lineid=sqlInterface.getNextVal("SEQ_TPS_LEVELCHANGE");
		data.setLineid(lineid);
		return data;
	}
	/**
	 * @param idList
	 * @param data
	 * @return
	 * @Description:级别调整
	 */
	@SuppressWarnings("static-access")
	public boolean createLevelChange(List<String> idList,E_levelchange data){
		if(data.getAfterlevelid()==null||data.getAfterlevelid().equals("")){			
			throw new RuntimeException("请选择调整后级别");
		};
		
		if(data.getResonchangeLineid()== null||data.getResonchangeLineid().equals("")){
			throw new RuntimeException("请选择调整原因");
		};
		
		Dao<E_levelchange> levelchangeDao=new DaoFactory().create(E_levelchange.class);
		Dao<E_currentlevel> currentlevelDao=new DaoFactory().create(E_currentlevel.class);
		Dao<E_level> levelDao=new DaoFactory().create(E_level.class);
		Dao<E_line> lineDao=new DaoFactory().create(E_line.class);
		Dao<E_levelchange_reson> reasonDao=new DaoFactory().create(E_levelchange_reson.class);
		for(int i=0;i<idList.size();i++){ 
			//首先在dao里面根据id搜索到相应的currentlevel数据
			E_currentlevel e_currentlevel=currentlevelDao.selectByID(idList.get(i).toString());
			//新建一个levelchange变量，手动设置要添加的内容，页面的已经添加的内容用
			E_line temp=lineDao.selectByID(e_currentlevel.getStartCityid());
			E_levelchange e_levelchange=new E_levelchange();
			
			E_level e_level = levelDao.selectByID(data.getAfterlevelid());
			E_levelchange_reson e_reason = reasonDao.selectByID(data.getResonchangeLineid());
			String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
			List<E_levelchange> levelchangeList=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,idList.get(i).toString());
			boolean flag=false;
			for(int pos=0;pos<levelchangeList.size();pos++){
				if(levelchangeList.get(pos).getApproveStatus()==1||levelchangeList.get(pos).getApproveStatus()==2/*||levelchangeList.get(pos).getApproveStatus()==4*/)
				{	
					//e_currentlevel.setActive(2);
					currentlevelDao.update(e_currentlevel);
					levelchangeList.get(pos).setAfterlevelid(data.getAfterlevelid());
					levelchangeList.get(pos).setAfterlevelname(e_level.getLevelName());
					levelchangeList.get(pos).setResonchangeLineid(data.getResonchangeLineid());
					levelchangeList.get(pos).setResonchangeName(e_reason.getResonchangeName());
					levelchangeList.get(pos).setChangeOtherreson(data.getChangeOtherreson());
					levelchangeList.get(pos).setStrartDate(data.getStrartDate());
					levelchangeList.get(pos).setEndDate(data.getEndDate());
					levelchangeList.get(pos).setApproveStatus(Integer.valueOf(1));
					java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
					levelchangeList.get(pos).setCreateTime(currdate);
					levelchangeList.get(pos).setCreateUsername(username);
					levelchangeDao.update(levelchangeList.get(pos));
					flag=true;
					break;
				}
				
			}
			if(!flag){
			SqlInterface sqlInterface=new SqlInterface();
			String lineid=sqlInterface.getNextVal("SEQ_TPS_LEVELCHANGE");
			e_levelchange.setLineid(lineid);
			//e_currentlevel.setActive(2);
			currentlevelDao.update(e_currentlevel);
			e_levelchange.setLeveltableLineid(idList.get(i).toString());
			e_levelchange.setShipperLineid(e_currentlevel.getShipperLineid());
			e_levelchange.setRoutLineid(temp.getLineid());
			e_levelchange.setPrelevelid(e_currentlevel.getLevelLineid());
			e_levelchange.setPrelevelname(e_currentlevel.getLevelName());
			e_levelchange.setAfterlevelid(data.getAfterlevelid());
			e_levelchange.setAfterlevelname(e_level.getLevelName());
			e_levelchange.setResonchangeLineid(data.getResonchangeLineid());
			e_levelchange.setResonchangeName(e_reason.getResonchangeName());
			e_levelchange.setChangeOtherreson(data.getChangeOtherreson());
			e_levelchange.setStrartDate(data.getStrartDate());
			e_levelchange.setEndDate(data.getEndDate());
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			e_levelchange.setCreateTime(currdate);
			e_levelchange.setCreateUsername(username);
			e_levelchange.setApproveStatus(Integer.valueOf(1));
			levelchangeDao.insert(e_levelchange);				
			}
		}
		return false;
	}
	/**
	 * @param idList
	 * @param data
	 * @return
	 * @Description:级别审核
	 */
	@SuppressWarnings({ "static-access", "unused" })
	public boolean levelCheckSubmit(List<String> idList,E_levelchange data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_levelchange> levelCheckDao=new DaoFactory().create(E_levelchange.class);
		Dao<E_currentlevel> levelDao=new DaoFactory().create(E_currentlevel.class);
		String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
		for(int i=0;i<idList.size();i++){ 
			E_levelchange e_levelCheck=levelCheckDao.selectByID(idList.get(i).toString());
			E_currentlevel e_Currentlevel=levelDao.selectByID(e_levelCheck.getLeveltableLineid());
			if(data.getApproveStatus()==3)
			{e_Currentlevel.setLevelLineid(e_levelCheck .getAfterlevelid());
			e_Currentlevel .setLevelName(e_levelCheck.getAfterlevelname());
			}
			e_levelCheck.setApproveStatus(data.getApproveStatus());
			e_levelCheck.setApproveMemo(data.getApproveMemo());
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			e_levelCheck.setApproveTime(currdate);
			e_levelCheck.setApproveUsername(username);
			levelCheckDao.update(e_levelCheck);
			levelDao.update(e_Currentlevel);
			
		}
		return false;
	}

	/**
	 * @param data
	 * @return
	 * @Description:新增原因字典
	 */
	public E_levelchange_reson addReason(E_levelchange_reson data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		/*Dao<E_levelchange_reson> dao=DaoFactory.create(E_levelchange_reson.class);
		E_levelchange_reson reason=new E_levelchange_reson();
		reason.setResonchangeName(data.getResonchangeName());
		reason=dao.selectOne(reason);
		if(reason!=null){
			throw new TpsRuntimeException("该原因已存在");
		}*/
		data.setActive(1);
		data.setActiveMemo("");
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setActiveTime(currdate);
		data.setActiveUser("");
		data.setCreateTime(currdate);
		data.setCreateUsername(username);
		
		return data;
	}
	/**
	 * @param idList
	 * @param data
	 * @return
	 * @Description:禁用原因字典
	 */
@SuppressWarnings("static-access")
public boolean activeReason(List<Integer> idList ,String data){
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();	
	Dao<E_levelchange_reson> reasonDao=new DaoFactory().create(E_levelchange_reson.class);
	for(int i=0;i<idList.size();i++){
		E_levelchange_reson e_levelchange_reson=reasonDao.selectByID(idList.get(i));
		e_levelchange_reson.setActive(0);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		e_levelchange_reson.setActiveTime(currdate);
		e_levelchange_reson.setActiveMemo(data);
		e_levelchange_reson.setActiveUser(username);
		reasonDao.update(e_levelchange_reson);
	}
	
		return false;
		
	}
/**
 * 
 * @param idList
 * @param data
 * @return
 * @Description:编辑原因字典
 */
@SuppressWarnings("static-access")
public boolean editReason(Integer idList ,E_levelchange_reson data){
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();	
	Dao<E_levelchange_reson> reasonDao=new DaoFactory().create(E_levelchange_reson.class);
	/*	E_levelchange_reson reason  = new E_levelchange_reson();
		reason.setResonchangeName(data.getResonchangeName());
		reason=reasonDao.selectOne(reason);
		if(reason!=null){
			throw new TpsRuntimeException("该原因已存在");
		}*/
		E_levelchange_reson e_levelchange_reson=reasonDao.selectByID(idList);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		e_levelchange_reson.setCreateTime(currdate);
		if(data.getActiveMemo()!=null)
		e_levelchange_reson.setActiveMemo(data.getActiveMemo());
		if(data.getResonchangeName()!=null)
		e_levelchange_reson.setResonchangeName(data.getResonchangeName());
		e_levelchange_reson.setCreateUsername(username);
		reasonDao.update(e_levelchange_reson);
	
		return false;
		
	}
/**
 * 
 * @param idList
 * @return
 * @Description:启用原因字典
 */
@SuppressWarnings("static-access")
public boolean enableReason(List<Integer> idList ){
	
	Dao<E_levelchange_reson> reasonDao=new DaoFactory().create(E_levelchange_reson.class);
	for(int i=0;i<idList.size();i++){
		E_levelchange_reson e_levelchange_reson=reasonDao.selectByID(idList.get(i));
		e_levelchange_reson.setActive(1);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		e_levelchange_reson.setActiveTime(currdate);
		e_levelchange_reson.setActiveMemo("");
		e_levelchange_reson.setActiveUser("");
		reasonDao.update(e_levelchange_reson);
	}
	
		return false;
		
	}
/**
 * 
 * @param idList
 * @return
 * @Description:删除原因字典
 */
@SuppressWarnings("static-access")
public boolean deleteReason(List<Integer> idList){
	
	Dao<E_levelchange_reson> dao=new DaoFactory().create(E_levelchange_reson.class);
	for(int i=0;i<idList.size();i++)
	{
		E_levelchange_reson lists=dao.selectByID(idList.get(i));
		dao.delete(lists);
	}
			
		return true;
	}
/**
 * @param data
 * @return
 * @Description:新增级别字典
 */
public E_level addLevel(E_level data){
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
/*	Dao<E_level> levelDao=new DaoFactory().create(E_level.class);
	E_level level  = new E_level();
	level.setLevelName(data.getLevelName());
	level=levelDao.selectOne(level);
	if(level!=null){
		throw new TpsRuntimeException("该等级已存在");
	}*/
	data.setActive(1);
	data.setActiveMemo("");
	java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
	data.setActiveTime(currdate);
	data.setActiveUser("");
	data.setCreateTime(currdate);
	data.setCreateUsername(username);
	
	return data;
}
/**
 * 
 * @param idList
 * @return
 * @Description:删除级别字典
 */
@SuppressWarnings("static-access")
public boolean deleteLevel(List<Integer> idList){
	
	Dao<E_level> dao=new DaoFactory().create(E_level.class);
	for(int i=0;i<idList.size();i++)
	{
		E_level lists=dao.selectByID(idList.get(i));
		dao.delete(lists);
	}
			
		return true;
	}
/**
 * 
 * @param idList
 * @param data
 * @return
 * @Description:禁用级别字典
 */
@SuppressWarnings("static-access")
public boolean activeLevel(List<Integer> idList ,String data ){
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	Dao<E_level> reasonDao=new DaoFactory().create(E_level.class);
	for(int i=0;i<idList.size();i++){
		E_level level=reasonDao.selectByID(idList.get(i));
		level.setActive(0);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		level.setActiveTime(currdate);
		level.setActiveMemo(data);
		level.setActiveUser(username);
		reasonDao.update(level);
	}
	
		return false;
		
	}
/**
 * 
 * @param idList
 * @param data
 * @return
 * @Description:编辑级别字典
 */
@SuppressWarnings("static-access")
public boolean editLevel(Integer idList ,E_level data ){
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	Dao<E_level> reasonDao=new DaoFactory().create(E_level.class);
	/*Dao<E_level> levelDao=new DaoFactory().create(E_level.class);
	E_level levels  = new E_level();
	levels.setLevelName(data.getLevelName());
	levels=levelDao.selectOne(levels);
	if(levels!=null){
		throw new TpsRuntimeException("该等级已存在");
		
	}*/
		E_level level=reasonDao.selectByID(idList);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		level.setCreateTime(currdate);
		level.setCreateUsername(username);
		if(data.getLevelName()!=null)
		level.setLevelName(data.getLevelName());
		if(data.getActiveMemo()!=null)
		level.setActiveMemo(data.getActiveMemo());
		reasonDao.update(level);
	
		return false;
		
	}
/**
 * 
 * @param idList
 * @return
 * @Description:启用级别字典
 */
@SuppressWarnings("static-access")
public boolean enableLevel(List<Integer> idList ){
	
	Dao<E_level> reasonDao=new DaoFactory().create(E_level.class);
	for(int i=0;i<idList.size();i++){
		E_level e_level=reasonDao.selectByID(idList.get(i));
		e_level.setActive(1);
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		e_level.setActiveTime(currdate);
		e_level.setActiveMemo("");
		e_level.setActiveUser("");
		reasonDao.update(e_level);
	}
	
		return false;
		
	}
/**
 * 
 * @return
 * @Description:获取调整后级别作为级别调整时的下拉框选项
 */
public List<E_level> getAfterLevelName(){
	String selectByActiveFullPath="com.unlcn.ils.tps.E_levelMapper.selectByActive";
	Dao<E_level> dao = DaoFactory.create(E_level.class);
	List<E_level> List=dao.getSession().selectList(selectByActiveFullPath,1);
		
		return List;
	} 
/**
 *
 * @param startCityID
 * @param destProvinceId
 * @param approveStatus
 * @param createTime
 * @param createTime2
 * @return
 * @Description:根据条件查询数据
 */
@SuppressWarnings({ "static-access", "unused" })
public List<List<String>> getCondition(Integer startCityID,Integer destProvinceId,Integer approveStatus ,String createTime,String createTime2){
	Dao<E_line> lineDao=new DaoFactory().create(E_line.class);
	Dao<E_levelchange> levelchangeDao=new DaoFactory().create(E_levelchange.class);
	Dao<E_currentlevel> currentDao=new DaoFactory().create(E_currentlevel.class);
	List<List<String>> returnList=new ArrayList<List<String>>();
	List<String> idList1=new ArrayList<String>();
	List<String> idList2=new ArrayList<String>();
	List<String> idList3=new ArrayList<String>();
	List<String> idList4=new ArrayList<String>();
	List<String> idList5=new ArrayList<String>();
	String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
	if(startCityID!=null){
		String selectByStartCityIDFullPath="com.unlcn.ils.tps.E_lineMapper.selectByStartCityID";
		List<E_line> lines=lineDao.getSession().selectList(selectByStartCityIDFullPath,startCityID);
		String selectByRout_LineIDFullPath="com.unlcn.ils.tps.E_currentlevelMapper.selectByRout_LineID";
		for(int i=0;i<lines.size();i++){
			List<E_currentlevel> currentlevels=currentDao.getSession().selectList(selectByRout_LineIDFullPath,lines.get(i).getLineid());
			for(int j=0;j<currentlevels.size();j++){
				idList1.add(currentlevels.get(j).getLineid());
			}
		}
		if(idList1.size()==0){
			idList1.add("-1");
		}
	}
	returnList.add(idList1);
	if(destProvinceId!=null){
		String selectByDestProvinceIDFullPath="com.unlcn.ils.tps.E_lineMapper.selectByDestProvinceID";
		List<E_line> lines=lineDao.getSession().selectList(selectByDestProvinceIDFullPath,destProvinceId);
		String selectByRout_LineIDFullPath="com.unlcn.ils.tps.E_currentlevelMapper.selectByRout_LineID";
		List<Integer> idList=new ArrayList<Integer>();
		for(int i=0;i<lines.size();i++){
			List<E_currentlevel> currentlevels=currentDao.getSession().selectList(selectByRout_LineIDFullPath,lines.get(i).getLineid());
			for(int j=0;j<currentlevels.size();j++){
				idList2.add(currentlevels.get(j).getLineid());
			}
		}
		if(idList2.size()==0){
			idList2.add("-1");
		}		
	}
	returnList.add(idList2);
	if(approveStatus!=null){	
		List<E_currentlevel> currentlevels=currentDao.selectAll();
		for(int i=0;i<currentlevels.size();i++){
			List<E_levelchange> levelchanges=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,currentlevels.get(i).getLineid());
			if(levelchanges.size()!=0){
				Integer status=levelchanges.get(levelchanges.size()-1).getApproveStatus();
				if(status==approveStatus){
					idList3.add(levelchanges.get(levelchanges.size()-1).getLeveltableLineid());
				}
			}
		}
		if(idList3.size()==0){
			idList3.add("-1");
		}		
	}
	returnList.add(idList3);
	if(createTime!=null){
		List<E_currentlevel> currentlevels=currentDao.selectAll();
		for(int i=0;i<currentlevels.size();i++){
			List<E_levelchange> levelchanges=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,currentlevels.get(i).getLineid());
			if(levelchanges.size()!=0){
				Timestamp timestamp=levelchanges.get(levelchanges.size()-1).getCreateTime();
				if(timestamp.after(Timestamp.valueOf(createTime))){
					idList4.add(levelchanges.get(levelchanges.size()-1).getLeveltableLineid());
				}
			}
		}
		if(idList4.size()==0){
			idList4.add("-1");
		}
	}
	returnList.add(idList4);
	if(createTime2!=null){
		List<E_currentlevel> currentlevels=currentDao.selectAll();
		for(int i=0;i<currentlevels.size();i++){
			List<E_levelchange> levelchanges=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,currentlevels.get(i).getLineid());
			if(levelchanges.size()!=0){
				Timestamp timestamp=levelchanges.get(levelchanges.size()-1).getCreateTime();
				if(timestamp.before(Timestamp.valueOf(createTime2))){
					idList5.add(levelchanges.get(levelchanges.size()-1).getLeveltableLineid());
				}
			}
		}
		if(idList5.size()==0){
			idList5.add("-1");
		}
	}
	returnList.add(idList5);
	return returnList;
}
/**
 * 
 * @param startCityID
 * @param destProvinceId
 * @return
 * @Description:查询未调整的数据
 */
@SuppressWarnings({ "static-access", "unused" })
public List<List<String>> getApproveStatusCondition(Integer startCityID,Integer destProvinceId){
	Dao<E_line> lineDao=new DaoFactory().create(E_line.class);
	Dao<E_levelchange> levelchangeDao=new DaoFactory().create(E_levelchange.class);
	Dao<E_currentlevel> currentDao=new DaoFactory().create(E_currentlevel.class);
	String selectByLevelTable_LineIDFullPath="com.unlcn.ils.tps.E_levelchangeMapper.selectByLevelTable_LineID";
	
	List<String> returnList=new ArrayList<String>();
	List<E_currentlevel> levels=currentDao.selectAll();
	List<String> lineid=new ArrayList<String>();
	List<String> lineid1=new ArrayList<String>();
	List<String> lineid2=new ArrayList<String>();
	List<List<String>> returnList2=new ArrayList<List<String>>();
	for(int i=0;i<levels.size();i++)
	{
		List<E_levelchange> levelchange=levelchangeDao.getSession().selectList(selectByLevelTable_LineIDFullPath,levels.get(i).getLineid());
		if(levelchange.size()==0)
		{
			
			lineid.add(levels.get(i).getLineid());
		}
		
	}
	if(levels.size()==0)
		lineid.add("-1");
	returnList2.add(lineid);
	if(startCityID!=null){
		String selectByStartCityIDFullPath="com.unlcn.ils.tps.E_lineMapper.selectByStartCityID";
		List<E_line> lines=lineDao.getSession().selectList(selectByStartCityIDFullPath,startCityID);
		String selectByRout_LineIDFullPath="com.unlcn.ils.tps.E_currentlevelMapper.selectByRout_LineID";
		for(int i=0;i<lines.size();i++){
			List<E_currentlevel> currentlevels=currentDao.getSession().selectList(selectByRout_LineIDFullPath,lines.get(i).getLineid());
			for(int j=0;j<currentlevels.size();j++){
				lineid1.add(currentlevels.get(j).getLineid());
			}
		}
		if(lineid1.size()==0){
			lineid1.add("-1");
		}
	}
	returnList2.add(lineid1);	
	if(destProvinceId!=null){
		String selectByDestProvinceIDFullPath="com.unlcn.ils.tps.E_lineMapper.selectByDestProvinceID";
		List<E_line> lines=lineDao.getSession().selectList(selectByDestProvinceIDFullPath,destProvinceId);
		String selectByRout_LineIDFullPath="com.unlcn.ils.tps.E_currentlevelMapper.selectByRout_LineID";
		List<Integer> idList=new ArrayList<Integer>();
		for(int i=0;i<lines.size();i++){
			List<E_currentlevel> currentlevels=currentDao.getSession().selectList(selectByRout_LineIDFullPath,lines.get(i).getLineid());
			for(int j=0;j<currentlevels.size();j++){
				lineid2.add(currentlevels.get(j).getLineid());
			}
		}
		if(lineid2.size()==0){
			lineid2.add("-1");
		}		
	}
	returnList2.add(lineid2);
	return returnList2;
	
}
/**
 * 
 * @param shipperid
 * @return
 * @Description:根据shipperid获取code
 */
private String getShipperById(String shipperid){
	//ShipperInterface ss=new ShipperInterface();
	//Map<String,String> map=ShipperInterface.getShipperByID(shipperid.toString());
	CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
	Map<String, String> map=crmInformationInterface.getShipperFromCrmById(shipperid);
		return map.get("name");
}
/**
 * 
 * @param data
 * @return
 * @Description:在当前级别表中新增数据
 */
public boolean setCurrentLevel(E_currentlevel data) {
	java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
	Dao<E_currentlevel> dao=DaoFactory.create(E_currentlevel.class);
	Dao<E_level> levelDao=DaoFactory.create(E_level.class);
	E_level templevel=levelDao.selectByID(data.getLevelLineid());
 	E_currentlevel e_currentlevel=new E_currentlevel();
 	SqlInterface sqlInterface=new SqlInterface();
 	String lineid=sqlInterface.getNextVal("SEQ_TPS_CURRENTLEVEL");
 	//e_currentlevel.setLineid(lineid);
	e_currentlevel.setShipperLineid(data.getShipperLineid());
	e_currentlevel.setShipperName(getShipperById(data.getShipperLineid()));
	e_currentlevel.setStartCityid(data.getStartCityid());
	e_currentlevel.setLevelLineid(data.getLevelLineid());
	e_currentlevel.setLevelName(templevel.getLevelName());
	e_currentlevel.setCreateTime(currdate);
	e_currentlevel.setCreateUsername(username);
	
	e_currentlevel.setActiveTime(currdate);
	e_currentlevel.setActiveMemo(data.getActiveMemo());
	//e_currentlevel.setActive(1);
	E_currentlevel conditionCurrentlevel=new E_currentlevel();
	conditionCurrentlevel.setShipperLineid(data.getShipperLineid());
	conditionCurrentlevel.setStartCityid(data.getStartCityid());
	List<E_currentlevel> currentList = dao.select(conditionCurrentlevel);
	if(currentList.size()!=0){
		//此处用于作为数据是否是再次插入的标志
		/*e_currentlevel.setLineid(currentList.get(0).getLineid());
		dao.update(e_currentlevel);*/
		return false;
	}
	else {
		e_currentlevel.setLineid(lineid);
		dao.insert(e_currentlevel);
		return true;
	}
	
}
/**
 * 
 * @return
 * @Description:获取线路字典
 */
public List<E_line> getRoutLineid(){
	
	Dao<E_line> dao = DaoFactory.create(E_line.class);
	return dao.selectAll();
}
}

