package com.customer_management.customer_pt.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MetaDataResponseDto {
    private Integer code;
    private String message;
}
