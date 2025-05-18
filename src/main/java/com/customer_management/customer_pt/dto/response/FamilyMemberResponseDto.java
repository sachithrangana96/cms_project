package com.customer_management.customer_pt.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class FamilyMemberResponseDto {
    private Long id;
    private String name;
    private String nic;
    private Date dateOfBirth;
}
