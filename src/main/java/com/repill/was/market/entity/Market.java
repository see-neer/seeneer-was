package com.repill.was.market.entity;

import com.repill.was.operation.entity.AddressId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    @EmbeddedId
    MarketId id;


}