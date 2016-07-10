package com.michir.projects.springresttemplate;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/upload")
public class UploadController {

	@Log
	private Logger log;
	
	@Autowired
	private UploadBackendService service;

	@RequestMapping(value="/txt/file", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> file(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// logging
		request.getMultiFileMap().forEach((a, b) -> {
			b.forEach(f -> {
				log.info("filename "+f.getOriginalFilename());
				log.info("name "+f.getName());
			});
		});
		
		// get parameter named "file" 
		MultipartFile part = request.getMultiFileMap().getFirst("file");

		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", part.getOriginalFilename()));
		return new ResponseEntity<byte[]>(service.doSomthing(part.getInputStream(), part.getOriginalFilename()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/txt/content", method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<byte[]> content(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// logging
		request.getMultiFileMap().forEach((a, b) -> {
			b.forEach(f -> {
				log.info("filename "+f.getOriginalFilename());
				log.info("name "+f.getName());
			});
		});
		
		// get parameter named "file" 
		MultipartFile part = request.getMultiFileMap().getFirst("file");

		return new ResponseEntity<byte[]>(service.doSomthing(part.getInputStream(), part.getOriginalFilename()), HttpStatus.OK);
	}
}
