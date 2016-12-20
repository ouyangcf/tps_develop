 
package com.unlcn.ils.tps.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_account;
import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_join_company;
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

public class AuditCompanyService implements ArrayContentProvider {
	private static  final Logger log = Logger.getLogger(Logger .class);
	@Override 
	
	/**
	 * @Description：自定义列表数据源，用于初审时的页面列表；
	 * 				  将初审表、意向新路表、合作申请表关联一起，创建列表。
	 */
	public Page<Map<String,String>> getElements(ArrayContext context) {
		/*// TODO Auto-generated method stub
		Dao<E_join_intentline> dao = DaoFactory.create(E_join_intentline.class) ;
		Dao<E_join_company> companyDao = DaoFactory.create(E_join_company.class);
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_audit> result=auditDao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		List<E_audit> lists=result.getContents();
		List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
		
		for(int i=0;i<lists.size();i++){
			E_audit e_temp=lists.get(i);

				Map<String, String> map=new HashMap<String, String>();	
				E_join_company e_join_company=companyDao.selectByID(e_temp.getJoinId());
				map.put("lineId", e_temp.getLineid().toString());
				map.put("joinid", e_temp.getJoinId().toString());
				map.put("joinflag", e_temp.getJoinFlag().toString());
				map.put("shipperLineid", e_join_company.getLineid().toString());
				map.put("shipperName", e_join_company.getCompanyName());
				map.put("provinceid", e_join_company.getProvinceId().toString());
				map.put("provincename", e_join_company.getProvince());
				map.put("cityid", e_join_company.getCityId().toString());
				map.put("cityname", e_join_company.getCity());
				map.put("legalUser", e_join_company.getLegalUser());
				map.put("maincontract", e_join_company.getMainContract());
				map.put("taxno", e_join_company.getTaxNo());
				map.put("capital", e_join_company.getCapital().toString());
				map.put("assets", e_join_company.getAssets());
				map.put("contractname", e_join_company.getContractName());
				map.put("createtime", e_join_company.getCreateTime().toString());
				if(e_join_company.getContract()!=null)
				map.put("contract", e_join_company.getContract().toString());
				if(e_join_company.getMobileno()!=null)
				map.put("mobileno", e_join_company.getMobileno().toString());
				
				map.put("createTime", e_join_company.getCreateTime().toString());
				map.put("checkflg", e_join_company.getCheckFlag().toString());
				
				if(e_join_company.getCheckFlag()!=0){
				map.put("audittime", e_temp.getAuditTime().toString());
				map.put("auditflag", e_temp.getAuditFlag().toString());
				map.put("auditmemo", e_temp.getAuditMemo());				
				}
				updateList.add(map);				
				
				

		}
		Page<Map<String,String>> result1=new Page<Map<String,String>>();
		result1.setPageIndex(result.getPageIndex());
		result1.setPageSize(result.getPageSize());
		result1.setTotal(updateList.size());
		result1.setContents(updateList);
		return result1;*/
		
		



		Dao<E_join_company> companyDao = DaoFactory.create(E_join_company.class);
				Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
				Map<String, Object> conditions = context.getCondition();
				//前台传入filters(JSON)
				Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
				Page<E_audit> result=auditDao.selectPageByCondition(conditions,
								condition,context.getPageable() ,context.getSortable(), true);
				List<E_audit> lists=result.getContents();
				List<Map<String,String>> updateList=new ArrayList<Map<String,String>>();
				Integer nums=0;
			
				for(int i=0;i<lists.size();i++){
					E_audit e_temp=lists.get(i);
					Map<String, String> map=new HashMap<String, String>();	
					E_join_company e_join_company=companyDao.selectByID(e_temp.getJoinId());

					map.put("lineId", e_temp.getLineid());
					map.put("joinid", e_temp.getJoinId());
					map.put("joinflag", e_temp.getJoinFlag().toString());
					map.put("shipperLineid", e_join_company.getLineid().toString());
					map.put("shipperName", e_join_company.getCompanyName());
					map.put("provinceid", e_join_company.getProvinceId().toString());
					map.put("provincename", e_join_company.getProvince());
					map.put("cityid", e_join_company.getCityId().toString());
					map.put("cityname", e_join_company.getCity());
					map.put("legalUser", e_join_company.getLegalUser());
					map.put("maincontract", e_join_company.getMainContract());
					map.put("taxno", e_join_company.getTaxNo());
					map.put("capital", e_join_company.getCapital().toString());
					if(e_join_company.getIdNumber() != null){
						map.put("idNumber", e_join_company.getIdNumber());
					}
					if (e_join_company.getRoadLicence() != null) {
						map.put("roadLicence", e_join_company.getRoadLicence());
					}
					if(e_join_company.getAssets()!=null){
						map.put("assets", e_join_company.getAssets());
					}
					if(e_join_company.getCarNumber()!=null){
						map.put("carNumber", e_join_company.getCarNumber().toString());
					}
					map.put("contractname", e_join_company.getContractName());
					map.put("createtime", e_join_company.getCreateTime().toString());
					if(e_join_company.getContract()!=null)
					map.put("contract", e_join_company.getContract().toString());
					if(e_join_company.getMobileno()!=null)
					map.put("mobileno", e_join_company.getMobileno().toString());
					
					map.put("createTime", e_join_company.getCreateTime().toString());
					map.put("checkflg", e_join_company.getCheckFlag().toString());
					
					if(e_join_company.getCheckFlag()!=0){
					map.put("audittime", e_temp.getAuditTime().toString());
					map.put("auditflag", e_temp.getAuditFlag().toString());
					map.put("auditmemo", e_temp.getAuditMemo());
					if(e_temp.getBeginDate()!=null)
					map.put("begindata", e_temp.getBeginDate().toString());
					if(e_temp.getEndDate()!=null)
					map.put("enddata", e_temp.getEndDate().toString());
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
	 * @Description：认证操作后更新分供方合作初审表
	 * @param data
	 * @return
	 */
	public boolean updateAuditCompany(E_audit data){
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		String username = context.getUser().getName();
		Dao<E_audit> auditDao = DaoFactory.create(E_audit.class);
		E_audit e_audit=auditDao.selectByID(data.getLineid());
		Dao<E_join_company> companyDao = DaoFactory.create(E_join_company.class);
		Dao<E_account>accountDao = DaoFactory.create(E_account.class);
		Dao<E_linkman>linkmanDao = DaoFactory.create(E_linkman.class);
		Dao<E_join_intentline>joininterlineDao = DaoFactory.create(E_join_intentline.class);
		Dao<E_join_file>joinFliedDao = DaoFactory.create(E_join_file.class);
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
		//更新合作申请表的状态
		E_join_company e_join_company=companyDao.selectByID(e_audit.getJoinId());
		e_join_company.setCheckFlag(e_audit.getAuditFlag());
		companyDao.update(e_join_company);
		//认证通过的，推送到crm新增或更新
		E_account e_account = new E_account();
		e_account.setCustomerId(e_audit.getJoinId());
		e_account.setFlag(1);
		List<E_account>accountlist = accountDao.select(e_account);
		E_linkman e_linkman = new E_linkman();
		e_linkman.setCustomerId(e_audit.getJoinId());
		e_account.setFlag(1);
		List<E_linkman>linkmanlist = linkmanDao.select(e_linkman);
		E_join_intentline e_join_intentline = new E_join_intentline();
		e_join_intentline.setJoinId(e_audit.getJoinId());
		e_join_intentline.setJoinFlag(1);
		List<E_join_intentline>joininterlinlist = joininterlineDao.select(e_join_intentline);
		E_join_file e_join_file =new E_join_file();
		e_join_file.setSourceId(e_audit.getJoinId());
		e_join_file.setFlag(1);
		List<E_join_file>idFilePathList = joinFliedDao.select(e_join_file);
		String idFilePath =  idFilePathList.get(0).getFilePath();
		log.info("营业执照地址为："+idFilePath);
		e_join_file.setFlag(2);
		List<E_join_file>roadLicenceList = joinFliedDao.select(e_join_file);
		String roadLicencePath = roadLicenceList.get(0).getFilePath();
		log.info("道路经营许可证地址为："+roadLicencePath);
		
		if(e_join_company.getCheckFlag()==3){
			if(e_join_company.getShipperId()==null){
				Result result=new Result(1, null, "");
				try {
					//推送到crm
					log.info("认证通过的，推送到crm新增");
					CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
					result=crmInformationInterface.addShipper2Crm_c(e_join_company,linkmanlist,accountlist,joininterlinlist,idFilePath,roadLicencePath);
					log.info("返回结果为: "+result.getRetCode());
					if(result.getRetCode()==-1){
						log.info("调用失败,错误码为: "+result.getErrCode());
						log.info("调用失败,错误信息为: "+result.getErrDesc());
						e_join_company.setCheckFlag(0);
						e_audit.setAuditFlag(0);
						companyDao.update(e_join_company);
						auditDao.update(e_audit);
						throw new RuntimeException("crm方新增分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						log.info("调用成功，返回的分供方id为："+result.getMap().get("id"));
					}
					e_join_company.setShipperId(result.getMap().get("id"));
					companyDao.update(e_join_company);					
				} catch (Exception e) {
					// TODO: handle exception
					// TODO: handle exception
					e_join_company.setCheckFlag(0);
					e_audit.setAuditFlag(0);
					companyDao.update(e_join_company);
					auditDao.update(e_audit);	
					if(result.getRetCode()==-1){
						throw new RuntimeException("crm方新增分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						throw new RuntimeException("更新失败,请重试");
					}
				}
			}
			else{
				CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
				Result result=crmInformationInterface.updateShipper2Crm_c(e_join_company);
				log.info("获取状态码: "+result.getRetCode());
			}
		}
		return false;
	}
}
