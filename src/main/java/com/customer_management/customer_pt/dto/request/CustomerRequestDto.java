package com.customer_management.customer_pt.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerRequestDto {
    private String name;
    private Date dateOfBirth;
    private String nic;

    private List<MobileNumberRequestDto> mobileNumbers;
    private List<AddressRequestDto> addresses;
    private List<Long> familyMemberIds;
}
