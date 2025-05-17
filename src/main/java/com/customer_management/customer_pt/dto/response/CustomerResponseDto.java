package com.customer_management.customer_pt.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerResponseDto {
    private Long id;
    private String name;
    private Date dateOfBirth;
    private String nic;

    private List<MobileNumberResponseDto> mobileNumbers;
    private List<AddressResponseDto> addresses;
    private List<FamilyMemberResponseDto> familyMembers;
}
