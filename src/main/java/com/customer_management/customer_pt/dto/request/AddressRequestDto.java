package com.customer_management.customer_pt.dto.request;

import lombok.Data;

@Data
public class AddressRequestDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
}
