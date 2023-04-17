package com.repill.was.member.webclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

@Data
public class TestDto {
        public Header header;
        public Body body;

    public TestDto() {
    }

    public static class Header {
            public String resultCode;
            public String resultMsg;
        }

        public static class Body {
            public int pageNo;
            public int totalCount;
            public int numOfRows;
            public Item[] items;
        }

        public static class Item {
            public Product item;
        }

        public static class Product {
            public String ENTRPS;
            public String PRDUCT;
            public String STTEMNT_NO;
            public String REGIST_DT;
        }
}

