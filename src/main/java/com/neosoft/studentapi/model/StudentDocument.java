package com.neosoft.studentapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


import lombok.Data;


@Data
@Entity
@Table(name="stu_doc_col")

public class StudentDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stu_doc_id_col")
	private Integer id;
	
	@Column(name = "stu_doc_fileName_col")
	private String fileName;
	
	@Column(name = "stu_doc_fileType_col")
	private String fileType;
	
	@Column(name = "stu_doc_fileData_col")
	@Lob
	private byte[] data;

	public StudentDocument(String fileName, String fileType, byte[] data) {
		
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	
	

}
