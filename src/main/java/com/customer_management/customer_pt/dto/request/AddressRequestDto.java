package com.customer_management.customer_pt.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressRequestDto {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
}
