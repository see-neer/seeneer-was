package com.repill.was.operation.entity;

import java.util.List;
import java.util.Optional;

public interface NickNameRepository {

    List<String> findByFirstNickName();
    List<String> findBySecondNickName();
}
