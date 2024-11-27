package com.task.StudentMS.controller;

import java.util.List;
import java.util.Map;

import com.task.StudentMS.model.Student;
import com.task.StudentMS.service.StudentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController     //Rest API
@RequestMapping("students")
//@CrossOrigin("http://localhost:3000/")
public class StudentController {
    
    @Autowired      //Dependency Injection
    StudentServiceImp stdServiceImp;

    @GetMapping("read")          //Read all students
    public List<Student> getAllStudent() {
        return stdServiceImp.getAllStudents();
    }

    @GetMapping("read/{id}")     //Read one student
    public Student getStudent(@PathVariable Long id) {
        return stdServiceImp.getStudent(id);
    }

    @PostMapping("create")         //Create student
    @ResponseStatus(HttpStatus.CREATED)
    public String createStudent(@Valid @RequestBody Student std) {
        return stdServiceImp.createStudent(std);
    }

    @PutMapping("update/{id}")     //Update student
    public String updateStudent(@PathVariable Long id, @Valid @RequestBody Student std) {
        return stdServiceImp.updateStudent(id, std);
    }
    
    @DeleteMapping("delete/{id}")  //Delete student
    public String deleteStudent(@PathVariable Long id){
        return stdServiceImp.deleteStudent(id);
    }

    @GetMapping("report")
    public List<Map<String, Long>> generateReport(){
        return stdServiceImp.generateReport();
    }

    @GetMapping("page/{pageSize}/{pageOffset}")
    public List<Student> getPage(@PathVariable int pageSize, @PathVariable int pageOffset){
        return stdServiceImp.getPage(pageSize, pageOffset);
    }

}
