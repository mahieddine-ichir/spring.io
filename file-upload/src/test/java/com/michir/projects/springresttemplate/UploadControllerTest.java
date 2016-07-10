package com.michir.projects.springresttemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=UploadControllerTest.class)
public class UploadControllerTest {

	private static final Log LOG = LogFactory.getLog(UploadController.class);

	@Test
	public void client() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
		LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("file", new FileSystemResource(new File(this.getClass().getResource("/file.txt").getFile())));

		HttpEntity<LinkedMultiValueMap<String,Object>> httpEntity = new HttpEntity<>(parts, headers);
		
		ResponseEntity<byte[]> exchange = restTemplate.exchange("http://localhost:8080/upload/txt/file", HttpMethod.POST, httpEntity, byte[].class);
		String filename = exchange.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION).split("\\s")[1].split("=")[1].replaceAll("\"", "");
		
		LOG.debug(">>>>> Content disposition: "+filename);
		FileUtils.copyInputStreamToFile(new ByteArrayInputStream(exchange.getBody()), new File("target", filename));
	}
	
	@Test
	public void client_for_content() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
		LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("file", new FileSystemResource(new File(this.getClass().getResource("/file.txt").getFile())));

		HttpEntity<LinkedMultiValueMap<String,Object>> httpEntity = new HttpEntity<>(parts, headers);
		
		ResponseEntity<byte[]> exchange = restTemplate.exchange("http://localhost:8080/upload/txt/content", HttpMethod.POST, httpEntity, byte[].class);
		LOG.debug(">>>>> Content: "+new String(exchange.getBody()));
	}
}
