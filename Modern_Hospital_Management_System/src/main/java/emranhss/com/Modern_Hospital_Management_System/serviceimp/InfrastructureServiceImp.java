package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import emranhss.com.Modern_Hospital_Management_System.dto.mapper.CoreInfrastructureMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.BedRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.request.DepartmentRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.request.FacilityRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.request.WardRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.BedResponse;
import emranhss.com.Modern_Hospital_Management_System.dto.response.DepartmentResponse;
import emranhss.com.Modern_Hospital_Management_System.dto.response.FacilityResponse;
import emranhss.com.Modern_Hospital_Management_System.dto.response.WardResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.Bed;
import emranhss.com.Modern_Hospital_Management_System.entity.Department;
import emranhss.com.Modern_Hospital_Management_System.entity.Facility;
import emranhss.com.Modern_Hospital_Management_System.entity.Ward;
import emranhss.com.Modern_Hospital_Management_System.enums.BedStatus;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.BedRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.DepartmentRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.FacilityRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.WardRepository;
import emranhss.com.Modern_Hospital_Management_System.service.InfrastructureService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InfrastructureServiceImp implements InfrastructureService {

    private final DepartmentRepository departmentRepository;
    private final FacilityRepository facilityRepository;
    private final WardRepository wardRepository;
    private final BedRepository bedRepository;
    private final CoreInfrastructureMapper mapper;


    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department dept = new Department();
        BeanUtils.copyProperties(request, dept);
        return mapper.toResponse(departmentRepository.save(dept));
    }

    @Override
    public FacilityResponse createFacility(FacilityRequest request) {
        Facility fac = new Facility();
        BeanUtils.copyProperties(request, fac);
        return mapper.toResponse(facilityRepository.save(fac));
    }

    @Override
    public WardResponse createWard(WardRequest request) {
        Department dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        Ward ward = new Ward();
        BeanUtils.copyProperties(request, ward);
        ward.setDepartment(dept);
        return mapper.toResponse(wardRepository.save(ward));
    }

    @Override
    @Transactional
    public BedResponse createBed(BedRequest request) {
        Ward ward = wardRepository.findById(request.getWardId())
                .orElseThrow(() -> new ResourceNotFoundException("Ward not found"));

        Bed bed = new Bed();
        bed.setBedNumber(request.getBedNumber());
        bed.setWard(ward);
        if (request.getStatus() != null) bed.setStatus(request.getStatus());

        if (request.getFacilityIds() != null && !request.getFacilityIds().isEmpty()) {
            List<Facility> facilities = facilityRepository.findAllById(request.getFacilityIds());
            bed.setFacilities(new HashSet<>(facilities));
        }

        return mapper.toResponse(bedRepository.save(bed));
    }

    @Override
    public List<BedResponse> getBedsByStatus(BedStatus status) {
        return bedRepository.findByStatus(status).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BedResponse updateBedStatus(Long id, BedStatus status) {
        Bed bed = (Bed) bedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bed record not found"));
        bed.setStatus(status);
        return mapper.toResponse(bedRepository.save(bed));
    }
}
