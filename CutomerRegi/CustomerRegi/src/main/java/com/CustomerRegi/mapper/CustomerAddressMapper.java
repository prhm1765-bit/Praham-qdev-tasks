package com.CustomerRegi.mapper;

import com.CustomerRegi.dto.CustomerAddressReqDTO;
import com.CustomerRegi.dto.CustomerAddressResDTO;
import com.CustomerRegi.model.CustomerAddress;

public interface CustomerAddressMapper {

    CustomerAddress toEntity(CustomerAddressReqDTO dto);

    CustomerAddressResDTO toDTO(CustomerAddress entity);

}
