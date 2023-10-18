package com.repill.was.global.factory;

import com.repill.was.operation.entity.NickNameRepository;

import java.util.Collections;
import java.util.List;

public final class NickNameMakeFactory {

    public static String makeNickName(NickNameRepository nickNameRepository) {
        List<String> byFirstNickName = nickNameRepository.findByFirstNickName();
        List<String> bySecondNickName = nickNameRepository.findBySecondNickName();
        Collections.shuffle(byFirstNickName);
        Collections.shuffle(bySecondNickName);
        return byFirstNickName.get(0) + bySecondNickName.get(0);
    }
}
