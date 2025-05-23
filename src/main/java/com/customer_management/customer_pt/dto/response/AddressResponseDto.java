package com.customer_management.customer_pt.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponseDto {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
}
