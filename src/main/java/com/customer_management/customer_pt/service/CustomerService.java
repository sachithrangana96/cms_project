package com.customer_management.customer_pt.service;

import com.customer_management.customer_pt.dto.request.CustomerRequestDto;
import com.customer_management.customer_pt.dto.response.CustomerResponseDto;
import com.customer_management.customer_pt.dto.response.PaginatedCustomerResponseDto;
import com.customer_management.customer_pt.exception.ApplicationException;
import com.customer_management.customer_pt.exception.NullNotAllowedException;
import com.customer_management.customer_pt.exception.ValueNotExistException;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto createCustomer(CustomerRequestDto dto) throws ApplicationException;
    CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) throws NullNotAllowedException, ValueNotExistException;
    CustomerResponseDto getCustomerById(Long id) throws NullNotAllowedException, ValueNotExistException;
    PaginatedCustomerResponseDto getAllCustomers(Integer pageNumber, Integer pageSize) throws ApplicationException;
}
