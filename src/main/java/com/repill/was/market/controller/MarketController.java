package com.repill.was.market.controller;

import com.repill.was.member.controller.MemberV1Controller;
import com.repill.was.operation.entity.Address;
import com.repill.was.operation.entity.AddressForMassMarketingDto;
import com.repill.was.operation.entity.AddressRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@MarketV1Controller
@RequiredArgsConstructor
public class MarketController {
}
