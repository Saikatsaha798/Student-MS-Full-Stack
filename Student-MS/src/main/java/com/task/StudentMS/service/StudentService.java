package com.task.StudentMS.service;

import com.task.StudentMS.model.Student;

import java.util.List;
import java.util.Map;

//An overview of modules of service
public interface StudentService {
    String createStudent(Student std);
    Student getStudent(Long id);
    List<Student> getAllStudents();
    String updateStudent(Long id, Student std);
    String deleteStudent(Long id);
    List<Map<String, Long>> generateReport();
    List<Student> getPage(int pageSize, int pageOffset);
}
