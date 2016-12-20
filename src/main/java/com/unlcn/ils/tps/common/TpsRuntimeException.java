package com.unlcn.ils.tps.common; 

import com.chinacreator.c2.exception.C2RuntimeException;
 
public class TpsRuntimeException extends C2RuntimeException { 
 
	/**   
	 *   
	 */ 
	private static final long serialVersionUID = 1L; 

	public TpsRuntimeException(String message) {
		super(message);
	}
	 public TpsRuntimeException(String message, Throwable cause) {
	        super(message, cause);
	    }
	 public String getErrorNum()
	 {
		 
		 return super.getErrorNum();
	 }
	 
}
