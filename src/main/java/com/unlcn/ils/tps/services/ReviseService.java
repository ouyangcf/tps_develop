package com.unlcn.ils.tps.services;

import java.sql.Timestamp ; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

import com.unlcn.ils.tps.E_argue;
import com.unlcn.ils.tps.E_checkdetail;
import com.unlcn.ils.tps.E_checkdocument;
import com.unlcn.ils.tps.E_checkhead;
import com.unlcn.ils.tps.E_revise;
import com.unlcn.ils.tps.ininterface.SqlInterface;
/**
 * 
 *@Title:
 *@Description:整改单服务 
 *@Author:Administrator
 *@Since:2016-8-18
 *@Version:1.1.0
 */
public class ReviseService implements ArrayContentProvider{
	/**
	 * 
	 * @param lineid
	 * @param e_revise
	 * @return
	 * @Description:增加整改单
	 */
	public boolean addRevise(String lineid,E_revise e_revise){
		Dao<E_argue> argueDao = DaoFactory.create(E_argue.class);
		Dao<E_revise> reviseDao = DaoFactory.create(E_revise.class);
		E_argue e_argue=argueDao.selectByID(lineid);
		E_revise e_revise2=new E_revise();
		String checkheadid=e_argue.getCheckheadLineid();
		String checkdetail=e_argue.getCheckdetailLineid();
		e_revise2.setArgueLineid(lineid);
		e_revise2.setCheckheadLineid(checkheadid);
		e_revise2.setCheckdetailLineid(checkdetail);
		e_revise2.setReviseDescription(e_revise.getReviseDescription());
		e_revise2.setReviseContent(e_revise.getReviseContent());
		e_revise2.setDeadDate(e_revise.getDeadDate());
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		e_revise2.setApproveUsername(username);
		e_revise2.setReviseStatus(0);
		e_revise2.setCreateUser(username);
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		e_revise2.setCreateTime(timestampnow);
		if(e_argue.getApproveMemo()==null){
			e_revise2.setApproveMemo("");
		}
		else
		e_revise2.setApproveMemo(e_argue.getApproveMemo());
		e_revise2.setApproveTime(e_argue.getApproveTime());
		e_revise2.setApproveStatus(e_argue.getArgueStatus());
		SqlInterface sqlInterface=new SqlInterface();
		e_revise2.setLineid(sqlInterface.getNextVal("SEQ_TPS_TPS_REVISE"));
		try {
			reviseDao.insert(e_revise2);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}	
		return true;
	}

	@SuppressWarnings("static-access")
	@Override
	public Page<Map<String,String>> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_revise> dao = DaoFactory.create(E_revise.class) ;
		Dao<E_argue> argueDao=new DaoFactory().create(E_argue.class);
		Dao<E_checkhead> checkheadDao=new DaoFactory().create(E_checkhead.class);
		Dao<E_checkdetail> checkdetailDao=new DaoFactory().create(E_checkdetail.class);
		Dao<E_checkdocument> checkdocDao=new DaoFactory().create(E_checkdocument.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_revise> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		//下面进行过期检测
		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		boolean updateFlag=false;
		List<E_revise> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++){
			//先做过期检测
			E_revise tempE_revise=lists.get(i);
			if(tempE_revise.getReviseStatus()==0||tempE_revise.getReviseStatus()==1)
			if(tempE_revise.getDeadDate().before(timestampnow)){
				tempE_revise.setReviseStatus(5);
				dao.update(tempE_revise);
				updateFlag=true;
			}
		}
		if(updateFlag==true){
			lists.clear();
			result=dao.selectPageByCondition(conditions,
					condition,context.getPageable() ,context.getSortable(), true);	
			lists=result.getContents();
		}
		for(int i=0;i<lists.size();i++){			
			//根据申诉单找到checkdetail以及checkhead
			Map<String, String> map=new HashMap<String, String>();
			E_revise tempE_revise=lists.get(i);
			E_argue tempA=argueDao.selectByID(lists.get(i).getArgueLineid());
			E_checkdetail tempCheckdetail=checkdetailDao.selectByID(tempA.getCheckdetailLineid());
			E_checkhead tempCheckhead=checkheadDao.selectByID(tempA.getCheckheadLineid());
			E_checkdocument tempCheckdoc=checkdocDao.selectByID(tempCheckdetail.getCheckdocLineid());
			map.put("approveMemo", tempE_revise.getApproveMemo());
			map.put("approveStatus", tempE_revise.getApproveStatus().toString());
			map.put("approveTime", tempE_revise.getApproveTime().toString());
			map.put("approveUsername", tempE_revise.getApproveUsername());
			map.put("argueLineid", tempE_revise.getArgueLineid());
			map.put("argueReson", tempA.getArgueReson());
			map.put("argueStatus", tempA.getArgueStatus().toString());
			map.put("argue_Time", tempCheckhead.getArgueTime().toString());
			map.put("checkDocLineid", tempCheckdetail.getCheckdocLineid());
			map.put("check_Memo", tempCheckdetail.getCheckMemo());
			map.put("checkdetailLineid", tempCheckdetail.getLineid());
			map.put("checkheadLineid", tempE_revise.getCheckheadLineid());
			map.put("contact", tempE_revise.getContact());
			map.put("countMonth", tempCheckhead.getCountmonth());
			map.put("deadDate", tempE_revise.getDeadDate().toString());
			map.put("executeUsername", tempE_revise.getExecuteUsername());
			map.put("item_Name", tempCheckdetail.getItemName());
			map.put("lineid", tempE_revise.getLineid());
			map.put("longcontent", tempE_revise.getLongcontent());
			map.put("operate_Name", tempCheckdetail.getOperateName());
			map.put("precontent", tempE_revise.getPrecontent());
			map.put("reson", tempE_revise.getReson());
			map.put("reviseContent", tempE_revise.getReviseContent());
			map.put("reviseDescription", tempE_revise.getReviseDescription());
			map.put("reviseStatus", tempE_revise.getReviseStatus().toString());
			map.put("shipperName", tempCheckhead.getShipperName());
			map.put("tempcontent", tempE_revise.getTempcontent());
			map.put("verifycontent", tempE_revise.getVerifycontent());
			map.put("Check_Money", tempCheckdetail.getCheckMoney().toString());
			map.put("Check_Score", tempCheckdoc.getCheckScore().toString());
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
	 * @return
	 * @Description:获取最大的id
	 */
	@SuppressWarnings("static-access")
	public String getMaxId(){
		Dao<E_revise> reviseDao=new DaoFactory().create(E_revise.class);
		String getMaxIdFullPath="com.unlcn.ils.tps.E_reviseMapper.getMaxId";
		List<E_revise> maxReviseId=reviseDao.getSession().selectList(getMaxIdFullPath);
		return maxReviseId.get(0).getLineid();
	}
}

