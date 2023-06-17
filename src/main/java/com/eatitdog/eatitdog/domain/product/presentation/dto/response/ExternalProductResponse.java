package com.eatitdog.eatitdog.domain.product.presentation.dto.response;

import com.eatitdog.eatitdog.domain.product.presentation.dto.api.ProductAPIDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExternalProductResponse implements Serializable {
    private String id;
    private String name;
    private String image;
    private String kind;

    public static ExternalProductResponse dtoToResponse(ProductAPIDto.Item item) {
        return ExternalProductResponse.builder()
                .id(item.getPrdlstReportNo())
                .name(item.getPrdlstNm())
                .image(item.getImgurl1())
                .kind(item.getPrdkind())
                .build();
    }

    public static List<ExternalProductResponse> dtoListToResponse(List<ProductAPIDto.ItemWrapper> itemList) {
        return itemList.stream().map(
                item -> ExternalProductResponse.dtoToResponse(item.getItem())
        ).collect(Collectors.toList());
    }
}
