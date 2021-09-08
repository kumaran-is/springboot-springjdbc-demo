package com.college.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/list")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public void addNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
		
		studentService.deleteStudent(id);
	}
	
	/*@PutMapping("/{id}")
	public void modifyStudent(
			@PathVariable Long id,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		
		studentService.updateStudent(id, name, email);
	} */

}
