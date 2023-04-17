package com.repill.was.member.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.repill.was.member.webclient.dto.TestDto;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import org.apache.tomcat.util.json.ParseException;

public interface TestWebClient {

    TestDto test() throws URISyntaxException;
}
