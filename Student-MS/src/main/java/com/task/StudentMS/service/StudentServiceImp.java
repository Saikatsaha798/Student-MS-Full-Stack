package com.task.StudentMS.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.StudentMS.model.Address;
import com.task.StudentMS.model.Student;
import com.task.StudentMS.entity.EntityAddress;
import com.task.StudentMS.entity.EntityStudent;
import com.task.StudentMS.repository.AddressRepository;
import com.task.StudentMS.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Service
@Service
public class StudentServiceImp implements StudentService {

    @Autowired      //Dependency injection
    StudentRepository stdRepository;

    @Autowired      //Dependency injection
    AddressRepository addrRepository;

    //Address entity --> Address DTO
    Address fetchAddress(EntityAddress eaddr, Address addr) {
        if (addr == null) {
            addr = new Address();
        }

        if (eaddr.getAddr1() != null) {
            addr.setAddr1(eaddr.getAddr1());
        }

        if (eaddr.getCity() != null) {
            addr.setCity(eaddr.getCity());
        }

        if (eaddr.getPin() != null) {
            addr.setPin(eaddr.getPin());
        }

        if (eaddr.getStreet() != null) {
            addr.setStreet(eaddr.getStreet());
        }

        return addr;
    }

    //Address DTO --> Address entity
    EntityAddress fetchAddress(Address eaddr, EntityAddress addr) {
        if (addr == null) {
            addr = new EntityAddress();
        }

        if (eaddr.getAddr1() != null) {
            addr.setAddr1(eaddr.getAddr1());
        }

        if (eaddr.getCity() != null) {
            addr.setCity(eaddr.getCity());
        }

        if (eaddr.getPin() != null) {
            addr.setPin(eaddr.getPin());
        }

        if (eaddr.getStreet() != null) {
            addr.setStreet(eaddr.getStreet());
        }

        return addr;
    }

    //Student entity --> Student DTO
    void fetchStudent(EntityStudent estd, Student std) {

        std.setId(estd.getId());

        if (estd.getName() != null) {
            std.setName(estd.getName());
        }

        if (estd.getStuClass() != null) {
            std.setStuClass(estd.getStuClass());
        }

        if (estd.getAge() != null) {
            std.setAge(estd.getAge());
        }

        if (estd.getAddress() != null) {
            std.setAddress(this.fetchAddress(estd.getAddress(), std.getAddress()));
        }
    }

    //Student DTO --> Student entity
    void fetchStudent(Student estd, EntityStudent std) {
        if (estd.getName() != null) {
            std.setName(estd.getName());
        }

        if (estd.getStuClass() != null) {
            std.setStuClass(estd.getStuClass());
        }

        if (estd.getAge() != null) {
            std.setAge(estd.getAge());
        }

        if (estd.getAddress() != null) {
            std.setAddress(this.fetchAddress(estd.getAddress(), std.getAddress()));
        }
    }

    //Create student
    @Override
    public String createStudent(Student std) {
        EntityStudent estd = new EntityStudent();
        this.fetchStudent(std, estd);

        addrRepository.save(estd.getAddress());
        stdRepository.save(estd);

        return "Added sucessfully.";
    }

    //Get all students
    @Override
    public List<Student> getAllStudents() {
        List<EntityStudent> estdArr = stdRepository.findAll();
        List<Student> stdArr = new ArrayList<>();

        for (EntityStudent estd : estdArr) {
            Student std = new Student();

            this.fetchStudent(estd, std);

            stdArr.add(std);
        }

        return stdArr;
    }

    //Get one student by ID
    @Override
    public Student getStudent(Long id) {
        Student std = new Student();

        EntityStudent estd = stdRepository.findById(id).get();
        this.fetchStudent(estd, std);

        return std;
    }

    //Update details of a student
    @Override
    public String updateStudent(Long id, Student std) {
        EntityStudent estd = stdRepository.findById(id).get();

        this.fetchStudent(std, estd);

        stdRepository.save(estd);
        addrRepository.save(estd.getAddress());

        return "Updated successfully.";
    }

    //Delete record of a student
    @Override
    public String deleteStudent(Long id) {
        EntityStudent estd = stdRepository.findById(id).get();

        EntityAddress eaddr = estd.getAddress();

        stdRepository.delete(estd);
        addrRepository.delete(eaddr);

        return "Deleted successfully.";
    }

//    @Override
//    public List<Map<String, Long>> generateReport() {
//        List<entStudent> estdArr = stdRepository.findAll();
//        long[] noOfStudents = new long[12];
//
//        List<Map<String, Long>> report = new ArrayList<Map<String, Long>>();
//
//        for (entStudent estd : estdArr){
//            noOfStudents[estd.getStuClass()-1]++;
//        }
//
//        for (int i=0; i<noOfStudents.length; i++){
//            Map<String, Long> classData = new HashMap<String, Long>();
//
//            if (noOfStudents[i] != 0) {
//                classData.put("Number of students", noOfStudents[i]);
//                classData.put("Class", (long) i + 1);
//
//                report.add(classData);
//            }
//        }
//
//        return report;
//    }

    @Override
    public List<Map<String, Long>> generateReport() {
        List<Map<String, Object>> query_results = stdRepository.countStudentsPerClass();
//        System.out.println(query_results.toString());
        List<Map<String, Long>> report = new ArrayList<>();

        for (Map<String, Object> rp : query_results){
            HashMap<String, Long> elem = new HashMap<String, Long>();

            elem.put("Class", ((Number) rp.get("Class")).longValue());
            elem.put("No of students",((Number) rp.get("NumberOfStudents")).longValue());
            report.add(elem);
        }

        return report;
    }

//    @Override
//    public List<Student> getPage(int pageSize, int pageOffset) {
//        Page<entStudent> estdArr = stdRepository.findAll(PageRequest.of(pageOffset, pageSize));
//        List<Student> stdArr = new ArrayList<Student>();
//
//        for (entStudent estd : estdArr){
//            Student std = new Student();
//
//            this.fetchStudent(estd, std);
//
//            stdArr.add(std);
//        }
//        return strArr;

    @Override
    public List<Student> getPage(int pageSize, int pageOffset) {

        List<EntityStudent> estdArr = stdRepository.getPage(pageSize, pageSize*(pageOffset-1));

        List<Student> stdArr = new ArrayList<Student>();
        for (EntityStudent estd : estdArr){
            Student std = new Student();

            this.fetchStudent(estd, std);

            stdArr.add(std);
        }

        return stdArr;
    }
}
