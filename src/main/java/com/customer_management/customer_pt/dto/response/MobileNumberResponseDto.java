package com.customer_management.customer_pt.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MobileNumberResponseDto {
    private Long id;
    private String number;
}
