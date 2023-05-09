package com.repill.was.TestDomainLayered.controller;

import com.repill.was.TestDomainLayered.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {

    private TestService testService;

    @GetMapping("/test")
    public void test() {
        testService.test();
    }
}
