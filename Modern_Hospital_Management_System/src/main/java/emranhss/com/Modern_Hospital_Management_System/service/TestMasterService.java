package emranhss.com.Modern_Hospital_Management_System.service;

import emranhss.com.Modern_Hospital_Management_System.dto.response.TestMasterResponse;

import java.util.List;

public interface TestMasterService {


    List<TestMasterResponse> getAll();

    List<TestMasterResponse> search(String keyword);
}
