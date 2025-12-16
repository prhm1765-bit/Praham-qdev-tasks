package com.CustomerRegi.service;

import com.CustomerRegi.dto.CustomerReqDTO;
import com.CustomerRegi.dto.CustomerResDTO;

import java.util.List;

public interface CustomerRegistrationService {

    CustomerResDTO save(CustomerReqDTO customerReqDTO);

    List<CustomerResDTO> findAll();

    CustomerResDTO getById(int id);

    CustomerResDTO edit(int id, CustomerReqDTO customerReqDTO);

    void delete(int id);
}
