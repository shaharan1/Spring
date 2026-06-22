package emranhss.com.Modern_Hospital_Management_System.serviceimp;

import emranhss.com.Modern_Hospital_Management_System.dto.mapper.DoctorChargeMapper;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import emranhss.com.Modern_Hospital_Management_System.repository.AdmittedPatientRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.BedBookingRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorChargeRepository;
import emranhss.com.Modern_Hospital_Management_System.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class DoctorChargeServiceImp implements DoctorRepository {


    private final DoctorChargeRepository doctorChargeRepository;
    private final DoctorRepository doctorRepository;
    private final BedBookingRepository bedBookingRepository;
    private final AdmittedPatientRepository admittedPatientRepository;
    private final DoctorChargeMapper doctorChargeMapper;

    @Override
    public List<Doctor> findBySpecialization(String specialization) {
        return List.of();
    }

    @Override
    public List<Doctor> findByDepartmentId(Long departmentId) {
        return List.of();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Doctor> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Doctor> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Doctor> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Doctor getOne(Long aLong) {
        return null;
    }

    @Override
    public Doctor getById(Long aLong) {
        return null;
    }

    @Override
    public Doctor getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Doctor> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Doctor> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Doctor> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Doctor> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Doctor> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Doctor> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Doctor, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Doctor> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Doctor> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Doctor> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Doctor> findAll() {
        return List.of();
    }

    @Override
    public List<Doctor> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Doctor entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Doctor> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Doctor> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Doctor> findAll(Pageable pageable) {
        return null;
    }
}
