package com.neosoft.studentapi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.studentapi.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Optional<Student> findByEmail(String email);

}
