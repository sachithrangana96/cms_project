package com.customer_management.customer_pt.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerResponseDto {
    private Long id;
    private String name;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String nic;

    private List<MobileNumberResponseDto> mobileNumbers;
    private List<AddressResponseDto> addresses;
    private List<FamilyMemberResponseDto> familyMembers;
}
