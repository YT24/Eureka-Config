/**
 * RestTempalteUtil.java
 * Created at 2018-06-06
 * Created by ZuoJian
 * Copyright(C)2018SAIC|SHANGHAI VOLKSWAGEN, All rights reserved.
 */
package com.example.demo.utils;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * ClassName:RestTempalteUtil.java
 * </p>
 * <p>
 * Description:RestTempalteUtil
 * </p>
 * <p>
 * author:ZuoJan
 * </p>
 * <p>
 * Date:2018-6-12
 * </p>
 */
@Component
public class RestTempalteUtil {

	/**
	 * <p>
	 * Field restTemplate:restTemplate
	 * </p>
	 */
	private static RestTemplate restTemplate;

	private static AsyncRestTemplate asyncRestTemplate;


	/**
	 * <p>
	 * Description:RestTempalteUtil
	 * </p>
	 */
	private RestTempalteUtil() {

	}

	/**
	 * <p>
	 * Description:getClient
	 * </p>
	 * 
	 * @return RestTemplate
	 */
	@Bean
	public RestTemplate getClient() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(50000);
		requestFactory.setConnectTimeout(50000);

		restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());
		restTemplate.setUriTemplateHandler(new RestTemplateUriHandler());
		restTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}

	@Bean
	public AsyncRestTemplate getAsyncClient() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(50000);
		factory.setReadTimeout(50000);
		// 设置异步任务（线程不会重用，每次调用时都会重新启动一个新的线程）
		factory.setTaskExecutor(new SimpleAsyncTaskExecutor());

		asyncRestTemplate = new AsyncRestTemplate();
		asyncRestTemplate.setAsyncRequestFactory(factory);
		asyncRestTemplate.setErrorHandler(new RestTemplateErrorHandler());
		asyncRestTemplate.setUriTemplateHandler(new RestTemplateUriHandler());
		asyncRestTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return asyncRestTemplate;
	}

}
