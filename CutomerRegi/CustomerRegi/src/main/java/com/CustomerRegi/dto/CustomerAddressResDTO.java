package com.CustomerRegi.dto;


import com.CustomerRegi.enums.AddressType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressResDTO {

    private String address;

    private AddressType addressType;

}
