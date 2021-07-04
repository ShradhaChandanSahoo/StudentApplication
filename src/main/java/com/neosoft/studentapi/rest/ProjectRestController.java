package com.neosoft.studentapi.rest;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.studentapi.model.Project;
import com.neosoft.studentapi.model.Student;
import com.neosoft.studentapi.service.IProjectService;
import com.neosoft.studentapi.service.IStudentService;



@RestController
@RequestMapping("/api/project")
public class ProjectRestController {

	private static final Logger log = LoggerFactory.getLogger(ProjectRestController.class);

	@Autowired
	private IProjectService projectService;
	
	@Autowired
	private IStudentService studentService;
	

	@PostMapping("/save/{stuId}/projects")
	public ResponseEntity<?> saveProject(@PathVariable Integer stuId,@RequestBody Project proj) {

		ResponseEntity<?> resp = null;

		try {
			log.info("Inside saveProject Method In saveProject For Save An Project Entity");
			
			Student student = studentService.findStudentById(stuId);
			proj.setStud(student);
			Project project = projectService.saveProject(proj);
			
			/*
			return studentService.findById(stuId).map(stu -> {
				proj.setStud(stu);
	            return projectService.saveProject(proj);
	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));*/
			
			String message = "Project "+project.getId()+" Is Saved Succefully";
			log.info(message);
			resp = new ResponseEntity<String>(message,HttpStatus.CREATED);
			log.info("ResponseEntity Is Returned As a String Message");

		} catch (Exception e) {
			log.error("Due To Internal Server Error. Project Entity Is Not Saved Succefully");
			resp = new ResponseEntity<String>("Unable To Save Project Entity",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return resp;

	}
	
	
	@GetMapping("/all/{stuId}/projects")
	public ResponseEntity<?> getAllProjects(@PathVariable Integer stuId) 
	{
		log.info("ENTERED INTO GET ALL Projects METHOD");
		ResponseEntity<?> resp = null;
		try {
			
			List<Project> projects = projectService.findAllProjectsByStudentId(stuId);
			log.info("SERVICE METHOD CALLED FOR ALL DATA FETCH");
			resp = new ResponseEntity<List<Project>>(projects,HttpStatus.OK);
			log.info("SUCCESS RESPONSE CREATED");
		} catch (Exception e) {
			log.error("PROBLEM IN FETCHING DATA {}",e.getMessage());
			resp = new ResponseEntity<String>("Unable to Fetch Projects",HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		log.info("ABOUT TO RETURN FROM GET ALL METHOD");
		return resp;
	}

}
