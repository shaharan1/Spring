package emranhss.com.Modern_Hospital_Management_System.serviceimp;

import emranhss.com.Modern_Hospital_Management_System.dto.mapper.TestMasterMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.response.TestMasterResponse;
import emranhss.com.Modern_Hospital_Management_System.repository.TestMasterRepository;
import emranhss.com.Modern_Hospital_Management_System.service.TestMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TestMasterServiceImpl implements TestMasterService {
    private final TestMasterMapper testMasterMapper;
    private final TestMasterRepository;

    public TestMasterServiceImpl(TestMasterMapper testMasterMapper) {
        this.testMasterMapper = testMasterMapper;
    }

    @Override
    public List<TestMasterResponse> getAll() {
        return List.of();
    }

    @Override
    public List<TestMasterResponse> search(String keyword) {
        return repository
                .findByTestNameContainingIgnoreCaseOrTestCodeContainingIgnoreCase(
                        keyword,
                        keyword
                )
                .stream()
                .map(testMasterMapper::toResponse)
                .toList();
    }
}
