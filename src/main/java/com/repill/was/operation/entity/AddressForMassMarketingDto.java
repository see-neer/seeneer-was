package com.repill.was.operation.entity;

import com.repill.was.global.exception.BadRequestException;
import lombok.Getter;

@Getter
public class AddressForMassMarketingDto {

    private String code;
    private String siDoName;
    private String siGunName;
    private String umpName;
    private String dongName;

    public AddressForMassMarketingDto(String code, String siDoName, String siGunName, String umpName, String dongName) {
        this.code = code;
        this.siDoName = siDoName;
        this.siGunName = siGunName;
        this.umpName = umpName;
        this.dongName = dongName;
    }

    public AddressForMassMarketingDto(String code, String siDoName, String siGunName, String umpName) {
        this.code = code;
        this.siDoName = siDoName;
        this.siGunName = siGunName;
        this.umpName = umpName;
    }

    public AddressForMassMarketingDto(String code, String siDoName, String siGunName) {
        this.code = code;
        this.siDoName = siDoName;
        this.siGunName = siGunName;
    }

    public AddressForMassMarketingDto(String code, String siDoName) {
        this.code = code;
        this.siDoName = siDoName;
    }

    public static Address newOne(String[] addressRaw){
        if(addressRaw.length == 2){
            return new Address(addressRaw[0], addressRaw[1], null, null, null);
        }

        if(addressRaw.length == 3){
            return new Address(addressRaw[0], addressRaw[1], addressRaw[2], null, null);
        }

        if(addressRaw.length == 4){
            return new Address(addressRaw[0], addressRaw[1], addressRaw[2], addressRaw[3], null);
        }
        if(addressRaw.length == 5) {
            return new Address(addressRaw[0], addressRaw[1], addressRaw[2], addressRaw[3], addressRaw[4]);
        }
        throw new BadRequestException("there is a wrong number of columns");
    }
}
