package com.unlcn.ils.tps.services;
 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.unlcn.ils.tps.E_account;
import com.unlcn.ils.tps.E_audit;
import com.unlcn.ils.tps.E_city;
import com.unlcn.ils.tps.E_currentlevel;
import com.unlcn.ils.tps.E_join_company;
import com.unlcn.ils.tps.E_join_driver;
import com.unlcn.ils.tps.E_join_file;
import com.unlcn.ils.tps.E_join_intentline;
import com.unlcn.ils.tps.E_level;
import com.unlcn.ils.tps.E_linkman;
import com.unlcn.ils.tps.E_province;
import com.unlcn.ils.tps.common.TpsRuntimeException;
import com.unlcn.ils.tps.ininterface.CrmInformationInterface;
import com.unlcn.ils.tps.ininterface.SqlInterface;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Conditions;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.unlcn.ils.crm.dto.Result;
public class JoinCompanyService implements ArrayContentProvider{
	WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	String username = context.getUser().getName();
	private static Logger logger=Logger.getLogger(Logger.class);
	/**
	 * 
	 * @param data
	 * @return
	 * @Description:检查传进来的分供方信息，用于保存至分供方合作申请表中
	 */
	public E_join_company checkdata(E_join_company data)
	{
		//判断分供方全称是否为空
		String companyName = data.getCompanyName();
		if(companyName == null || companyName.equals(""))
		{
			throw new RuntimeException("分供方全称不能为空");
		}; 
		
	   //得到城市名称
	    int cityid = data.getCityId();
	    if(cityid!=0)
	    {
	    	E_city city = new E_city();
	    	city.setLineid(cityid);
			Dao<E_city> dao=DaoFactory.create(E_city.class);
			city = dao.selectOne(city);
			data.setCity(city.getCityname());
				
			//得到省份
			int provinceid = city.getProvinceid();
			E_province province = new E_province();
			province.setLineid(provinceid);
			Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
			province = daoprovince.selectOne(province);
			data.setProvinceId(provinceid);
			data.setProvince(province.getProvincename());
	    }
	    else{
	    	throw new RuntimeException("注册城市不能为空");
	    };
		
		//判断法人代表
		String legalUser = data.getLegalUser();
		if(legalUser == null||legalUser.equals(""))
		{
			throw new RuntimeException("法人代表不能为空");
		};
		
		//判断总负责人
		String mainContract = data.getMainContract();
		if(mainContract == null||mainContract.equals(""))
		{
			throw new RuntimeException("总负责人不能为空");
		};
		
		//判断税务号
		String taxNo = data.getTaxNo();
		if(taxNo == null||taxNo.equals(""))
		{
			throw new RuntimeException("税务号不能为空");
		};
		
		//判断注册资金
		if(data.getCapital()== null)
		{
			throw new RuntimeException("注册资金不能为空");
		};
		
		//判断联系人
		String contractName = data.getContractName();
		if(contractName == null||contractName.equals(""))
		{
			throw new RuntimeException("联系人不能为空");
		};
		
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setCreateUser(username);
		data.setActive(1);
		data.setCheckFlag(0);
		
		
		return data;
	}

	@Override
	/**
	 * @Description:用于分供方合作搜索，进行搜索的后台处理；
	 */
	public Page<E_join_company> getElements(ArrayContext context) {
		// TODO Auto-generated method stub
		Dao<E_join_company> dao = DaoFactory.create(E_join_company.class);
		Map<String, Object> conditions = context.getCondition();
		//前台传入filters(JSON)
		Conditions condition=conditions.containsKey("filters")?(Conditions)conditions.get("filters") :null;
		Page<E_join_company> result=dao.selectPageByCondition(conditions,
						condition,context.getPageable() ,context.getSortable(), true);
		
		return result;
	}
	
	/**
	 * 
	 * @param data
	 * @param startCity
	 * @param endCity
	 * @param price
	 * @param qty
	 * @return
	 * @Description：创建分供方合作申请信息并进行检查；
	 * 				  将分供方合作基本信息保存至合作申请表中、意向线路保存至意向线路表中；
	 */
	@SuppressWarnings("unused")
	public String createCompany(E_join_company data,
			String[] startCity,String[] endCity,
			String[] price,String[] qty,
			List<E_linkman> linkmanList, List<E_account> accountList,
			String idNumberFileURL,String idNumberFileName,String roadLicenceFileURL,String roadLicenceFileName
			){
	    Dao<E_join_driver> driverDao=DaoFactory.create(E_join_driver.class);
	    Dao<E_join_company> companyDao=DaoFactory.create(E_join_company.class);
	    Dao<E_join_intentline> interlineDao=DaoFactory.create(E_join_intentline.class);
	    Dao<E_join_file>joinfileDao = DaoFactory.create(E_join_file.class);
	    Dao<E_city> cityDao=DaoFactory.create(E_city.class);
	    Dao<E_province> provinceDao=DaoFactory.create(E_province.class);
	    Dao<E_linkman> linkmanDao=DaoFactory.create(E_linkman.class);
	    Dao<E_account> accountDao=DaoFactory.create(E_account.class);
	    Dao<E_audit> auditDao=DaoFactory.create(E_audit.class);
	    Dao<E_level> levelDao=DaoFactory.create(E_level.class);
	    Dao<E_currentlevel> currentDao=DaoFactory.create(E_currentlevel.class);
		//判断分供方全称是否为空
		String companyName = data.getCompanyName();
		if(companyName == null || companyName.equals(""))
		{
			throw new RuntimeException("分供方全称不能为空");
		};
		E_join_company condition=new E_join_company();
		condition.setCompanyName(companyName);
		List<E_join_company> results=companyDao.select(condition);
		if (results!=null) {
			if (results.size()!=0) {
				throw new TpsRuntimeException("所填分供方已存在");
			}
		}
	   //得到城市名称
	    int cityid = data.getCityId();
	    if(cityid!=0)
	    {
	    	E_city city = new E_city();
	    	city.setLineid(cityid);
			Dao<E_city> dao=DaoFactory.create(E_city.class);
			city = dao.selectOne(city);
			data.setCity(city.getCityname());
				
			//得到省份
			int provinceid = city.getProvinceid();
			E_province province = new E_province();
			province.setLineid(provinceid);
			Dao<E_province> daoprovince=DaoFactory.create(E_province.class);
			province = daoprovince.selectOne(province);
			data.setProvinceId(provinceid);
			data.setProvince(province.getProvincename());
	    }
	    else{
	    	throw new RuntimeException("注册城市不能为空");
	    };
		
		//判断法人代表
		String legalUser = data.getLegalUser();
		if(legalUser == null||legalUser.equals(""))
		{
			throw new RuntimeException("法人代表不能为空");
		};
		
		//判断总负责人
		String mainContract = data.getMainContract();
		if(mainContract == null||mainContract.equals(""))
		{
			throw new RuntimeException("总负责人不能为空");
		};
		
		//判断税务号
		String taxNo = data.getTaxNo();
		if(taxNo == null||taxNo.equals(""))
		{
			throw new RuntimeException("税务号不能为空");
		};
		
		//判断注册资金
		if(data.getCapital()== null)
		{
			throw new RuntimeException("注册资金不能为空");
		};
		
		//判断联系人
		String contractName = data.getContractName();
		if(contractName == null||contractName.equals(""))
		{
			throw new RuntimeException("联系人不能为空");
		};
		
		//判断可用车辆数
		if (data.getCarNumber() == null || data.getCarNumber().equals("")) {
			
			throw new RuntimeException("可用车辆数不能为空");
		}
		//判断营业执照号
		if (data.getIdNumber() == null || data.getIdNumber().equals("")) {
			throw new RuntimeException("营业执照号不能为空");
		}
		//判断道路运输许可证
		if (data.getRoadLicence() == null || data.getRoadLicence().equals("")) {
			throw new RuntimeException("道路运输许可证不能为空");
		}
		//创建时间、创建人、取消标志、默认的初审状态、Lineid设置；
		java.sql.Timestamp currdate = new java.sql.Timestamp(new Date().getTime());
		data.setCreateTime(currdate);
		data.setCreateUser(username);
		data.setActive(1);
		data.setCheckFlag(0);
		SqlInterface sqlInterface=new SqlInterface();
		String companyLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_COMPANY");
		data.setLineid(companyLineid);
		companyDao.insert(data);
		//获取刚刚插入的司机表主键
		String getMaxIdPath="com.unlcn.ils.tps.E_join_companyMapper.getMaxId";
		List<E_join_company> companies=companyDao.getSession().selectList(getMaxIdPath);
		//插入意向线路表
		for(int i=0;i<startCity.length;i++){
			E_join_intentline e_join_intentline=new E_join_intentline();
			e_join_intentline.setStartCity(startCity[i]);
			e_join_intentline.setDestProvince(endCity[i]);
			if(!price[i].equals("")){
				BigDecimal bigDecimal=new BigDecimal(price[i]);
				e_join_intentline.setPrice(bigDecimal);				
			}
			if (!qty[i].equals("")) {
				e_join_intentline.setQty(Integer.parseInt(qty[i]));				
			}
			java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
			e_join_intentline.setCreateTime(timestampnow);
			e_join_intentline.setCreateUser(username);
			e_join_intentline.setActive(0);
			e_join_intentline.setJoinId(companies.get(0).getLineid().toString());
			e_join_intentline.setJoinFlag(1);
			String intentLineid=sqlInterface.getNextVal("SEW_TPS_TPS_JOIN_INTERLINE");
			e_join_intentline.setLineid(intentLineid);
			interlineDao.insert(e_join_intentline);
		}
		
		//插入账户信息
				if((accountList != null) && (accountList.size()>0)){
					//E_account e_account = new E_account();
					for(E_account e_account : accountList ){
						String accountLineid=sqlInterface.getNextVal("SEQ_TPS_ACCOUNT");
						//log.info("我要返回账户信息的序号是"+accountLineid);
						e_account.setId(accountLineid);
						e_account.setCustomerId(companyLineid);
						//e_account.setOwnerType("企业");
						e_account.setOperUser(username);
						e_account.setStatus("1");
						e_account.setFlag(1);
						//log.info("我要返回"+e_account.getOperUser());
						java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
						e_account.setOperTime(timestampnow);
						/*e_account.setStartTime(timestampnow);
						e_account.setEndTime(timestampnow);*/
					}
					accountDao.insertBatch(accountList);
				}
				
				//插入联系人信息
				if((linkmanList != null) && (linkmanList.size()>0)){
					//E_account e_account = new E_account();
					for(E_linkman e_linkman : linkmanList ){
						String linkmanLineid=sqlInterface.getNextVal("SEQ_TPS_LINKMAN");
						//log.info("我要返回账户信息的序号是"+linkmanLineid);
						e_linkman.setId(linkmanLineid);
						e_linkman.setCustomerId(companyLineid);
						e_linkman.setServiceType("");
						e_linkman.setOperUser(username);
						e_linkman.setFlag(1);
						//log.info("我要返回"+e_linkman.getOperUser());
						java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
						e_linkman.setOperTime(timestampnow);
					}
					linkmanDao.insertBatch(linkmanList);
				}
		//插入附件列表（营业执照）
				E_join_file e_join_file=new E_join_file();
				e_join_file.setFileName(idNumberFileName);
				e_join_file.setFilePath(idNumberFileURL);
				String sourceStr=idNumberFileName;
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
		        e_join_file.setFileExt(substring);
		        e_join_file.setSourceId(companyLineid);
		        e_join_file.setFlag(1);
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_join_file.setCreateTime(timestampnow);
				e_join_file.setCreateUser(username);
				String filelineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
				e_join_file.setLineid(filelineid);
				joinfileDao.insert(e_join_file);	
		//插入附件列表（运输管理许可证）
				E_join_file e_join_file1=new E_join_file();
				e_join_file1.setFileName(roadLicenceFileName);
				e_join_file1.setFilePath(roadLicenceFileURL);
				String sourceStr1=roadLicenceFileName;
				Integer pos1=sourceStr1.indexOf('.');
		        String substring1=sourceStr1.substring(pos1+1, sourceStr1.length() );		        
		        e_join_file1.setFileExt(substring1);
		        e_join_file1.setSourceId(companyLineid);
		        e_join_file1.setFlag(2);
				//java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_join_file1.setCreateTime(timestampnow);
				e_join_file1.setCreateUser(username);
				String filelineid1=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
				
				e_join_file1.setLineid(filelineid1);
				joinfileDao.insert(e_join_file1);	
		//插入初审表
		E_audit e_audit=new E_audit();
		e_audit.setJoinId(companies.get(0).getLineid());
		e_audit.setJoinFlag(1);
		e_audit.setAuditFlag(0);
		//java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
		e_audit.setCreateTime(timestampnow);
		e_audit.setCreateUser(username);
		e_audit.setActive(0);
		String auditLineid=sqlInterface.getNextVal("SEQ_TPS_TPS_AUDIT");
		e_audit.setLineid(auditLineid);
		auditDao.insert(e_audit);
		return companies.get(0).getLineid();
	}
	public List<String> selectByCityId(Integer cityId){
		Dao<E_join_company> companyDao=DaoFactory.create(E_join_company.class);
		String getMaxIdPath="com.unlcn.ils.tps.E_join_companyMapper.selectByCityId";
		List<E_join_company> companies=companyDao.getSession().selectList(getMaxIdPath,cityId);
		List<String> list=new ArrayList<String>();
		for(int i=0;i<companies.size();i++){
			list.add(companies.get(i).getLineid());
		}
		return list;
	}
	
	/**
	 * @Description：初审时分供方信息编辑，更新合作申请表信息；
	 */
	public boolean updateComapny(E_join_company data){
		Dao<E_join_company> companyDao=DaoFactory.create(E_join_company.class);
		Dao<E_city> cityDao=DaoFactory.create(E_city.class);
		E_join_company e_join_company=companyDao.selectByID(data.getLineid());
		e_join_company.setCompanyName(data.getCompanyName());
		e_join_company.setCityId(data.getCityId());
		e_join_company.setProvinceId(cityDao.selectByID(data.getCityId()).getProvinceid());
		e_join_company.setProvince(cityDao.selectByID(data.getCityId()).getProvincename());
		e_join_company.setCity(cityDao.selectByID(data.getCityId()).getCityname());
		e_join_company.setLegalUser(data.getLegalUser());
		e_join_company.setMainContract(data.getMainContract());
		e_join_company.setTaxNo(data.getTaxNo());
		e_join_company.setCapital(data.getCapital());
		e_join_company.setAssets(data.getAssets());
		e_join_company.setContractName(data.getContractName());
		if(data.getContract()!=null)
		e_join_company.setContract(data.getContract());
		e_join_company.setMobileno(data.getMobileno());
		//companyDao.update(e_join_company);
		//只有认证通过的数据才推送到crm更新
		if (e_join_company.getCheckFlag()==3) {
			//将更新的信息传入crm接口
			CrmInformationInterface crmInformationInterface=new CrmInformationInterface();
			Result result=new Result(1, null, "");
			 try {
				result=crmInformationInterface.updateShipper2Crm_c(e_join_company);
				if(result.getRetCode()==-1){
					logger.info("调用失败,错误码为: "+result.getErrCode());
					logger.info("调用失败,错误信息为: "+result.getErrDesc());
					throw new RuntimeException("crm方更新分供方失败,错误原因为:"+result.getErrDesc());
					}
					else{
						companyDao.update(e_join_company);
					}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if(result.getRetCode()==-1){
					throw new RuntimeException("crm方更新分供方失败,错误原因为:"+result.getErrDesc());
				}
				else{
					throw new RuntimeException("更新失败,请重试");
				}
			}
			logger.info("更新的状态为"+result.getRetCode());
			if(result.getRetCode()==-1){
				logger.info("引发的错误是："+result.getErrDesc());
			}			
		}
		return false;
	}
	
	/**
	 * @Description：附件上传
	 * @param mapList
	 * @param id
	 * @return
	 */
	/*@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public boolean companyAttached(Map mapList,String id){
		//一个附件对应一个一个数据
		Dao<E_join_file> authenticationDao=new DaoFactory().create(E_join_file.class);
		Dao<E_join_company> companyDao=new DaoFactory().create(E_join_company.class);
		List<Map> fileList=(List<Map>) mapList.get("fileList");
		E_join_company e_join_company=companyDao.selectByID(id);
		//单文件时
		if(fileList==null){
			E_join_file e_attached=new E_join_file();
			e_attached.setFileName(mapList.get("name").toString());
			e_attached.setFilePath(mapList.get("url").toString());
			String sourceStr=(String) mapList.get("name");
			Integer pos=sourceStr.indexOf('.');
	        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
			e_attached.setFileExt(substring);
			e_attached.setSourceId(e_join_company.getLineid());
			e_attached.setFlag(1);
			java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
			e_attached.setCreateTime(timestampnow);
			e_attached.setCreateUser(username);
			SqlInterface sqlInterface=new SqlInterface();
			String lineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
			e_attached.setLineid(lineid);
			authenticationDao.insert(e_attached);
			return true;
		}
		//多文件时
		else{
			for(int i=0;i<fileList.size();i++){
				E_join_file e_attached=new E_join_file();
				Map map=fileList.get(i);
				e_attached.setFileName(map.get("name").toString());
				e_attached.setFilePath(map.get("url").toString());
				String sourceStr=(String) map.get("name");
				Integer pos=sourceStr.indexOf('.');
		        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
				e_attached.setFileExt(substring);
				e_attached.setFlag(1);
				e_attached.setSourceId(e_join_company.getLineid());
				java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
				e_attached.setCreateTime(timestampnow);
				e_attached.setCreateUser(username);
				SqlInterface sqlInterface=new SqlInterface();
				String lineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
				e_attached.setLineid(lineid);
				authenticationDao.insert(e_attached);
			}
			return true;
		}
		}	*/
	/**
	 * 
	 * @param data
	 * @param id
	 * @return
	 * @Description:营业执照上传
	 */
	@SuppressWarnings("rawtypes")
	public boolean addFileBuss(Map data,String id) {
		Dao<E_join_file>joinfileDao = DaoFactory.create(E_join_file.class);
		E_join_file e_join_file = new E_join_file();
		logger.info("我要返回"+data.get("name").toString());
		e_join_file.setFileName(data.get("name").toString());
		e_join_file.setFilePath(data.get("url").toString());
		String sourceStr=(String)data.get("name");
		Integer pos=sourceStr.indexOf('.');
        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
        e_join_file.setFileExt(substring);
        e_join_file.setSourceId(id);
        e_join_file.setFlag(1);
		java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
		e_join_file.setCreateTime(timestampnow);
		e_join_file.setCreateUser(username);
		SqlInterface sqlInterface=new SqlInterface();
		String filelineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
		e_join_file.setLineid(filelineid);
		joinfileDao.insert(e_join_file);	
		return true;	
	}
	/**
	 * 
	 * @param data
	 * @param id
	 * @return
	 * @Description:道路运输许可证上传
	 */
	@SuppressWarnings("rawtypes")
	public boolean addFileTrans(Map data,String id) {
		Dao<E_join_file>joinfileDao = DaoFactory.create(E_join_file.class);
		E_join_file e_join_file = new E_join_file();
		e_join_file.setFileName(data.get("name").toString());
		e_join_file.setFilePath(data.get("url").toString());
		String sourceStr=(String) data.get("name");
		Integer pos=sourceStr.indexOf('.');
        String substring=sourceStr.substring(pos+1, sourceStr.length() );		        
        e_join_file.setFileExt(substring);
        e_join_file.setSourceId(id);
        e_join_file.setFlag(2);
		java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
		e_join_file.setCreateTime(timestampnow);
		e_join_file.setCreateUser(username);
		SqlInterface sqlInterface=new SqlInterface();
		String filelineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
		e_join_file.setLineid(filelineid);
		joinfileDao.insert(e_join_file);	
		return true;
		
	}
}

