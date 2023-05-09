package com.repill.was.TestDomainHexo.adapter.out;

import com.repill.was.TestDomainHexo.application.port.out.TestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TestPersistenceAdapter implements TestPort {

    private TestRepository testRepository;

    @Override
    public void test() {
        testRepository.test();
    }
}
