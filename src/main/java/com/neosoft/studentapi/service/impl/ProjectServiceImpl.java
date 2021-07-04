package com.neosoft.studentapi.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.studentapi.model.Project;
import com.neosoft.studentapi.repo.ProjectRepository;
import com.neosoft.studentapi.service.IProjectService;


@Service
public class ProjectServiceImpl implements IProjectService {
	
	private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectRepository projectRepo;

	@Override
	public Project saveProject(Project proj) {
		
		return projectRepo.save(proj);
	}

	@Override
	public List<Project> findAllProjectsByStudentId(Integer stuId) {
		
		return projectRepo.findByStudentId(stuId);
	}

}
