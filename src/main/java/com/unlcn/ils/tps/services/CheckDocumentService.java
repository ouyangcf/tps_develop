package com.unlcn.ils.tps.services;  

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.unlcn.ils.tps.E_checkdocument;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
/**
 * 
 *@Title:
 *@Description:考核记录单服务
 *@Author:Administrator 
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class CheckDocumentService implements ArrayContentProvider{
	@Override
	public Page<E_checkdocument> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_checkdocument> dao = DaoFactory.create(E_checkdocument.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_checkdocument> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	/**
	 * 
	 * @param ShipperId
	 * @return
	 * @Description:根据分供方编号获取考核单
	 */
	@SuppressWarnings("static-access")
	public List<String> getByShipper(String ShipperId){
		Dao<E_checkdocument> checkdocumentDao=new DaoFactory().create(E_checkdocument.class);		
		String selectByShipperLineidFullPath="com.unlcn.ils.tps.E_checkdocumentMapper.selectByShipperLineid";
		List<String> idList=new ArrayList<String>();
		List<E_checkdocument> checkdocuments=checkdocumentDao.getSession().selectList(selectByShipperLineidFullPath, ShipperId);
		for(int i=0;i<checkdocuments.size();i++){
			idList.add(checkdocuments.get(i).getLineid());		
		}
		return idList;
	}
	/**
	 * 
	 * @return
	 * @Description:获取最大的考核单id
	 */
	@SuppressWarnings("static-access")
	public String getMaxCheckDocId(){
		String selectByItem_NameFullPath="com.unlcn.ils.tps.E_checkdocumentMapper.getMaxId";
		Dao<E_checkdocument> checkdocumentDao=new DaoFactory().create(E_checkdocument.class);	
		List<E_checkdocument> checkdocuments=checkdocumentDao.getSession().selectList(selectByItem_NameFullPath);
		return checkdocuments.get(0).getLineid();
	}
	
	@SuppressWarnings("static-access")
	public String getShipperNameById(String id){
		Dao<E_checkdocument> checkdocumentDao=new DaoFactory().create(E_checkdocument.class);
		E_checkdocument checkdocument=checkdocumentDao.selectByID(id);
		return checkdocument.getShipperName();
	}
}
