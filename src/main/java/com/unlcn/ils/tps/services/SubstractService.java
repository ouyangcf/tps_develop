package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.E_substract;
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
 *@Description:减免单服务 
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class SubstractService implements ArrayContentProvider{
	/**
	 * 
	 * @param lineid
	 * @param e_substract
	 * @return
	 * @Description:创建减免单
	 */
	@SuppressWarnings("unused")
	public boolean addSubstract(String lineid,E_substract e_substract){
		Dao<E_argue> argueDao = DaoFactory.create(E_argue.class);
		Dao<E_revise> reviseDao = DaoFactory.create(E_revise.class);
		Dao<E_substract> substractDao = DaoFactory.create(E_substract.class);
		E_argue e_argue=argueDao.selectByID(lineid);
		E_substract e_substract2=new E_substract();
		String checkheadid=e_argue.getCheckheadLineid();
		String checkdetail=e_argue.getCheckdetailLineid();
		String getMaxIdFullPath="com.unlcn.ils.tps.E_substractMapper.getMaxId";
		List<E_substract> lists=substractDao.getSession().selectList(getMaxIdFullPath);
		SqlInterface sqlInterface=new SqlInterface();
		String maxid=sqlInterface.getNextVal("SEQ_TPS_TPS_SUBSTRACT");
		e_substract2.setLineid(maxid);
		e_substract2.setSubFrom(1);
		e_substract2.setSubFromid(lineid);
		e_substract2.setCheckheadLineid(checkheadid);
		e_substract2.setSubMoney(e_substract.getSubMoney());
		e_substract2.setSubReson(e_substract.getSubReson());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_substract2.setCreateTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		e_substract2.setCreateUser(username);
		try {
			substractDao.insert(e_substract2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param lineid
	 * @param e_substract
	 * @return
	 * @Description:创建减免单
	 */
	@SuppressWarnings("unused")
	public boolean addSubstractn(String lineid,E_substract e_substract){
		Dao<E_argue> argueDao = DaoFactory.create(E_argue.class);
		Dao<E_revise> reviseDao = DaoFactory.create(E_revise.class);
		Dao<E_substract> substractDao = DaoFactory.create(E_substract.class);
		E_revise e_revise=reviseDao.selectByID(lineid);
		E_substract e_substract2=new E_substract();
		String checkheadid=e_revise.getCheckheadLineid();
		String checkdetail=e_revise.getCheckdetailLineid();
		String getMaxIdFullPath="com.unlcn.ils.tps.E_substractMapper.getMaxId";
		List<E_substract> lists=substractDao.getSession().selectList(getMaxIdFullPath);
		SqlInterface sqlInterface=new SqlInterface();
		String maxid=sqlInterface.getNextVal("SEQ_TPS_TPS_SUBSTRACT");
		e_substract2.setLineid(maxid);
		e_substract2.setSubFrom(2);
		e_substract2.setSubFromid(lineid);
		e_substract2.setCheckheadLineid(checkheadid);
		e_substract2.setSubMoney(e_substract.getSubMoney());
		e_substract2.setSubReson(e_substract.getSubReson());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_substract2.setCreateTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		e_substract2.setCreateUser(username);
		try {
			substractDao.insert(e_substract2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param lineid
	 * @param e_substract
	 * @return
	 * @Description:增加减免单
	 */
	@SuppressWarnings("unused")
	public boolean addSubstractn_(String lineid,E_substract e_substract){
		Dao<E_argue> argueDao = DaoFactory.create(E_argue.class);
		Dao<E_revise> reviseDao = DaoFactory.create(E_revise.class);
		Dao<E_substract> substractDao = DaoFactory.create(E_substract.class);
		E_substract e_substract2=new E_substract();
		String getMaxIdFullPath="com.unlcn.ils.tps.E_substractMapper.getMaxId";
		List<E_substract> lists=substractDao.getSession().selectList(getMaxIdFullPath);
		SqlInterface sqlInterface=new SqlInterface();
		String maxid=sqlInterface.getNextVal("SEQ_TPS_TPS_SUBSTRACT");
		e_substract2.setLineid(maxid);
		e_substract2.setSubFrom(3);
		e_substract2.setSubFromid(lineid);
		e_substract2.setCheckheadLineid(lineid);
		e_substract2.setSubMoney(e_substract.getSubMoney());
		e_substract2.setSubReson(e_substract.getSubReson());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_substract2.setCreateTime(timestampnow);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		e_substract2.setCreateUser(username);
		try {
			substractDao.insert(e_substract2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	@Override
	public Page<E_substract> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_substract> dao = DaoFactory.create(E_substract.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_substract> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		
		return result;
	}
}
