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
import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.unlcn.ils.crm.customer.linkman.Linkman;
import com.unlcn.ils.crm.dto.Result;
import com.unlcn.ils.crm.service.CrmInformationService;
import com.unlcn.ils.tps.E_linkman;
public class LinkmanInterface { 
	private static  final Logger log = Logger.getLogger(Logger .class);
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	/**
	 * 
	 * @param linkman 
	 * @return
	 * @Description:从接口获取联系人信息 
	 */    
	public List<Linkman> getLinkmanByid(Linkman linkman){
 
		/*List<Linkman> returnList=new ArrayList<Linkman>();
		Linkman linkman2=new Linkman();
		linkman2.setJob("分供方0");
		linkman2.setName("吉安");
		returnList.add(linkman2);*/
		CrmInformationService crmInformationService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		List<Linkman> returnList=crmInformationService.getLinkmanByid(linkman);
		return returnList;
	}
	/**
	 * 
	 * @param linkman
	 * @return
	 * @Description:将从接口得到的联系人信息输出
	 */
	public List<Map<String, String>> getLinkmanByidFromCrm(Linkman linkman,String lineid){
		List<Linkman> lists=getLinkmanByid(linkman);
		List<Map<String,String>> returnlist=new ArrayList<Map<String,String>>();
		for(int i=0;i<lists.size();i++)
		{
			Map<String,String> map=new HashMap<String,String> ();
			Linkman linkmanlist = lists.get(i);
			if(linkmanlist.getId()!=null){
				map.put("id", linkmanlist.getId().toString());
			}
			if(linkmanlist.getCustomerId()!=null){
				//CustomerInfo customerInfo = new CustomerInfo();
				//customerInfo.setId(lineid);
				map.put("customerId", linkmanlist.getCustomerId().getId());
				//linkmanlist.getCustomerId().getId();
			}
			if(linkmanlist.getServiceType()!=null){
				map.put("serviceType", linkmanlist.getServiceType());
			}
			if(linkmanlist.getDepartment()!=null);
			map.put("department", linkmanlist.getDepartment());
			if(linkmanlist.getJob()!=null)
			map.put("job", linkmanlist.getJob());
			if(linkmanlist.getName()!=null)
			map.put("name", linkmanlist.getName());
			if(linkmanlist.getPhone()!=null)
			map.put("phone", linkmanlist.getPhone());
			if(linkmanlist.getTel()!=null)
			map.put("tel", linkmanlist.getTel());
			if(linkmanlist.getQq()!=null)
			map.put("qq", linkmanlist.getQq());
			if(linkmanlist.getEmail()!=null)
			map.put("email", linkmanlist.getEmail());
			returnlist.add(map);
	}
		return returnlist;
	}
	public boolean updateLinkman2Crm_dlg(List<E_linkman>listLinkmanList,String csdmid) {
		Result result = new Result(1, null, "");
		log.info("更新后的，推送到Crm");
		LinkmanInterface linkmanInterface=new LinkmanInterface();
		try {
			result=linkmanInterface.updateLinkman2Crm(listLinkmanList,csdmid);
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
	/**
	 * 
	 * @param listLinkmanList
	 * @return
	 * @Description:处理更新后的联系人信息
	 */
	private Result updateLinkman2Crm(List<E_linkman>listLinkmanList,String csdmid) {
		Result result = new Result(1, null, "");
		log.info("开始处理联系人信息");
		for (E_linkman e_linkman : listLinkmanList) {
			Linkman linkman = new Linkman();
			if(e_linkman.getServiceType()!=null){
				//linkman.setServiceType(e_linkman.getServiceType());
				linkman.setServiceType("无");
			}
			linkman.setServiceType("无");
			if(e_linkman.getDepartment()!=null){
				linkman.setDepartment(e_linkman.getDepartment());
			}
			if(e_linkman.getJob()!=null){
				linkman.setJob(e_linkman.getJob());
			}
			if(e_linkman.getName()!=null){
				linkman.setName(e_linkman.getName());
			}
			if(e_linkman.getPhone()!=null){
				linkman.setPhone(e_linkman.getPhone());
			}
			if(e_linkman.getTel()!=null){
				linkman.setTel(e_linkman.getTel());
			}
			if(e_linkman.getEmail()!=null){
				linkman.setEmail(e_linkman.getEmail());
			}
			if(e_linkman.getQq()!=null){
				linkman.setQq(e_linkman.getQq());
			}
			linkman.setOperUser(username);
			java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
			linkman.setOperTime(currdate);
			/*if(e_linkman.getId()!=null && e_linkman.getCustomerId()!=null){
				log.info("ID不为空，编辑"+e_linkman.getId());
				linkman.setId(e_linkman.getId());
				CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setId(e_linkman.getCustomerId());
				linkman.setCustomerId(customerInfo);
				result = updateLinkman(linkman);
			}else {
				log.info("为空，新增");
				CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setId(csdmid);
				linkman.setCustomerId(customerInfo);
				result = addLinkman(linkman);
			}*/
			if(e_linkman.getId().equals("")|| e_linkman.getCustomerId().equals("")){
				log.info("为空，新增");
				CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setId(csdmid);
				linkman.setCustomerId(customerInfo);
				result = addLinkman(linkman);
			}else {
				log.info("ID不为空，编辑"+e_linkman.getId());
				linkman.setId(e_linkman.getId());
				CustomerInfo customerInfo = new CustomerInfo();
				customerInfo.setId(e_linkman.getCustomerId());
				linkman.setCustomerId(customerInfo);
				result = updateLinkman(linkman);
			}
		}
		return result;
		
	}
	/**
	 * 
	 * @param linkman
	 * @return
	 * @Description:更新后的联系人推送到Crm(编辑)
	 */
	private Result updateLinkman(Linkman linkman) {
		
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		if(dbService!=null){
			log.info("dbservice,不为空");
		}else {
			log.info("服务为空");
		}
		try {
			Result result=  dbService.updateLinkman(linkman);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("网络原因未能推送成功,请重新点击提交按钮");
		}
	}
	/**
	 * 
	 * @param linkman
	 * @return
	 * @Description:更新后的联系人推送到Crm(新增)
	 */
	private Result addLinkman(Linkman linkman) {
		log.info("开始新增");
		CrmInformationService dbService = (CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");
		try {
			Result result= dbService.addLinkman(linkman);
			return result;
		} catch (Exception e) {
			throw new RuntimeException("网络原因未能推送成功,请重新点击提交按钮");
		}
	}
}
