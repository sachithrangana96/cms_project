package com.customer_management.customer_pt.controller;


import com.customer_management.customer_pt.dto.request.CustomerRequestDto;
import com.customer_management.customer_pt.dto.response.AppResponse;
import com.customer_management.customer_pt.dto.response.CustomerResponseDto;
import com.customer_management.customer_pt.dto.response.PaginatedCustomerResponseDto;
import com.customer_management.customer_pt.exception.ApplicationException;
import com.customer_management.customer_pt.exception.ApplicationGeneralException;
import com.customer_management.customer_pt.exception.NullNotAllowedException;
import com.customer_management.customer_pt.exception.ValueNotExistException;
import com.customer_management.customer_pt.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public AppResponse<CustomerResponseDto> createCustomer(
            @RequestBody CustomerRequestDto customer,HttpServletResponse httpServletResponse) throws ApplicationGeneralException {
        try {
            return AppResponse.created(customerService.createCustomer(customer));
        } catch (ApplicationException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return AppResponse.error(null, "Error 1", "Invalid Request", "CM-001", e.getMessage());
        }catch (Exception e) {
            throw new ApplicationGeneralException(e);
        }
    }

    @PutMapping("/{id}")
    public AppResponse<CustomerResponseDto> updateCustomer(
            @PathVariable Long id, @RequestBody CustomerRequestDto customer,HttpServletResponse httpServletResponse) {
        try {
            return AppResponse.ok(customerService.updateCustomer(id, customer));
        } catch (NullNotAllowedException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return AppResponse.error(null, "Error 1", "Invalid Request", "CM-001", e.getMessage());
        } catch (ValueNotExistException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return AppResponse.error(null, "Error 2", "Invalid Request", "CM-002", e.getMessage());
        }catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return AppResponse.error(null, "Error 3", "Invalid Request", "CM-003", e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public AppResponse<CustomerResponseDto> getCustomer(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        try {
            return AppResponse.ok(customerService.getCustomerById(id));
        } catch (NullNotAllowedException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return AppResponse.error(null, "Error 1", "Invalid Request", "CM-001", e.getMessage());
        } catch (ValueNotExistException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return AppResponse.error(null, "Error 2", "Invalid Request", "CM-002", e.getMessage());
        }catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return AppResponse.error(null, "Error 3", "Invalid Request", "CM-003", e.getMessage());
        }
    }

    @GetMapping
    public AppResponse<List<CustomerResponseDto>> getAllCustomers(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "" + Integer.MAX_VALUE) Integer pageSize
    ) throws ApplicationGeneralException {
        try {
            PaginatedCustomerResponseDto response = null;
            response = customerService.getAllCustomers(pageNumber, pageSize);
            return AppResponse.okList(response.getData(),response.getSize(),response.getPage(),response.getTotal());
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationGeneralException(e);
        }
    }
}
