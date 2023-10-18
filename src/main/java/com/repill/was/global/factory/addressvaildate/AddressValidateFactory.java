package com.repill.was.global.factory.addressvaildate;

import com.repill.was.operation.entity.AddressInfoRepository;
import com.repill.was.operation.entity.AddressNotFoundException;

public final class AddressValidateFactory {

    public void validateAddress(AddressInfoRepository addressInfoRepository, String addressDetailA, String addressDetailB) {
        addressInfoRepository.findByAddressDetailAAndAddressDetailB(addressDetailA, addressDetailB)
                .orElseThrow(AddressNotFoundException::new);
    }
}
