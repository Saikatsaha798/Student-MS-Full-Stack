package com.task.StudentMS.repository;


import com.task.StudentMS.entity.EntityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Integer> {

//    @Query(nativeQuery = true, value = "select * from user_data where username = :usr;")
    EntityUser findByUsername(String username);
}
