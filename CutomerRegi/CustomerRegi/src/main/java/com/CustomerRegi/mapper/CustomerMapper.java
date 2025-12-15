package com.CustomerRegi.mapper;

import com.CustomerRegi.dto.CustomerReqDTO;
import com.CustomerRegi.dto.CustomerResDTO;
import com.CustomerRegi.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    Customer toEtity(CustomerReqDTO dto);

    CustomerResDTO toDTO(Customer customer);

}
