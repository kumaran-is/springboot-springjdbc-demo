package com.college.demo.student;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO implements DAO<Student> {
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Override
	public List<Student> findAll() {
		 String sql = "SELECT id, name, email, dob FROM student";

	     return jdbcTemplate.query(sql, rowMapper);
	}
	
	/**
     * Maps a row in the database to a Student
     */
	
	RowMapper<Student> rowMapper = (resultSet, rowNum) -> {
   
        return new Student(
			resultSet.getLong("id"),
			resultSet.getString("name"),
			resultSet.getString("email"),
			resultSet.getDate("dob").toLocalDate()
        );
    };
	
    @Override
    public void create(Student student) {
        String sql = "INSERT INTO student(name, email, dob) VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql,student.getName(), student.getEmail(), student.getDob());
        if(insert == 1) {
           log.info("New Student Created: " + student.getName());
        }
    }
    

	@Override
    public void update(Student student, Long id) {
        String sql = "update student set name = ?, email = ? where id = ?";
        int update = jdbcTemplate.update(sql, student.getName(), student.getEmail(), id);
        if(update == 1) {
           log.info("Student Updated: " + student.getName());
        }
    }
    

	@Override
    public void delete(Long id) {
        String sql = "delete from student where id = ?";
        int delete = jdbcTemplate.update(sql,id);
        if(delete == 1) {
            log.info("Student Deleted: " + id);
        }
    }
    
    @Override
    public Optional<Student> findById(Long id) {
        String sql = "SELECT id, name, email, dob FROM student WHERE id = ?";
        Student student = null;
        try {
        	student = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { id });
        } catch (DataAccessException ex) {
           log.error("Student not found for given id: " + id);
        }
        return Optional.ofNullable(student);
    } 
    
    @Override
    public Optional<Student> findByEmail(String email) {
    	
    	String sql = "SELECT id, name, email, dob FROM student WHERE email = ?";
        Student student = null;
        try {
        	student = jdbcTemplate.queryForObject(sql, rowMapper, new Object[] { email });
        } catch (DataAccessException ex) {
        	log.error("Student not found for given email: " + email);
        }
        
        return Optional.ofNullable(student);
    	
    }
    
    @Override
    public boolean isEmailTaken(String email) {
    	
    	String sql = "SELECT EXISTS (SELECT 1 FROM student WHERE email = ?)";
    	
    	return jdbcTemplate.queryForObject(sql, (resultSet, i) -> resultSet.getBoolean(1), new Object[] { email });
    	
    }

}
