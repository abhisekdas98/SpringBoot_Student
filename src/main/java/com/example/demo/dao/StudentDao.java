package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.student.Student;

public interface StudentDao extends JpaRepository<Student,Integer> {
	
	public List<Student> findBySchool(String school);
	
	@Query("from Student order by Regno")
	publi  c List<Student> sortByReg();
	
	@Query("from Student order by Name")
	public List<Student> sortByName();
	
	@Query("from Student order by Std")
	public List<Student> sortByStd();
	
	@Query("from Student where percent>=40 order by Percent")
	public List<Student> getPassStudents();
	
	@Query("from Student where percent<40 order by Percent")
	public List<Student> getFailStudents();
	
}
