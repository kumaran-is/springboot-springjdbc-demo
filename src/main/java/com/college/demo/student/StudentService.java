package com.college.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
	
	private final StudentDAO studentDAO;
	
	@Autowired
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public List<Student> getStudents() {
		
		return (List<Student>) studentDAO.findAll();
	}
	
	public void addNewStudent(Student student) {
		
		/*Optional<Student>  studentOptional = studentDAO.findStudentByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		} */
		
		studentDAO.create(student);
	}
	
	public void deleteStudent(Long id) { 
		
		/*boolean exists = studentDAO.existsById(id);
		if(!exists) {
			throw new IllegalStateException("Student with id " + id + " does not exists");
		} */
		
		studentDAO.delete(id);
	}
	

	/* public void updateStudent(Long id, String name, String email) { 
		
		Student student = studentDAO.findById(id)
				.orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exists"));
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(),  name)) {
			studentDAO.updateByName(id, name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
			studentDAO.updateByEmail(id, email);;
		}
	} */

}
