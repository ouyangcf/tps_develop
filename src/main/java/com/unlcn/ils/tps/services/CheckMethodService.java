package com.unlcn.ils.tps.services; 

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_method;
import com.unlcn.ils.tps.ininterface.SqlInterface;

public class CheckMethodService {
	public E_method checkMethod(E_method data) {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestampnow);
		data.setCreateUser(username);
		return data;
	}
	public boolean deleteMethod( List<Integer> idList) {
		Dao<E_method> dao=DaoFactory.create(E_method.class);
		for (int i = 0; i <idList.size(); i++) {
			E_method method =new E_method();
			method=dao.selectByID(idList.get(i));
			dao.delete(method);
		}
		
		return true;
	}
	public boolean saveMethod(List<E_method> datas) {
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Timestamp createTime=new Timestamp(System.currentTimeMillis());
		Dao<E_method> dao=DaoFactory.create(E_method.class);
		//System.out.println(datas.get(0).getMethod());
		for(int i=0;i<datas.size();i++){
			E_method e_method=new E_method();
			e_method.setCreateTime(createTime);
			e_method.setCreateUser(username);
			e_method.setLeftNode(datas.get(i).getLeftNode());
			e_method.setMethod(datas.get(i).getMethod());
			e_method.setRightNode(datas.get(i).getRightNode());
			e_method.setSubMoney(datas.get(i).getSubMoney());
			e_method.setTarget(datas.get(i).getTarget());
			
			if(datas.get(i).getLineid()==null)
			{
				SqlInterface sqlInterface=new SqlInterface();
				String lineid=sqlInterface.getNextVal("SEQ_TPS_TD_METHOD");
				e_method.setLineid(Integer.valueOf(lineid));
				dao.insert(e_method);
			}
			else {
				e_method.setLineid(datas.get(i).getLineid());
				dao.update(e_method);
			}
		}
		return false;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> getMethods() {
		new DaoFactory();
		Dao<E_method> dao=DaoFactory.create(E_method.class);
		List<E_method> listItems=dao.selectAll();
		List<Map> returnList=new ArrayList<Map>();
		for(int i=0;i<listItems.size()-1;i++){
			for(int j=listItems.size()-1;j>i;j--){
			if(listItems.get(j).getMethod().equals(listItems.get(i).getMethod())){
				listItems.remove(j);
			}	
			}
		}
		for(int i=0;i<listItems.size();i++){
			Map map=new HashMap();

			map.put(listItems.get(i).getLineid().toString(),listItems.get(i).getMethod());
			returnList.add(map);				

		}
		return returnList;
	}
	public int getMethodLineid(String methodName) {
		Dao<E_method> dao=DaoFactory.create(E_method.class);
		E_method method=new E_method();
		method.setMethod(methodName);
		method=dao.selectOne(method);
		
		return method.getLineid();
	}
}
