package com.neosoft.studentapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.studentapi.model.Student;
import com.neosoft.studentapi.repo.StudentRepository;
import com.neosoft.studentapi.service.IStudentService;



@Service
public class StudentServiceImpl implements IStudentService , UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository  studentRepo;

	/*
	@Autowired
	private BCryptPasswordEncoder pwdEncoder; */

	@Override
	public Integer saveStudent(Student stu) {

		log.info("Entered In saveStudent Method");
		//stu.setPwd(pwdEncoder.encode(stu.getPwd()));
		return studentRepo.save(stu).getId();
	}

	@Override
	public Student findStudentById(Integer stuId) {

		return studentRepo.findById(stuId).get();
	}

	@Override
	public List<Student> getAllStudent() {

		return studentRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Student findByEmail(String email) {
		Optional<Student> user=studentRepo.findByEmail(email);
		if(user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student user=findByEmail(email);
		if(user==null)
			throw new UsernameNotFoundException(
					new StringBuffer()
					.append("User name ")
					.append(email)
					.append(" not found!")
					.toString()
					);
		List<GrantedAuthority> authorities=
				user.getRoles()
				.stream()
				.map(
						role->new SimpleGrantedAuthority(role)
						)
				.collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(
				email,
				user.getPwd(),
				authorities);
	}

}
