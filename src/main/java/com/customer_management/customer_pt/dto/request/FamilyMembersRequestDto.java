package com.customer_management.customer_pt.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilyMembersRequestDto {
    private Long id;
    private String number;
}
