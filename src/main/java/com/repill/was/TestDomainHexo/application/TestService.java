package com.repill.was.TestDomainHexo.application;

import com.repill.was.TestDomainHexo.application.port.in.TestUseCase;
import com.repill.was.TestDomainHexo.application.port.out.TestPort;

public class TestService implements TestUseCase {

    private TestPort testPort;

    @Override
    public void test() {
        testPort.test();
    }
}
