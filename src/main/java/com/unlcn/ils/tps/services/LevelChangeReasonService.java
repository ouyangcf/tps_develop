package com.unlcn.ils.tps.services;

import java.util.List; 

import com.unlcn.ils.tps.E_levelchange_reson;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
/**
 * 
 *@Title:
 *@Description:级别调整原因服务
 *@Author:johnn
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class LevelChangeReasonService {
	/**
	 * 
	 * @return
	 * @Description:获取原因字典中所有可用的原因作为级别调整时的下拉选项
	 */
public List<E_levelchange_reson> getReasonName(){
	String selectByActiveFullPath="com.unlcn.ils.tps.E_levelchange_resonMapper.selectByActive";
	Dao<E_levelchange_reson> dao = DaoFactory.create(E_levelchange_reson.class);
	List<E_levelchange_reson> List=dao.getSession().selectList(selectByActiveFullPath,1);
		
		return List;
	}

}
