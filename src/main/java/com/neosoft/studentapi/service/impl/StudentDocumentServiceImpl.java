package com.neosoft.studentapi.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.studentapi.exception.FileStorageException;
import com.neosoft.studentapi.exception.MyFileNotFoundException;
import com.neosoft.studentapi.model.StudentDocument;
import com.neosoft.studentapi.repo.StudentDocumentRepository;
import com.neosoft.studentapi.rest.ProjectRestController;
import com.neosoft.studentapi.service.IStudentDocumentService;

@Service
public class StudentDocumentServiceImpl implements IStudentDocumentService {
	
	private static final Logger log = LoggerFactory.getLogger(StudentDocumentServiceImpl.class);
	
	@Autowired
	private StudentDocumentRepository studentDocumentRepo;
	
	@Override
	public StudentDocument saveDocument(MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            StudentDocument stuDocument = new StudentDocument(fileName, file.getContentType(), file.getBytes());

            return studentDocumentRepo.save(stuDocument);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!"+ ex);
        }
		
		
	}

	@Override
	public StudentDocument getDocument(String filename) {
		
		return studentDocumentRepo.findByFileName(filename);
	}

}
