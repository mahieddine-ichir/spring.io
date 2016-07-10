package com.michir.projects.springresttemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class UploadBackendService {

	@Log
	private Logger logger;
	
	public byte[] doSomthing(InputStream is, String name) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Streams.copy(is, bos, true);
			return bos.toByteArray();
		} catch (IOException e) {
			logger.error("Error processing data "+name, e);
			throw new RuntimeException(e);
		}
	}
	
	public byte[] content(InputStream is, String name) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			Streams.copy(is, bos, true);
			return bos.toByteArray();
		} catch (IOException e) {
			logger.error("Error processing data "+name, e);
			throw new RuntimeException(e);
		}
	}
	
}
