package com.CustomerRegi.model;

import com.CustomerRegi.enums.AddressType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    @NotEmpty(message = "address can not be null")
    private String address;

    @Column(name = "addresstype")
    @NotBlank(message = "Address type cannpot be null")
    private AddressType addresstype;

    @ManyToOne
    @JoinColumn(name = "cutomer_id")
    private Customer customer;

}