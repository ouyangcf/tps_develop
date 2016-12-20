package com.unlcn.ils.tps.services;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_account;
import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_file;
import com.unlcn.ils.tps.E_join_intentline;
import com.unlcn.ils.tps.E_linkman;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.crm.dto.Result; 
/**
 * @Description：自定义列表数据源，用于初审时的页面列表；
 * @Description：将初审表、意向新路表、加盟申请表关联一起，创建列表。
 */
public class AuditDriverService implements ArrayContentProvider{
	private static  final Logger log = Logger.getLogger(Logger .class);
	@Override  
	public Page<Map<String,String>> getElements(ArrayContext context) {
		Dao<E_join_driver> driveDao = DaoFactory.create(E_join_driver.class);
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_audit> result=auditDao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_audit> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		Integer nums=0;
		//下面进行过期检测
/*		Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
		boolean updateFlag=false;
		for(int i=0;i<lists.size();i++){
			//先做过期检测
			E_audit temp=lists.get(i);
			if(temp.getAuditFlag()==0)
				continue;
			if(temp.getEndDate().before(timestampnow)){
				temp.setAuditFlag(5);
				auditDao.update(temp);
				//同时更新相应的司机公司表
				if(temp.getJoinFlag()==0){
					E_join_driver tempDriver=driveDao.selectByID(temp.getJoinId());
					tempDriver.setCheckFlag(5);
					driveDao.update(tempDriver);
				}
				updateFlag=true;
			}
		}
		if(updateFlag==true){
			lists.clear();
			result=auditDao.selectPageByCondition(conditions,
			condition,context.getPageable() ,context.getSortable(), true);	
			lists=result.getContents();
		}	*/
		for(int i=0;i<lists.size();i++){
			E_audit e_temp=lists.get(i);
			Map<String, String> map=new HashMap<String, String>();	
			E_join_driver e_join_driver=driveDao.selectByID(e_temp.getJoinId());
			map.put("lineId", e_temp.getLineid());
			map.put("licenseno", e_join_driver.getLicenseno());
			map.put("vehiclelicenseno", e_join_driver.getVehicleLicenseno());
			map.put("cardno", e_join_driver.getCardno());
			if(e_join_driver.getMobileno()!=null)
			map.put("mobileno", e_join_driver.getMobileno());
			map.put("createtime", e_join_driver.getCreateTime().toString());
			map.put("joinid", e_temp.getJoinId());
			map.put("joinflag", e_temp.getJoinFlag().toString());
			map.put("province", e_join_driver.getProvince());
			map.put("provinceid", e_join_driver.getProvinceId().toString());
			map.put("city", e_join_driver.getCity());
			map.put("cityid", e_join_driver.getCityId().toString());
			map.put("checkflg", e_join_driver.getCheckFlag().toString());
			if(e_join_driver.getCarNumber()!=null){
				map.put("carNumber", e_join_driver.getCarNumber().toString());
			}
			map.put("driverName", e_join_driver.getDriverName());
			if(e_join_driver.getCheckFlag()!=0){
			map.put("audittime", e_temp.getAuditTime().toString());
			map.put("auditflag", e_temp.getAuditFlag().toString());
			map.put("auditmemo", e_temp.getAuditMemo());
			if(e_temp.getBeginDate()!=null)
			map.put("begindata", e_temp.getBeginDate().toString());
			if(e_temp.getEndDate()!=null)
			map.put("enddata", e_temp.getEndDate().toString());
			//map.put("ctime", e_temp.getCreateTime().toString());
			}
			nums++;
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
	 * @Description：认证操作后更新分供方加盟初审表
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean updateAuditDriver(E_audit data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		E_audit e_audit=auditDao.selectByID(data.getLineid()); 
		Dao<E_join_driver> driverDao = DaoFactory.create(E_join_driver.class);
		//意向线路、账户和联系人DAO
		Dao<E_account> accountDao = DaoFactory.create(E_account.class);
		Dao<E_linkman> linkmanDao = DaoFactory.create(E_linkman.class);
		Dao<E_join_intentline>joininterlinedDao = DaoFactory.create(E_join_intentline.class);
		Dao<E_join_file>joinFileDao = DaoFactory.create(E_join_file.class);
		
		boolean timeoutFlag=false;
		if(e_audit.getAuditFlag()==5)
			timeoutFlag=true;
		//认证通过时
		if(data.getAuditFlag()==3){
			e_audit.setAuditFlag(data.getAuditFlag());
			e_audit.setAuditTool(data.getAuditTool());			
			e_audit.setAuditMemo(data.getAuditMemo());
			e_audit.setAuditUser(username);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_audit.setAuditTime(timestampnow);
			e_audit.setBeginDate(data.getBeginDate());
			e_audit.setEndDate(data.getEndDate());
		}
		//认证不通过时
		if(data.getAuditFlag()==4){
			e_audit.setAuditFlag(data.getAuditFlag());
			e_audit.setAuditTool(data.getAuditTool());			
			e_audit.setAuditMemo(data.getAuditMemo());
			e_audit.setAuditUser(username);
			Timestamp timestampnow=new Timestamp(System.currentTimeMillis());
			e_audit.setAuditTime(timestampnow);
		}
		auditDao.update(e_audit);
		//更新加盟申请表的状态
		E_join_driver e_join_driver=driverDao.selectByID(e_audit.getJoinId());
		e_join_driver.setCheckFlag(e_audit.getAuditFlag());
		driverDao.update(e_join_driver);
		//认证通过的，推送到crm新增或更新
		
		E_account e_account = new E_account();
		e_account.setCustomerId(e_audit.getJoinId());
		e_account.setFlag(0);
		List<E_account>accountlist = accountDao.select(e_account);
		log.info("账户信息的个数为："+accountlist.size());
		E_linkman e_linkman = new E_linkman();
		e_linkman.setCustomerId(e_audit.getJoinId());
		e_linkman.setFlag(0);
		List<E_linkman>linkmanlist = linkmanDao.select(e_linkman);
		log.info("联系人信息的个数为："+linkmanlist.size());
		E_join_intentline e_join_intentline = new E_join_intentline();
		e_join_intentline.setJoinId(e_audit.getJoinId());
		e_join_intentline.setJoinFlag(0);
		List<E_join_intentline>joininterlinelist = joininterlinedDao.select(e_join_intentline);
		log.info("意向线路的个数为："+joininterlinelist.size());
		E_join_file e_join_file = new E_join_file();
		e_join_file.setSourceId(e_audit.getJoinId());
		e_join_file.setFlag(0);
		List<E_join_file> idFilePathList = joinFileDao.select(e_join_file);
		String idFilePath =  idFilePathList.get(0).getFilePath();
		log.info("身份证路径为："+idFilePath);
		
		if(e_join_driver.getCheckFlag()==3){
			if(e_join_driver.getShipperId()==null){
				Result result=new Result(1, null, "");
				//推送到crm
				log.info("认证通过的，推送到crm新增");
				CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
				
				try {
					result=crmInformationInterface.addShipper2Crm_d(e_join_driver,accountlist,linkmanlist,joininterlinelist,idFilePath);
					log.info("返回结果为: "+result.getRetCode());
					if(result.getRetCode()==-1){
						log.info("调用失败,错误码为: "+result.getErrCode());
						log.info("调用失败,错误信息为: "+result.getErrDesc());
						e_join_driver.setCheckFlag(0);
						e_audit.setAuditFlag(0);
						driverDao.update(e_join_driver);
						auditDao.update(e_audit);
						
						throw new RuntimeException("crm方新增分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						log.info("调用成功，返回的分供方id为："+result.getMap().get("id"));
					}
					e_join_driver.setShipperId(result.getMap().get("id"));
					driverDao.update(e_join_driver);					
				} catch (Exception e) {
					// TODO: handle exception
					e_join_driver.setCheckFlag(0);
					e_audit.setAuditFlag(0);
					driverDao.update(e_join_driver);
					auditDao.update(e_audit);
					if(result.getRetCode()==-1){
						throw new RuntimeException("crm方新增分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						throw new RuntimeException("更新失败,请重试");
					}
				}

			}
			else {
				log.info("认证通过的，推送到crm更新");
				CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
				Result result=crmInformationInterface.updateShipper2Crm_d(e_join_driver);
				log.info("获取状态码: "+result.getRetCode());
			}
		}
		
		return false;
	}
}
