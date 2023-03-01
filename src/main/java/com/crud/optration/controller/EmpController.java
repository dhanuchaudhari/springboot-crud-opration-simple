package com.crud.optration.controller;

import com.crud.optration.entity.Emp;
import com.crud.optration.exception.ResourceNotFoundException;
import com.crud.optration.repository.EmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private EmpRepo empRepo;

    @GetMapping
    public List<Emp>getAllEmployee(){
        return empRepo.findAll();
    }
    @PostMapping
    public Emp createemp(@RequestBody Emp emp){
        return empRepo.save(emp);
    }

    @GetMapping("{id}")
    public ResponseEntity<Emp> getEmployeeById(@PathVariable Long id){
        Emp emp = empRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exit with id"+id));
        return ResponseEntity.ok(emp);
    }
@PutMapping("{id}")
public ResponseEntity<Emp> updateEmployeeById(@PathVariable Long id ,Emp employeeDetail){
    Emp updateEmp = empRepo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Employee not exit with id"+id));

    updateEmp.setFname(employeeDetail.getFname());
    updateEmp.setLname(employeeDetail.getFname());
    updateEmp.setEmail(employeeDetail.getEmail());

    empRepo.save(updateEmp);
    return ResponseEntity.ok(updateEmp);
}
@DeleteMapping("{id}")
public ResponseEntity<HttpEntity >deleteEmp(@PathVariable long id){
        Emp emp =empRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exit with id"+id));
        empRepo.delete(emp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


