package com.customer_management.customer_pt.util.mapper;

import com.customer_management.customer_pt.dto.request.CustomerRequestDto;
import com.customer_management.customer_pt.dto.response.CustomerResponseDto;
import com.customer_management.customer_pt.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequestDto dto);

    CustomerResponseDto toDTO(Customer customer);

    List<CustomerResponseDto> toDTOList(List<Customer> customer);
}
