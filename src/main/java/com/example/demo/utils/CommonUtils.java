package com.example.demo.utils;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CommonUtils {

	protected static final Logger LOG = LoggerFactory.getLogger(CommonUtils.class);
	
	public static boolean checkParamAvailable(String param) {
		
		if (StringUtils.isBlank(param)) {
			return false;
		}
		return true;
	}
	
	
	public static void resultMsg(Object result, HttpServletResponse response, int responseCode) {
		//response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		try {
			ServletOutputStream e = response.getOutputStream();
			e.write(JsonUtils.getJsonString(result).getBytes("utf-8"));
			e.flush();
			response.flushBuffer();
			e.close();
		} catch (Exception e) {
			LOG.info("context", e);
		}
		return;
	}
	
	
	public static void dealResult(int errorCode, String errorMsg,Object data, HttpServletResponse response) {
		ResultMessage errorResult = new ResultMessage();
		errorResult.setError_code("" + errorCode);
		errorResult.setError_Msg(errorMsg);
		errorResult.setData(data);
		resultMsg(errorResult, response, errorCode);
	}
	public static HttpEntity getHttpEntity(String jsonRequest) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> requestEntity = null;
		if(jsonRequest==null || "".equals(jsonRequest)){
			requestEntity = new HttpEntity<>(httpHeaders);
		}else{

			requestEntity = new HttpEntity<>(jsonRequest,httpHeaders);
		}
		return requestEntity;
	}


	public static String getPr_uid() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String pr_uid = str.replace("-", "").substring(0, str.replace("-", "").length() / 2);
		return pr_uid;
	}

	public static String getPr_id() {
		UUID uuid = UUID.randomUUID();
		String pr_id = uuid.toString();
		return pr_id;
	}




}
