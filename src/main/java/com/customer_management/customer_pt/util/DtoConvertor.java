package com.customer_management.customer_pt.util;

import com.customer_management.customer_pt.dto.response.AddressResponseDto;
import com.customer_management.customer_pt.dto.response.CustomerResponseDto;
import com.customer_management.customer_pt.dto.response.FamilyMemberResponseDto;
import com.customer_management.customer_pt.dto.response.MobileNumberResponseDto;
import com.customer_management.customer_pt.entity.Address;
import com.customer_management.customer_pt.entity.Customer;
import com.customer_management.customer_pt.entity.MobileNumber;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoConvertor {

    public List<MobileNumberResponseDto> mapToMobileNumberResponseDto(List<MobileNumber> mobileNumbers) {
        if (mobileNumbers == null) return Collections.emptyList();
        return mobileNumbers.stream()
                .map(m -> MobileNumberResponseDto.builder()
                        .id(m.getId())
                        .number(m.getNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public List<AddressResponseDto> mapToAddressResponseDto(List<Address> addresses) {
        if (addresses == null) return Collections.emptyList();
        return addresses.stream()
                .map(a -> AddressResponseDto.builder()
                        .id(a.getId())
                        .addressLine1(a.getAddressLine1())
                        .addressLine2(a.getAddressLine2())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .build())
                .collect(Collectors.toList());
    }


    public CustomerResponseDto convertToResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .nic(customer.getNic())
                .dateOfBirth(customer.getDateOfBirth())
                .mobileNumbers(customer.getMobileNumbers() != null ?
                        customer.getMobileNumbers().stream().map(m ->
                                MobileNumberResponseDto.builder()
                                        .id(m.getId())
                                        .number(m.getNumber())
                                        .build()
                        ).collect(Collectors.toList()) : Collections.emptyList())
                .addresses(customer.getAddress() != null ?
                        customer.getAddress().stream().map(a ->
                                AddressResponseDto.builder()
                                        .id(a.getId())
                                        .addressLine1(a.getAddressLine1())
                                        .addressLine2(a.getAddressLine2())
                                        .city(a.getCity())
                                        .country(a.getCountry())
                                        .build()
                        ).collect(Collectors.toList()) : Collections.emptyList())
                .familyMembers(customer.getFamilyMembers() != null ?
                        customer.getFamilyMembers().stream().map(f ->
                                FamilyMemberResponseDto.builder()
                                        .id(f.getId())
                                        .name(f.getName())
                                        .nic(f.getNic())
                                        .build()
                        ).collect(Collectors.toList()) : Collections.emptyList())
                .build();

    }
}