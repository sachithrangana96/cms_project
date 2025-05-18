package com.customer_management.customer_pt.service;

import com.customer_management.customer_pt.dto.request.AddressRequestDto;
import com.customer_management.customer_pt.dto.request.CustomerRequestDto;
import com.customer_management.customer_pt.dto.request.MobileNumberRequestDto;
import com.customer_management.customer_pt.dto.response.*;
import com.customer_management.customer_pt.entity.Address;
import com.customer_management.customer_pt.entity.Customer;
import com.customer_management.customer_pt.entity.MobileNumber;
import com.customer_management.customer_pt.exception.ApplicationException;
import com.customer_management.customer_pt.exception.NullNotAllowedException;
import com.customer_management.customer_pt.exception.ValueNotExistException;
import com.customer_management.customer_pt.repository.AddressRepository;
import com.customer_management.customer_pt.repository.CustomerRepository;
import com.customer_management.customer_pt.repository.MobileNumberRepository;
import com.customer_management.customer_pt.util.DtoConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MobileNumberRepository mobileNumberRepository;

    @Autowired
    private DtoConvertor dtoConvertor;




    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto dto) throws ApplicationException {

        Optional<Customer> byNice = customerRepository.findByNice(dto.getNic());

        if(byNice.isPresent()){
            throw new ApplicationException("Already Available this Nic" +dto.getNic());
        }

        // Step 1: Create base customer (without mobile numbers yet)
        Customer customer = Customer.builder()
                .name(dto.getName())
                .nic(dto.getNic())
                .dateOfBirth(dto.getDateOfBirth())
                .build();

        // Fetch family members by IDs
        if (dto.getFamilyMemberIds() != null && !dto.getFamilyMemberIds().isEmpty()) {
            List<Customer> familyMembers = customerRepository.findAllById(dto.getFamilyMemberIds());
            customer.setFamilyMembers(familyMembers);
        }

        // Step 2: Map mobile numbers and set customer reference
        List<MobileNumber> numbers = dto.getMobileNumbers().stream()
                .map(m ->{
                    MobileNumber mb = new MobileNumber();
                    mb.setCustomer(customer);
                    mb.setNumber(m.getNumber());
                    return mb;
                })
                .collect(Collectors.toList());

        customer.setMobileNumbers(numbers); // set numbers to customer

        // Step 3: Same for addresses
        List<Address> addresses = dto.getAddresses().stream()
                .map(a -> Address.builder()
                        .addressLine1(a.getAddressLine1())
                        .addressLine2(a.getAddressLine2())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .customer(customer) // ✅ if you have customer field in Address entity
                        .build())
                .collect(Collectors.toList());

        customer.setAddress(addresses);

        // Step 4: Save the customer (this will save related entities too if Cascade is set)
        Customer savedCustomer = customerRepository.save(customer);

        // Step 5: Map to Response DTO
        return dtoConvertor.convertToResponseDto(savedCustomer);


    }


    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto dto) throws NullNotAllowedException, ValueNotExistException {

        if (id == null) {
            throw new NullNotAllowedException("Customer ID cannot be null");
        }

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ValueNotExistException("Customer not found with ID: " + id));

        // Update simple fields
        customer.setName(dto.getName());
        customer.setNic(dto.getNic());
        customer.setDateOfBirth(dto.getDateOfBirth());


        // Update family members
        if (dto.getFamilyMemberIds() != null) {
            List<Customer> familyMembers = customerRepository.findAllById(dto.getFamilyMemberIds());
            customer.setFamilyMembers(familyMembers);
        }

        // Handle Mobile Numbers
        List<MobileNumber> updatedMobileNumbers = dto.getMobileNumbers().stream().map(mDto -> {
            if (mDto.getId() != null) {
                // Update existing
                return mobileNumberRepository.findById(mDto.getId()).map(existing -> {
                    existing.setNumber(mDto.getNumber());
                    return existing;
                }).orElseThrow(() -> new RuntimeException("Mobile number not found: " + mDto.getId()));
            } else {
                // Add new
                return MobileNumber.builder()
                        .number(mDto.getNumber())
                        .customer(customer)
                        .build();
            }
        }).collect(Collectors.toList());

        // Handle Addresses
        List<Address> updatedAddresses = dto.getAddresses().stream().map(aDto -> {
            if (aDto.getId() != null) {
                // Update existing
                return addressRepository.findById(aDto.getId()).map(existing -> {
                    existing.setAddressLine1(aDto.getAddressLine1());
                    existing.setAddressLine2(aDto.getAddressLine2());
                    existing.setCity(aDto.getCity());
                    existing.setCountry(aDto.getCountry());
                    return existing;
                }).orElseThrow(() -> new RuntimeException("Address not found: " + aDto.getId()));
            } else {
                // Add new
                return Address.builder()
                        .addressLine1(aDto.getAddressLine1())
                        .addressLine2(aDto.getAddressLine2())
                        .city(aDto.getCity())
                        .country(aDto.getCountry())
                        .customer(customer)
                        .build();
            }
        }).collect(Collectors.toList());

        customer.setMobileNumbers(updatedMobileNumbers);
        customer.setAddress(updatedAddresses);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponseDto.builder()
                .id(savedCustomer.getId())
                .name(savedCustomer.getName())
                .nic(savedCustomer.getNic())
                .dateOfBirth(savedCustomer.getDateOfBirth())
                .mobileNumbers(dtoConvertor.mapToMobileNumberResponseDto(savedCustomer.getMobileNumbers()))
                .addresses(dtoConvertor.mapToAddressResponseDto(savedCustomer.getAddress()))
                .build();
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) throws NullNotAllowedException, ValueNotExistException {

        if (id == null) {
            throw new NullNotAllowedException("Customer ID cannot be null");
        }


        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ValueNotExistException("Customer not found with ID: " + id));

        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .nic(customer.getNic())
                .dateOfBirth(customer.getDateOfBirth())
                .mobileNumbers(dtoConvertor.mapToMobileNumberResponseDto(customer.getMobileNumbers()))
                .addresses(dtoConvertor.mapToAddressResponseDto(customer.getAddress()))
                .familyMembers(customer.getFamilyMembers().stream().map(f ->
                        FamilyMemberResponseDto.builder()
                                .id(f.getId())
                                .name(f.getName())
                                .nic(f.getNic())
                                .build()
                ).collect(Collectors.toList()))
                .build();    }

    @Override
    public PaginatedCustomerResponseDto getAllCustomers(Integer pageNumber, Integer pageSize) throws ApplicationException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        long totalCount = customerRepository.count();

        List<CustomerResponseDto> dtos = customerPage.map(dtoConvertor::convertToResponseDto).getContent();

        return new PaginatedCustomerResponseDto(
                dtos,
                totalCount,
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

    }


}

