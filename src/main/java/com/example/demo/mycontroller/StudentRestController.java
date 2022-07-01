package com.example.demo.mycontroller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.dao.StudentDao;
import com.example.demo.student.Student;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class StudentRestController {
	
	@Autowired
    StudentDao dao;
	
	@GetMapping("/students")
	public List<Student> getStudents()
	{
		return dao.findAll();
	}
	@GetMapping("/student/{regno}")
	public Optional<Student> getStudentByRegno(@PathVariable("regno") int eid)
	{
		return dao.findById(eid);
	}
	
	@PostMapping(path="/student",consumes= {"application/json","application/xml"})
	public String insertStudent(@RequestBody Student s)
	{
		System.out.println(s);
		dao.save(s);
		return "Student inserted successfully";
	}
	
	@PutMapping("/student")
	public String updateStudent(@RequestBody Student e)
	{
		
		if(dao.existsById(e.getRegno()))
		{ 
			System.out.println(e);
		    dao.save(e);
		    return "Student updated successfully";
		    
		}
		else
		return "No Student present with the given id: "+e.getRegno();
		
		
	}
   
	@DeleteMapping("/student/{regno}")
	public String deleteStudent(@PathVariable("regno") int id)
	{
		if(dao.existsById(id))
		{
		   dao.deleteById(id);
		   return "Student details deleted";
		}
		else
		return "No Student present with the given id: "+id;
	}
	
	@GetMapping("/students/{school}")
	public List<Student> getStudentsBySchool(@PathVariable String school)
	{
		return dao.findBySchool(school);
	}
	
	@GetMapping("/students1/sort")
	public List<Student> sortByRegno(@RequestParam String column)
	{
		if(column.equalsIgnoreCase("regno"))
		return dao.sortByReg();
		else if(column.equalsIgnoreCase("name"))
			return dao.sortByName();
		else if(column.equalsIgnoreCase("standard"))
			return dao.sortByStd();
		
		return new LinkedList<Student>();
	}
	
	@GetMapping("/passstudents/result")
	public List<Student> getfuncPass(@RequestParam boolean pass)
	{
		if(pass)
			return dao.getPassStudents();
		else
		   return dao.getFailStudents();
	}
    
	
}
