package com.nowpark;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nowpark.service.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SuperBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperBoardApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(UserService userService) {
		return args -> {
			log.info("Create Data...");
//			userService.insertData();
			log.info("findAll()...");
			userService.selectAll().forEach(entry -> log.info(entry.toString()));
		};
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(messageConverters());

		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory requestFactory(@Autowired RestTemplateProperties restTemplateProperties) {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		requestFactory.setReadTimeout(restTemplateProperties.getReadTimeout());
		requestFactory.setConnectTimeout(restTemplateProperties.getConnectTimeout());

		return requestFactory;
	}

	private List<HttpMessageConverter<?>> messageConverters() {
		return Arrays.asList(
				new StringHttpMessageConverter(Charset.forName("UTF-8")),
				new MappingJackson2HttpMessageConverter()
		);
	}

	@Getter
	@Setter
	@Component
	@ConfigurationProperties(prefix = "resttemplate")
	public static class RestTemplateProperties {
		private Integer readTimeout;
		private Integer connectTimeout;
	}
}
