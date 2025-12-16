package com.CustomerRegi.dto;

import com.CustomerRegi.enums.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressReqDTO {

    @NotEmpty(message = "address can not be null")
    private String address;

    @NotNull(message = "Address type cannpot be null")
    private AddressType addresstype;

}
