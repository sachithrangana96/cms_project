package com.customer_management.customer_pt.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileNumberRequestDto {
    private Long id;
    private String number;
}
