package com.neosoft.studentapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.studentapi.model.StudentDocument;

@Repository
public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Integer> {

	StudentDocument findByFileName(String filename);

}
