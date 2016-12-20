package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_attached;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.ininterface.SqlInterface;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title: 
 *@Description:考核单附件
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0 
 */
public class AttachedService implements ArrayContentProvider{
	/**
	 * 
	 * @param mapList
	 * @param id
	 * @return
	 * @Description:创建附件数据
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean createAttached(Map mapList,String id){
		new DaoFactory();
		//一个附件对应一个一个数据
		Dao<E_attached> attachedDao=DaoFactory.create(E_attached.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		//单文件时 
		if(fileList==null){
			E_attached e_attached=new E_attached();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setFlag(2);
			e_attached.setSourceId(id);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
			attachedDao.insert(e_attached);
			return true;
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_attached e_attached=new E_attached();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(2);
				e_attached.setSourceId(id);
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
				attachedDao.insert(e_attached);
			}
			return true;
		}
		}
	/**
	 * 
	 * @param mapList
	 * @param id
	 * @return
	 * @Description:考核单附件之申诉单附件
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean argueAttached(Map mapList,String id){
		new DaoFactory();
		//一个附件对应一个一个数据
		Dao<E_attached> attachedDao=DaoFactory.create(E_attached.class);
		new DaoFactory();
		Dao<E_argue> argueDao=DaoFactory.create(E_argue.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		E_argue e_argue=argueDao.selectByID(id);
		//单文件时
		if(fileList==null){
			E_attached e_attached=new E_attached();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setCheckheadLineid(e_argue.getCheckheadLineid());
			e_attached.setFlag(0);
			e_attached.setSourceId(e_argue.getLineid());
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
			attachedDao.insert(e_attached);
			return true;
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_attached e_attached=new E_attached();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(0);
				e_attached.setSourceId(e_argue.getLineid());
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
				attachedDao.insert(e_attached);
			}
			return true;
		}
		}	
	/**
	 * 
	 * @param mapList
	 * @param id
	 * @return
	 * @Description:处理附件
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean possessAttached(Map mapList,String id){
		new DaoFactory();
		//一个附件对应一个一个数据
		Dao<E_attached> attachedDao=DaoFactory.create(E_attached.class);
		new DaoFactory();
		Dao<E_checkdocument> checkdocDao=DaoFactory.create(E_checkdocument.class);
		new DaoFactory();
		Dao<E_checkdetail> checkdetailDao=DaoFactory.create(E_checkdetail.class);
		new DaoFactory();
		Dao<E_checkhead> checkheaDao=DaoFactory.create(E_checkhead.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		//单文件时
		if(fileList==null){
			E_attached e_attached=new E_attached();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setFlag(2);
			e_attached.setSourceId(id);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
			attachedDao.insert(e_attached);
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_attached e_attached=new E_attached();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(2);
				e_attached.setSourceId(id);
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
				attachedDao.insert(e_attached);
			}
		}
		//检测所在的考核记录单已经生成了表头，如果是则更新数据
		E_checkdocument e_checkdocument=checkdocDao.selectByID(id);
		if(e_checkdocument.getFlag()==1){
			//获取表头
			String selectByCheckDocLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckDocLineid";
			String selectByCheckDocLineidFullPath1="com.unlcn.ils.tps.E_attachedMapper.selectByCheckDocLineid";
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckDocLineidFullPath, e_checkdocument.getLineid());
			E_checkdetail e_checkdetail=checkdetails.get(0);
			E_checkhead e_checkhead=checkheaDao.selectByID(e_checkdetail.getCheckheadLineid());
			String checkheadId=e_checkhead.getLineid();
			List<E_attached> attacheds=attachedDao.getSession().selectList(selectByCheckDocLineidFullPath1, id);
			for(int i=0;i<attacheds.size();i++){
				E_attached e_attached=attacheds.get(i);
				e_attached.setCheckheadLineid(checkheadId);
				attachedDao.update(e_attached);
			}
		}
		return false;
		}

	@Override
	public Page<E_attached> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_attached> dao = DaoFactory.create(E_attached.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_attached> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	/**
	 * 
	 * @param mapList
	 * @param id
	 * @return
	 * @Description:整改单附件
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	public boolean reviseAttached(Map mapList,String id){
		new DaoFactory();
		//一个附件对应一个一个数据
		Dao<E_attached> attachedDao=DaoFactory.create(E_attached.class);
		Dao<E_revise> reviseDao=new DaoFactory().create(E_revise.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		E_revise e_revise=reviseDao.selectByID(id);
		//单文件时
		if(fileList==null){
			E_attached e_attached=new E_attached();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setCheckheadLineid(e_revise.getCheckheadLineid());
			e_attached.setFlag(1);
			e_attached.setSourceId(e_revise.getLineid());
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
			attachedDao.insert(e_attached);
			return true;
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_attached e_attached=new E_attached();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(1);
				e_attached.setSourceId(e_revise.getLineid());
				Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
				String username = context.getUser().getName();
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				e_attached.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ATTACHED"));
				attachedDao.insert(e_attached);
			}
			return true;
		}
		}	
	@SuppressWarnings("static-access")
	public boolean delFile(List<String> id){
		Dao<E_attached> dao=new DaoFactory().create(E_attached.class);
		E_attached attached=new E_attached();
		for(int i=0;i<id.size();i++){
			attached.setLineid(id.get(i));
			dao.delete(attached);
		}
		
		return false;
	}
}
