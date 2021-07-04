package com.neosoft.studentapi.service;

import java.util.List;

import com.neosoft.studentapi.model.Student;

public interface IStudentService {

	Integer saveStudent(Student stu);

	Student findStudentById(Integer stuId);

	List<Student> getAllStudent();

}
