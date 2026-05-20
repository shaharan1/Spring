package com.emranhss.ModernHospitalSystemManagement.controller;


import com.emranhss.ModernHospitalSystemManagement.entity.Department;
import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
import com.emranhss.ModernHospitalSystemManagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Department/")
public class DepartmentController {

    @Autowired
    private DepartmentService depService;


    @PostMapping
    public ResponseEntity<Department> save (@RequestBody Department d)
    {
        Department savedDepartment= depService.saveOrUpdate(d);
     return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Department>> getAll(){

        List<Department> list=depService.getAll();
        return ResponseEntity.ok(list);
    }

}
