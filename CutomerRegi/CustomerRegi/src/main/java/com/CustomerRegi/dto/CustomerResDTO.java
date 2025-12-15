package com.CustomerRegi.dto;

import com.CustomerRegi.model.CustomerAddress;

import java.time.LocalDate;
import java.util.List;

public class CustomerResDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String mobilenumber;

    private LocalDate dob;

    private String email;

    private List<CustomerAddress> addresses;

}
