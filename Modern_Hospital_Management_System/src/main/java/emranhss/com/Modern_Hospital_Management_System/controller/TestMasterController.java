package emranhss.com.Modern_Hospital_Management_System.controller;


import emranhss.com.Modern_Hospital_Management_System.dto.response.TestMasterResponse;
import emranhss.com.Modern_Hospital_Management_System.service.TestMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TestMasterController {


    private final TestMasterService testMasterService;

    @GetMapping
    public List<TestMasterResponse> getAll() {

        return testMasterService.getAll();

    }

    @GetMapping("/search")
    public List<TestMasterResponse> search(
            @RequestParam String keyword) {

        return testMasterService.search(keyword);

    }

}
