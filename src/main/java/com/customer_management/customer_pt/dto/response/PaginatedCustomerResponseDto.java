package com.customer_management.customer_pt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedCustomerResponseDto {
    private List<CustomerResponseDto> data;
    private long total;
    private int page;
    private int size;
}
