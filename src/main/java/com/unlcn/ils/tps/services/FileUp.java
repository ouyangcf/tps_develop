package com.unlcn.ils.tps.services;
 
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.unlcn.ils.tps.E_join_file;
import com.unlcn.ils.tps.ininterface.SqlInterface;

public class FileUp {
	
	public boolean IDFileUp(String urlString,String nameString) {
	   Dao<E_join_file>joinfliedao = DaoFactory.create(E_join_file.class);
	   E_join_file e_join_file=new E_join_file();
	   e_join_file.setFilePath(urlString);
	   e_join_file.setFileName(nameString);
	   java.sql.Timestamp timestampnow=new java.sql.Timestamp(System.currentTimeMillis());
	   e_join_file.setCreateTime(timestampnow);
	   SqlInterface sqlInterface=new SqlInterface();
	   String lineid=sqlInterface.getNextVal("SEQ_TPS_TPS_JOIN_FILE");
	   e_join_file.setLineid(lineid);
	   //e_join_file.setSourceId(1);
	   joinfliedao.insert(e_join_file);
	   return true;
		
		
	}
}
