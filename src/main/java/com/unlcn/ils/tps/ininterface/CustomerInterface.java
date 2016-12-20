package com.unlcn.ils.tps.ininterface;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.crm.customer.consigner.Consigner;
import com.unlcn.ils.crm.customer.custgroup.CustomerInfo;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.unlcn.ils.crm.service.CrmInformationService;
 
public class CustomerInterface {   

	static CrmInformationService dbService=(CrmInformationService)ApplicationContextManager.getContext().getBean("crmInformationService");;
	@SuppressWarnings("unused")
	private static Logger logger=Logger.getLogger(Logger.class );
	//得到客户信息   
		public static List<Map<String,String>> getData(Map<String,Object> con)
		{
			List<Map<String,String>> listcustom=new ArrayList<Map<String,String>>();
			Consigner searchconsignor = new Consigner();
		
			List<Consigner> objcustom=dbService.getConsignor(searchconsignor);
			for(int i=0;i<objcustom.size();i++)
			{	
				Map<String,String> map=new HashMap<String,String> ();
				Consigner customer = objcustom.get(i);
				if ( customer.getCustomerId() == null) continue;
				map.put("id", customer.getCustomerId().getId());
				map.put("name", customer.getName());
				listcustom.add(map);
			}
			return listcustom;
		}
		public static Map<String,String> getCustomerByID(String customerid)
		{
			Map<String,String> map=new HashMap<String,String> ();
			Consigner searchconsignor = new Consigner();
			CustomerInfo info = new CustomerInfo();
			info.setId("C"+customerid);
			searchconsignor.setCustomerId(info);
			
			List<Consigner> objcustom=dbService.getConsignor(searchconsignor);
			if (objcustom.size()>0 )
			{
				Consigner customer = objcustom.get(0);
				map.put("id", customer.getCustomerId().getId());
				map.put("name", customer.getName());
				return map;
			}else return null;
		}
		
}
