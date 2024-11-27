package com.task.StudentMS.repository;

import com.task.StudentMS.entity.EntityAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Including JPA modules
@Repository
public interface AddressRepository extends JpaRepository<EntityAddress, Long>{

}