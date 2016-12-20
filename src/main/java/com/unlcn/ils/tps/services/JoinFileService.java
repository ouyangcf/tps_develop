package com.unlcn.ils.tps.services;
 
import java.util.Map;

import com.unlcn.ils.tps.E_join_file;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class JoinFileService implements ArrayContentProvider{

	@Override
	/**
	 * @Description：查看附件列表
	 */
	public Page<E_join_file> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_join_file> dao = DaoFactory.create(E_join_file.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_join_file> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		return result;
	}
	
	/**
	 * @Description：删除结盟合作附件表里面的附件；
	 * @param id
	 * @return
	 */
	public boolean delFile(String id){
		Dao<E_join_file> dao = DaoFactory.create(E_join_file.class) ;
		E_join_file e_join_file=new E_join_file();
		e_join_file.setLineid(id.toString());
		dao.delete(e_join_file);
		return false;
	}
}
