package com.neosoft.studentapi.service;

import java.util.List;

import com.neosoft.studentapi.model.Project;

public interface IProjectService {

	public Project saveProject(Project proj);

	public List<Project> findAllProjectsByStudentId(Integer stuId);

}
