package com.eatitdog.eatitdog.domain.report.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eatitdog.eatitdog.global.statics.ValidMessageConstants.PRODUCT_ID_NOT_BLANK;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateReportRequest {

    @NotBlank(message = PRODUCT_ID_NOT_BLANK)
    private String productId;
}
