package com.eatitdog.eatitdog.domain.product.presentation.dto.api;

import lombok.Data;

import java.util.List;

@Data
public class ProductAPIDto {

    private Body body;
    private Header header;

    @Data
    public static class Header {
        private String resultCode;
        private String resultMessage;
    }

    @Data
    public static class Body {
        private List<ItemWrapper> items;
        private String totalCount;
        private String pageNo;
        private String numOfRows;
    }

    @Data
    public static class ItemWrapper {
        private Item item;
    }

    @Data
    public static class Item {
        private String nutrient;
        private String rawmtrl;
        private String prdlstNm;
        private String imgurl1;
        private String imgurl2;
        private String productGb;
        private String seller;
        private String prdkindstate;
        private String rnum;
        private String manufacture;
        private String barcode;
        private String prdkind;
        private String capacity;
        private String prdlstReportNo;
        private String allergy;
    }
}
