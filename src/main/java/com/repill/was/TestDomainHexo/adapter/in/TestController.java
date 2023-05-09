package com.repill.was.TestDomainHexo.adapter.in;

import com.repill.was.TestDomainHexo.application.port.in.TestUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Repository
public class TestController {

    private TestUseCase testUseCase;

    @GetMapping("/test")
    public void test() {
        testUseCase.test();
    }
}
