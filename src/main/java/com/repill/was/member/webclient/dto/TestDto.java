package com.repill.was.member.webclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

@Data
public class TestDto {
    private Header header;
    private Body body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Header {
        private String resultCode;
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    public static class Body {
        private int pageNo;
        private int totalCount;
        private int numOfRows;
        private List<Item> items;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(int numOfRows) {
            this.numOfRows = numOfRows;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public static class Item {
        private Product item;

        public Product getItem() {
            return item;
        }

        public void setItem(Product item) {
            this.item = item;
        }
    }

    public static class Product {
        private String ENTRPS;
        private String PRDUCT;
        private String STTEMNT_NO;
        private String REGIST_DT;

        public String getENTRPS() {
            return ENTRPS;
        }

        public void setENTRPS(String ENTRPS) {
            this.ENTRPS = ENTRPS;
        }

        public String getPRDUCT() {
            return PRDUCT;
        }

        public void setPRDUCT(String PRDUCT) {
            this.PRDUCT = PRDUCT;
        }

        public String getSTTEMNT_NO() {
            return STTEMNT_NO;
        }

        public void setSTTEMNT_NO(String STTEMNT_NO) {
            this.STTEMNT_NO = STTEMNT_NO;
        }

        public String getREGIST_DT() {
            return REGIST_DT;
        }

        public void setREGIST_DT(String REGIST_DT) {
            this.REGIST_DT = REGIST_DT;
        }
    }
}

