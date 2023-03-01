package com.crud.optration.repository;

import com.crud.optration.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepo extends JpaRepository<Emp ,Long >{
}
