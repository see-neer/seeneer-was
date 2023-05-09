package com.repill.was.TestDomainLayered.service;

import com.repill.was.TestDomainLayered.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;

    @Override
    public void test() {
        testRepository.test();
    }
}
