package com.neosoft.studentapi.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.studentapi.model.Student;
import com.neosoft.studentapi.service.IStudentService;


@RestController
@RequestMapping("/api/student")
public class StudentRestController {
	
    private static final Logger log = LoggerFactory.getLogger(StudentRestController.class);
	
	@Autowired
	private IStudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<?> saveStudent(@RequestBody Student stu){
		
		ResponseEntity<?> resp = null;
		
		try {
			log.info("Inside saveStudent Method In saveStudent For Save An Student Entity");
			Integer studentId = studentService.saveStudent(stu);
			String message = "Student "+studentId+" Is Saved Succefully";
			log.info(message);
			resp = new ResponseEntity<String>(message,HttpStatus.CREATED);
			log.info("ResponseEntity Is Returned As a String Message");
			
		} catch (Exception e) {
			log.error("Due To Internal Server Error. Student Entity Is Not Saved Succefully");
			resp = new ResponseEntity<String>("Unable To Save Student Entity",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return resp;
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllStudents() 
	{
		log.info("ENTERED INTO GET ALL STUDENT METHOD");
		ResponseEntity<?> resp = null;
		try {
			
			List<Student> students = studentService.getAllStudent();
			log.info("SERVICE METHOD CALLED FOR ALL DATA FETCH");
			resp = new ResponseEntity<List<Student>>(students,HttpStatus.OK);
			log.info("SUCCESS RESPONSE CREATED");
		} catch (Exception e) {
			log.error("PROBLEM IN FETCHING DATA {}",e.getMessage());
			resp = new ResponseEntity<String>("Unable to Fetch Students",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		log.info("ABOUT TO RETURN FROM GET ALL METHOD");
		return resp;
	}

}
