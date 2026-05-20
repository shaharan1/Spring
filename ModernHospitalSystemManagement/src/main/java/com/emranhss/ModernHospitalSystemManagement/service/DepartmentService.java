package com.emranhss.ModernHospitalSystemManagement.service;

import com.emranhss.ModernHospitalSystemManagement.entity.Department;
import com.emranhss.ModernHospitalSystemManagement.entity.policeStation;
import com.emranhss.ModernHospitalSystemManagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository depRepository;

    public List<Department> getAll(){

        return depRepository.findAll();
    }

    public Department saveOrUpdate(Department d){
        return depRepository.save(d);
    }

    public Optional<Department> getByIdDepartment(long id){

        return depRepository.findById(id);
    }

    public void delete(long id){

        depRepository.deleteById(id);
    }
}
