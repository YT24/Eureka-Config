package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.InputStream;


public class JsonUtils {
	

	 protected static final ObjectMapper MAPPER = new ObjectMapper();

	 
	 public static byte[] getJsonBytes(Object obj) {
	    	byte[] message=null;
	        try {
		      	message=MAPPER.writeValueAsBytes(obj);
		    } catch (Exception e1) {
		      	e1.printStackTrace();
		    }     
	        return message;
	}
	 
	 public static String getJsonString(Object obj) {
	    	String message=null;
	        try {
		      	message=MAPPER.writeValueAsString(obj);
		    } catch (Exception e1) {
		      	e1.printStackTrace();
		    }     
	        return message;
	}	 
	
	 public static String resultError(Integer code,String error,Object data,HttpServletResponse response){
		 response.setStatus(417);
		 ResultMessage result=new ResultMessage();
		 result.setError_code(code+"");
		 result.setError_Msg(error);		 
		 result.setData(data);
		 return getJsonString(result);
	}
	

	
   public static byte[] getObj2Json(Object obj) {
	    byte[] message=null;
	    try {
		   	message=MAPPER.writeValueAsBytes(obj);
		} catch (Exception e1) {
		   	e1.printStackTrace();
		}     
	    return message;
   }

   public static  <S> S getJson2Obj(Object obj,final Class<S> serviceClass) {	    	
  		  
	   		try {     	
	        	if(obj instanceof String) {
	        		return MAPPER.readValue((String)obj, serviceClass);
	        	}else
	        	if(obj instanceof byte[]) {
	        		return MAPPER.readValue((byte[])obj, serviceClass);
	        	}else
	        	if(obj instanceof InputStream) {
	        		return MAPPER.readValue((InputStream)obj, serviceClass);
	        	}else
	        	if(obj instanceof DataInput) {
	        		return MAPPER.readValue((DataInput)obj, serviceClass);
	        	}
		    } catch (Exception e1) {
		      	e1.printStackTrace();
		    }     
	        return null;
	  }	
}
