package com.neosoft.studentapi.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "student_tab")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "stu_id_col")
	private Integer id;
	
	@Column(name = "stu_firstName_col")
	private String firstName;
	
	@Column(name = "stu_lastName_col")
	private String lastName;
	
	@Column(name = "stu_phoneNumber_col")
	private String phoneNumber;
	
	@Column(name = "stu_email_col")
	private String email;
	
	@Column(name="pwd")
	private String pwd;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(
			name="rolestab",
			joinColumns = 
			@JoinColumn(name="stu_id_col"))
	@Column(name="role")
	private List<String> roles;
	

}
