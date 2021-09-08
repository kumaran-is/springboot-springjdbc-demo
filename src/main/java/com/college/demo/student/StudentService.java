package com.college.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import com.college.demo.APIRequestException;
import com.college.demo.EmailValidator;


@Service
public class StudentService {
	
	private final DAO<Student> dao;
	private final EmailValidator emailValidator;
	
	@Autowired
	public StudentService(
		DAO<Student> dao,
		EmailValidator emailValidator
	) {
		this.dao = dao;
		this.emailValidator = emailValidator;
	}
	
	public List<Student> getStudents() {
		
		return (List<Student>)dao.findAll();
	}
	
	public void addNewStudent(Student student) {
		
		/*Optional<Student>  studentOptional = dao.findByEmail(student.getEmail());
		
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email " + student.getEmail() + " taken");
		} */
		
		if (!emailValidator.test(student.getEmail())) {
            throw new APIRequestException(student.getEmail() + " is not valid");
        }
		
		if(dao.isEmailTaken(student.getEmail())) {
			throw new APIRequestException("Email " + student.getEmail() + " taken");
		} 
		
		dao.create(student);
	}
	
	public void deleteStudent(Long id) { 
		
		Optional<Student>  studentOptional  = dao.findById(id);
		if(!studentOptional.isPresent()) {
			throw new APIRequestException("Student with id " + id + " does not exists");
		} 
		
		dao.delete(id);
	}
	
	@Transactional
	 public void updateStudent(Long id, String name, String email) { 
		
		Student student = dao.findById(id)
				.orElseThrow(() -> new APIRequestException("Student with id " + id + " does not exists"));

		
		/*if(name != null && name.length() > 0 && !Objects.equals(student.getName(),  name)) {
			student.setName(name);
		} */
		
		Optional.ofNullable(name)
                .filter(studentName -> !ObjectUtils.isEmpty(studentName))
                .map(StringUtils::capitalize)
                .ifPresent(studentName -> student.setName(studentName));
		
		/*if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(),  email)) {
			student.setEmail(email);
		}
		*/
		
		Optional.ofNullable(email)
        .filter(studentEmail -> !ObjectUtils.isEmpty(studentEmail))
        .map(StringUtils::capitalize)
        .ifPresent(studentEmail -> student.setEmail(studentEmail));
		
		dao.update(student, id);
	} 

}
