package com.repill.was.member.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.repill.was.member.webclient.dto.TestDto;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface TestWebClient {

    TestDto test() throws URISyntaxException;

    String login(String code);
}
