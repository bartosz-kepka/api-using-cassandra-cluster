package pl.nbd.api.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nbd.api.exception.NotFoundException;
import pl.nbd.api.model.Test;
import pl.nbd.api.repository.TestRepository;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public Test addTest(Test test) {
        test.setId(Uuids.timeBased().toString());
        return testRepository.insert(test);
    }

    public Test getTest(String id) {
        return testRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat
                .format("Test with id \"{0}\" not found", id)));
    }
}
