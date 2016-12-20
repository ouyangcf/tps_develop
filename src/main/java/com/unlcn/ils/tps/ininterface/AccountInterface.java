package com.unlcn.ils.tps.ininterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.customer.account.Account;
import com.unlcn.ils.crm.dto.Result;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.tps.E_account;
 
public class AccountInterface {
	private static  final Logger log = Logger.getLogger(Logger .class);
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	/**
	 * 
	 * @param account   
	 * @return 
	 * @Description:从接口获取账户信息  
	 */
	public List<Account> getAccountByid(Account account){
		 
		/*List<Account> returnList=new ArrayList<Account>();
		Account dto=new Account();
		dto.setName("分供方0");
		dto.setOwnerType("1");
		returnList.add(dto);*/
		CrmInformationService crmInformationService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		List<Account> returnList=crmInformationService.getAccountByid(account);
		return returnList; 
	}
	public void deleteAccount() {
		CrmInformationService crmInformationService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		Result returnList=crmInformationService.deleteAccount("1");
	}
	/**
	 * 
	 * @param account
	 * @return
	 * @Description:将从接口得到的账户信息输出
	 */
	
	public List<Map<String, String>> getAccountByidFromCrm(Account account){
		List<Account> lists=getAccountByid(account);
		List<Map<String,String>> returnlist=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++)
		{
			Map<String,String> map=new HashMap<String,String> ();
			Account accountlist = lists.get(i);
			if(accountlist.getId()!=null){
				map.put("id", accountlist.getId().toString());
			}
			if(accountlist.getCustomerId()!=null){
				map.put("customerId", accountlist.getCustomerId().toString());
			}
			if(accountlist.getStatus()!=null){
				map.put("status", accountlist.getStatus().toString());
			}
			if(accountlist.getAccountFilepath()!=null){
				map.put("accountFilepath", accountlist.getAccountFilepath().toString());
			}
			if(accountlist.getType()!=null)
				map.put("type", accountlist.getType());
			if(accountlist.getOwnerType()!=null)
			map.put("ownerType", accountlist.getOwnerType());
			if(accountlist.getName()!=null)
			map.put("name", accountlist.getName());
			if(accountlist.getBank()!=null)
			map.put("bank", accountlist.getBank());
			if(accountlist.getAccountNumber()!=null)
			map.put("accountNumber", accountlist.getAccountNumber());
			if(accountlist.getOwnerName()!=null)	
			map.put("ownerName", accountlist.getOwnerName());
			if(accountlist.getBankOpenBranch()!=null)
				map.put("bankOpenBranch", accountlist.getBankOpenBranch());
			if(accountlist.getDesc()!=null)
			map.put("desc", accountlist.getDesc());
			returnlist.add(map);
	}
		return returnlist;
	}
	public boolean updateAccount2Crm_dlg(List<E_account>listAccountList,String cusdmid) {
		Result result = new Result(1, null, "");
		log.info("更新后的，推送到Crm");
		AccountInterface accountInterface = new AccountInterface();
		try {
			result=accountInterface.updateAccountn2Crm(listAccountList,cusdmid);
			log.info("返回结果为: "+result.getRetCode());
			if(result.getRetCode()==-1){
				log.info("调用失败,错误码为: "+result.getErrCode());
				log.info("调用失败,错误信息为: "+result.getErrDesc());
			}
			}catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException("crm方新增分供方失败,错误原因为:"+result.getErrDesc());
			}
		return true;
		
	}
	private Result updateAccountn2Crm(List<E_account>listAccountList,String cusdmid) {
		Result result = new Result(1, null, "");
		for (E_account e_account : listAccountList) {
			Account account = new Account();
			account.setOwnerType(e_account.getOwnerType());
			account.setName(e_account.getName());
			account.setBank(e_account.getBank());
			account.setAccountNumber(e_account.getAccountNumber());
			if(e_account.getAccountFilepath()!=null){
				account.setAccountFilepath(e_account.getAccountFilepath());
			}
			account.setBankOpenBranch(e_account.getBankOpenBranch());
			if(e_account.getOwnerName()!=null){
				account.setOwnerName(e_account.getOwnerName());
			}
			if(e_account.getDesc()!=null){
				account.setDesc(e_account.getDesc());
			}
/*			if(e_account.getStartTime()!=null){
				account.setStartTime(e_account.getStartTime());
			}
			if(e_account.getEndTime()!=null){
				account.setEndTime(e_account.getEndTime());
			}*/
			account.setType(e_account.getType());
			account.setOperUser(username);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			account.setOperTime(currdate);
			if(e_account.getId().equals("")||e_account.getCustomerId().equals("")){
				/*CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setId(cusdmid);*/
				account.setCustomerId(cusdmid);
				//预存接口，等待更新
				//result = addAccount(account);
			}else {
				account.setId(e_account.getId());
				account.setCustomerId(e_account.getCustomerId());
				//预存接口，等待更新
				//result = updateAccount(account);
			}
		}
		return result;
		
	}
/*	private Result updateAccount(Account account) {
		log.info("ID不为空，开始编辑");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService!=null){
			log.info("dbservice,不为空");
		}else {
			log.info("服务为空");
		}
		try {
			Result result=  dbService.updateAccount(account);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("网络原因未能推送成功,请重新点击提交按钮");
		}	
	}
	private Result addAccount(Account account) {
		log.info("开始新增");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		try {
			Result result= dbService.addAccount(account);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("网络原因未能推送成功,请重新点击提交按钮");
		}
	}*/
}
