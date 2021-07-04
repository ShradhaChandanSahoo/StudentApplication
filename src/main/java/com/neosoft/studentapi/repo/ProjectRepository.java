package com.neosoft.studentapi.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neosoft.studentapi.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	@Query("SELECT proj FROM Project proj INNER JOIN proj.stud AS stud WHERE stud.id=:stuId")
	List<Project> findByStudentId(Integer stuId);

}
