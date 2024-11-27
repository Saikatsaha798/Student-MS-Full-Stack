package com.task.StudentMS.repository;

import com.task.StudentMS.entity.EntityStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;



//JPA repository modules
@Repository
public interface StudentRepository extends JpaRepository<EntityStudent, Long>{

    @Query(nativeQuery = true, value = "SELECT e.stu_class as Class, COUNT(e.id) as NumberOfStudents from student as e group by e.stu_class;")
    List<Map<String, Object>> countStudentsPerClass();

    @Query(nativeQuery = true, value = "select * from student limit :pageSize offset :offset;")
    List<EntityStudent> getPage(@Param("pageSize") int pageSize, @Param("offset") int offset);
    
}