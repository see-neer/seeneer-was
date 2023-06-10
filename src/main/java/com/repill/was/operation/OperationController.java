package com.repill.was.operation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.repill.was.global.config.SwaggerConfig;
import com.repill.was.global.exception.BadRequestException;
import com.repill.was.member.controller.MemberV1Controller;
import com.repill.was.member.entity.Address;
import com.repill.was.member.entity.AddressForMassMarketingDto;
import com.repill.was.member.entity.AddressRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@MemberV1Controller
@RequiredArgsConstructor
public class OperationController {
    private final AddressRepository addressRepository;

    @ApiOperation("매스마케팅용 주소목록 CSV 파일 업로드")
    @PostMapping("/address")
    public void uploadCsv(@RequestPart MultipartFile csv, @PathVariable(name = "id") long zoneId){
        List<Address> addressDtoList = getAddressDtoList(csv);
        insertAll(addressDtoList);
    }

    void insertAll(List<Address> addressDtoList) {
        addressDtoList.forEach(dto -> {
            Address address = Address.newOne(
                    addressRepository.nextId(),
                    dto.getAddressCodeA(),
                    dto.getAddressDetailA(),
                    dto.getAddressDetailB(),
                    dto.getAddressDetailC(),
                    dto.getAddressDetailD());
            addressRepository.save(address);
        });
    }

    public List<Address> getAddressDtoList(MultipartFile csv) {
        List<String[]> parsedList = parse(csv);
        return parsedList.stream().map(AddressForMassMarketingDto::newOne)
                    .collect(Collectors.toList());
    }

    public List<String[]> parse(MultipartFile csv) {
        List<String[]> addressList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csv.getInputStream()))) {
            String lineText = "";
            reader.readLine(); // 첫줄 skip
            while ((lineText = reader.readLine()) != null) {
                String[] rawData = lineText.split(",");
                addressList.add(rawData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressList;
    }
}
