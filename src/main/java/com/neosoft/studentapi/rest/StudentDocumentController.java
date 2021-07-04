package com.neosoft.studentapi.rest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neosoft.studentapi.model.StudentDocument;
import com.neosoft.studentapi.model.UploadFileResponse;
import com.neosoft.studentapi.service.IStudentDocumentService;

@RestController
@RequestMapping("/stu/doc")
public class StudentDocumentController {

	private static final Logger log = LoggerFactory.getLogger(StudentDocumentController.class);

	@Autowired
	private IStudentDocumentService studentDocumentService;

	@PostMapping("/uploadFile")
	public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam MultipartFile file) {

		StudentDocument document = studentDocumentService.saveDocument(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/stu/doc/downloadFile/")
				.path(document.getFileName())
				.toUriString();

		return ResponseEntity.status(HttpStatus.OK).body(new UploadFileResponse(document.getFileName(), fileDownloadUri,
				file.getContentType(), file.getSize()));

	}

	@PostMapping("/uploadfiles")
	public ResponseEntity<List<UploadFileResponse>> uploadMultipleFiles (@RequestParam("files") MultipartFile[] files) {

		List<UploadFileResponse> responses = Arrays.asList(files)
				.stream()
				.map(
						file -> {
							StudentDocument document = studentDocumentService.saveDocument(file);

							String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
									.path("/stu/doc/downloadFile/")
									.path(document.getFileName())
									.toUriString();
							return new UploadFileResponse(document.getFileName(), fileDownloadUri,
									file.getContentType(), file.getSize());
						}
						)
				.collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(responses);
	}

	@GetMapping("/download/{filename:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

		Resource resource = (Resource) studentDocumentService.getDocument(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}



}
