package com.unlcn.ils.tps.services;

import java.sql.Timestamp; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkhead;
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
 *@Description:申诉单服务
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ArgueService implements ArrayContentProvider{
	/**
	 * 
	 * @param data
	 * @param checkheadlineid
	 * @param checkdetaillineid
	 * @return
	 * @Description:处理申诉单
	 */
	@SuppressWarnings("static-access")
	public E_argue pocessArgue(E_argue data,String checkheadlineid,String checkdetaillineid){
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		E_checkdetail e_checkdetail=checkdetailDao.selectByID(checkdetaillineid);
		//更新明细表
		e_checkdetail.setArgueStatus(1);
		checkdetailDao.update(e_checkdetail);
		//更新表头
		E_checkhead e_checkhead=checkheadDao.selectByID(checkheadlineid);
		e_checkdetail.setArgueStatus(1);
			//查找该表头下所有的明细表，如果所有明细表都为1，则置为2，表示已全部申诉
		String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, checkheadlineid);
		Integer checknums=0;
		e_checkhead.setArgueStatus(1);
		for(int i=0;i<checkdetails.size();i++){
			if(checkdetails.get(i).getArgueStatus()==1)
				checknums++;
		}
		if(checknums==checkdetails.size()){
			e_checkhead.setArgueStatus(2);
		}
		checkheadDao.update(e_checkhead);
		//创建申诉单
		SqlInterface sqlInterface=new SqlInterface(); 
		data.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ARGUE"));
		data.setCheckheadLineid(checkheadlineid);
		data.setCheckdetailLineid(checkdetaillineid);
		data.setArgueStatus(1);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		data.setCreateUser(username);
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestampnow);
		argueDao.insert(data);
		return data;
	}
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:处理申诉单
	 */
	@SuppressWarnings("static-access")
	public E_argue possess(E_argue data){
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		E_checkdetail e_checkdetail=checkdetailDao.selectByID(data.getCheckdetailLineid());
		//更新明细表
		e_checkdetail.setArgueStatus(1);
		checkdetailDao.update(e_checkdetail);
		//更新表头
		E_checkhead e_checkhead=checkheadDao.selectByID(data.getCheckheadLineid());
		e_checkdetail.setArgueStatus(1);
			//查找该表头下所有的明细表，如果所有明细表都为1，则置为2，表示已全部申诉
		String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
		List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, data.getCheckheadLineid());
		Integer checknums=0;
		e_checkhead.setArgueStatus(1);
		for(int i=0;i<checkdetails.size();i++){
			if(checkdetails.get(i).getArgueStatus()==1)
				checknums++;
		}
		if(checknums==checkdetails.size()){
			e_checkhead.setArgueStatus(2);
		}
		checkheadDao.update(e_checkhead);
		//创建申诉单
		SqlInterface sqlInterface=new SqlInterface();
		data.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ARGUE"));
		data.setArgueStatus(1);
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();		
		data.setCreateUser(username);
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		data.setCreateTime(timestampnow);		
		return data;
	}
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_argue> dao = DaoFactory.create(E_argue.class) ;
		Dao<E_checkdetail> checkdetailDao = DaoFactory.create(E_checkdetail.class) ;
		Dao<E_checkhead> checkheadDao = DaoFactory.create(E_checkhead.class) ;
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_argue> result=dao.selectPageByCondition(conditions,
				condition,context.getPageable() ,context.getSortable(), true);
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		List<E_argue> lists=result.getContents();
		for(int i=0;i<lists.size();i++){
			Map<String, String> map=new HashMap<String, String>();
			E_argue tempArgue=lists.get(i);
			map.put("lineid", tempArgue.getLineid());
			map.put("checkheadLineid", tempArgue.getCheckheadLineid());
			map.put("checkdetailLineid", tempArgue.getCheckheadLineid());
			map.put("argueReson", tempArgue.getArgueReson());
			E_checkdetail tempCheckdetail=checkdetailDao.selectByID(tempArgue.getCheckdetailLineid());
			E_checkhead tempCheckhead=checkheadDao.selectByID(tempArgue.getCheckheadLineid());
			map.put("operateName", tempCheckdetail.getOperateName());
			map.put("itemName", tempCheckdetail.getItemName());
			map.put("shipperName", tempCheckhead.getShipperName());
			map.put("checkdetailLineid", tempCheckdetail.getLineid());
			map.put("checkMemo", tempCheckdetail.getCheckMemo());
			map.put("argueTime", tempCheckhead.getArgueTime().toString());
			map.put("checkNumber", tempCheckdetail.getCheckNumber().toString());
			map.put("checkMoney", tempCheckdetail.getCheckMoney().toString());
			map.put("argueStatus", tempArgue.getArgueStatus().toString());
			map.put("approveUsername", tempArgue.getApproveUsername());
			map.put("createTime", tempArgue.getCreateTime().toString());
			map.put("countmonth", tempCheckhead.getCountmonth());
			map.put("approveMemo", tempArgue.getApproveMemo());
			if(tempArgue.getApproveTime()!=null)
			map.put("approveTime", tempArgue.getApproveTime().toString());
			if(tempArgue.getApproveStatus()!=null)
			map.put("approveStatus", tempArgue.getApproveStatus().toString());
			map.put("createTime", tempArgue.getCreateTime().toString());
			map.put("createUser", tempArgue.getCreateUser());
			map.put("createUser", tempArgue.getCreateUser());
			updateList.add(map);
		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(result.getTotal());
		result1.setContents(updateList);
		return result1;
	}
	/**
	 * 
	 * @param idList
	 * @return
	 * @Description:取消申诉
	 */
	@SuppressWarnings("static-access")
	public boolean cancelArgue(List<Integer> idList){
		Dao<E_argue> dao = DaoFactory.create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		List<E_argue> argues=new ArrayList<E_argue>();
		for(int i=0;i<idList.size();i++){
			argues.add(dao.selectByID(idList.get(i)));
		}
		//将申诉单设置为0：未申诉
		for(int i=0;i<idList.size();i++){
			E_argue e_argue=argues.get(i);
			e_argue.setArgueStatus(0);
			dao.update(e_argue);
			E_checkdetail e_checkdetail=checkdetailDao.selectByID(e_argue.getCheckdetailLineid());
			e_checkdetail.setArgueStatus(0);
			//将考核单明细表设为0;
			checkdetailDao.update(e_checkdetail);
			//检测表头，如果还有别的明细表为1的话，设为1，结束
			E_checkhead e_checkhead=checkheadDao.selectByID(e_argue.getCheckheadLineid());		
			String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, e_checkhead.getLineid());
			boolean flag=false;
			for(int j=0;j<checkdetails.size();j++){
				if(checkdetails.get(j).getArgueStatus()==1){
					flag=true;
					break;					
				}

			}
			if(!flag){
				e_checkhead.setArgueStatus(0);
				checkheadDao.update(e_checkhead);
			}
		}
		return true;
	}
	/**
	 * 
	 * @param e_argue
	 * @param e_revise
	 * @param e_substract
	 * @return
	 * @Description:申诉处理
	 */
	public boolean ArgueDeal(E_argue e_argue,E_revise e_revise,E_substract e_substract){
		Dao<E_argue> argueDao = DaoFactory.create(E_argue.class);
		Dao<E_revise> reviseDao = DaoFactory.create(E_revise.class);
		Dao<E_substract> substractDao = DaoFactory.create(E_substract.class);
		//更新申诉单状态
		E_argue e_argue2=argueDao.selectByID(e_argue.getLineid());
		e_argue2.setArgueStatus(e_argue.getArgueStatus());
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();		
		e_argue2.setApproveUsername(username);
		e_argue2.setApproveMemo(e_argue.getApproveMemo());
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_argue2.setApproveTime(timestampnow);
		e_argue2.setApproveStatus(1);
		try {
			argueDao.update(e_argue2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		//生成整改单
		if(e_revise!=null){
			E_revise e_revise2=new E_revise();
			String checkheadid=e_argue2.getCheckheadLineid();
			String checkdetail=e_argue2.getCheckdetailLineid();
			e_revise2.setCheckheadLineid(checkheadid);
			e_revise2.setCheckdetailLineid(checkdetail);
			e_revise2.setArgueLineid(e_argue.getLineid());
			e_revise2.setReviseDescription(e_revise.getReviseDescription());
			e_revise2.setReviseContent(e_revise.getReviseContent());
			e_revise2.setDeadDate(e_revise.getDeadDate());		
			e_revise2.setApproveUsername(username);
			e_revise2.setApproveMemo(e_argue2.getApproveMemo());
			e_revise2.setApproveTime(e_argue2.getApproveTime());
			e_revise2.setApproveStatus(e_argue2.getArgueStatus());
			e_revise2.setReviseStatus(0);
			SqlInterface sqlInterface=new SqlInterface();
			e_revise2.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_REVISE"));
			Timestamp timestampnow1=new Timestamp(System.currentTimeMillis());
			e_revise2.setCreateTime(timestampnow1);
			e_revise2.setCreateUser(username);
			try {
				reviseDao.insert(e_revise2);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}		
		}
		//生成减免单
		if(e_substract!=null){
			E_substract e_substract2=new E_substract();
			SqlInterface sqlInterface1=new SqlInterface();
			e_substract2.setLineid(sqlInterface1.getNextVal("SEQ_TPS_TPS_SUBSTRACT"));
			e_substract2.setSubFrom(1);
			e_substract2.setSubFromid(e_argue2.getLineid());
			e_substract2.setCheckheadLineid(e_argue2.getCheckheadLineid());
			e_substract2.setSubMoney(e_substract.getSubMoney());
			e_substract2.setSubReson(e_substract.getSubReson());
			e_substract2.setCreateTime(timestampnow);
			e_substract2.setCreateUser(username );
			try {
				substractDao.insert(e_substract2);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		return true;
	}
	/**
	 * 
	 * @param data
	 * @param idList
	 * @return
	 * @Description:处理申诉单
	 */
	@SuppressWarnings("static-access")
	public E_argue possess(E_argue data,List<Integer> idList){
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		for(int i=0;i<idList.size();i++){
			E_checkdetail e_checkdetail=checkdetailDao.selectByID(idList.get(i));
			//更新明细表
			e_checkdetail.setArgueStatus(1);
			checkdetailDao.update(e_checkdetail);
			//更新表头
			E_checkhead e_checkhead=checkheadDao.selectByID(e_checkdetail.getCheckheadLineid());
			e_checkdetail.setArgueStatus(1);
			//查找该表头下所有的明细表，如果所有明细表都为1，则置为2，表示已全部申诉
			String selectByCheckHeadLineidFullPath="com.unlcn.ils.tps.E_checkdetailMapper.selectByCheckHeadLineid";
			List<E_checkdetail> checkdetails=checkdetailDao.getSession().selectList(selectByCheckHeadLineidFullPath, e_checkdetail.getCheckheadLineid());
			Integer checknums=0;
			e_checkhead.setArgueStatus(1);
			for(int j=0;j<checkdetails.size();j++){
				if(checkdetails.get(j).getArgueStatus()==1)
					checknums++;
			}
			if(checknums==checkdetails.size()){
				e_checkhead.setArgueStatus(2);
			}
			checkheadDao.update(e_checkhead);
			E_argue e_argue=new E_argue();
			e_argue.setCheckheadLineid(e_checkdetail.getCheckheadLineid());
			e_argue.setCheckdetailLineid(e_checkdetail.getLineid());
			e_argue.setArgueReson(data.getArgueReson());
			e_argue.setArgueStatus(1);
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			String username = context.getUser().getName();
			e_argue.setCreateUser(username);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_argue.setCreateTime(timestampnow);	
			SqlInterface sqlInterface=new SqlInterface();
			e_argue.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_ARGUE"));
			argueDao.insert(e_argue);
		}
		return data;
	}
	/**
	 * 
	 * @return
	 * @Description:获取最大申诉单编号
	 */
	@SuppressWarnings("static-access")
	public String getMaxId(){
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		String getMaxIdFullPath="com.unlcn.ils.tps.E_argueMapper.getMaxId";
		List<E_argue> maxIdFrozenheads=argueDao.getSession().selectList(getMaxIdFullPath);
		return maxIdFrozenheads.get(0).getLineid();
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @Description:根据考核单明细主键查询
	 */
	@SuppressWarnings("static-access")
	public String selectByCheckDetailLineidn(String id){
		String getMaxIdFullPath="com.unlcn.ils.tps.E_argueMapper.selectByCheckDetailLineidn";
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		List<E_argue> argues=argueDao.getSession().selectList(getMaxIdFullPath,id);
		if(argues.size()==0){
			return "-1";
		}
		return argues.get(0).getLineid();
	}
}
