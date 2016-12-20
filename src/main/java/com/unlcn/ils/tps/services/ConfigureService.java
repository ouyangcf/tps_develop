package com.unlcn.ils.tps.services;

import java.sql.Timestamp;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_configure_detail;
import com.unlcn.ils.tps.E_configure_head;
import com.unlcn.ils.tps.ininterface.SqlInterface;

/**
 * 
 *@Title:
 *@Description:考核配置服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ConfigureService {
	private static Logger logger=Logger.getLogger(Logger.class);
	/**
	 * 
	 * @return
	 * @Description:获取考核配置表头
	 */
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public List<Map> getConfigures(){
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class); 
		List<E_configure_head> lists=configureheadDao.selectAll();
		List<Map> returnList=new ArrayList<Map>();
		for(int i=0;i<lists.size();i++){
			Map map=new HashMap();
			map.put(lists.get(i).getLineid(),lists.get(i).getConfigureName());
			returnList.add(map);
		}
		return returnList; 
	}
	/**
	 * 
	 * @return
	 * @Description:获取最新的id
	 */
	@SuppressWarnings("static-access")
	public String getNewestId(){
		Dao<E_configure_head> configureheadDao=new DaoFactory().create(E_configure_head.class);  
		String getMaxIdFullPath="com.unlcn.ils.tps.E_configure_headMapper.getMaxId";
		List<E_configure_head> maxIdList=configureheadDao.getSession().selectList(getMaxIdFullPath);
		if(maxIdList.size()!=0){
			String max=maxIdList.get(0).getLineid();
			return max;
		}
		return "2";
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:处理考核配资表表头
	 */
	public E_configure_head pocess(E_configure_head data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setCreateUsername(username);
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestampnow);
		SqlInterface sqlInterface=new SqlInterface();
		data.setLineid(sqlInterface.getNextVal("SEQ_TPS_CONFIGURE_HEAD"));
		return data;
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:删除考核配置
	 */
	@SuppressWarnings("static-access")
	public boolean delConfigures(List<String> idList){
		Dao<E_configure_detail> dao=new DaoFactory().create(E_configure_detail.class);
		logger.info("获取准备删除的id号");
		for(int i=0;i<idList.size();i++){
			logger.info("第"+i+"个id为："+idList.get(i));
		}
		for(int i=0;i<idList.size();i++){
			E_configure_detail del=new E_configure_detail();
			del.setLineid(idList.get(i));
			dao.delete(del);
		}
		return false;
	}
}
