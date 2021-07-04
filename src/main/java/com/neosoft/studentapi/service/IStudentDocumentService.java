package com.neosoft.studentapi.service;

import org.springframework.web.multipart.MultipartFile;

import com.neosoft.studentapi.model.StudentDocument;

public interface IStudentDocumentService {
	
	public StudentDocument saveDocument(MultipartFile file);
	public StudentDocument getDocument(String filename);

}
