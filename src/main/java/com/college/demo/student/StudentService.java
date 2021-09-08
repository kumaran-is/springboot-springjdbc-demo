package com.college.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
	
	private final DAO<Student> dao;
	
	@Autowired
	public StudentService(DAO<Student> dao) {
		this.dao = dao;
	}
	
	public List<Student> getStudents() {
		
		return (List<Student>)dao.findAll();
	}
	
	public void addNewStudent(Student student) {
		
		/*Optional<Student>  studentOptional = dao.findByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email " + student.getEmail() + " taken");
		} */
		
		if(dao.isEmailTaken(student.getEmail())) {
			throw new IllegalStateException("Email " + student.getEmail() + " taken");
		} 
		
		dao.create(student);
	}
	
	public void deleteStudent(Long id) { 
		
		Optional<Student>  studentOptional  = dao.findById(id);
		if(!studentOptional.isPresent()) {
			throw new IllegalStateException("Student with id " + id + " does not exists");
		} 
		
		dao.delete(id);
	}
	

	 public void updateStudent(Long id, String name, String email) { 
		
		Student student = dao.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exists"));
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(),  name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
			student.setEmail(email);
		}
		
		dao.update(student, id);
	} 

}
