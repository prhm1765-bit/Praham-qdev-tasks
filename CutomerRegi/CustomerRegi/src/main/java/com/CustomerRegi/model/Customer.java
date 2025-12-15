package com.CustomerRegi.model;

import com.CustomerRegi.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    @NotBlank(message = "First Name can not b null cannot be null")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "Last Name can not be null")
    private String lastName;

    @Column(name = "mobilenumber")
    @NotBlank(message = "Mobile Number can not be null")
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobilenumber;

    @Column(name = "dob")
    @NotBlank(message = "Dob can not be null")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @Column(name = "gender")
    @NotBlank(message = "Gender can not be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "password")
    @NotBlank(message = "message can not be null")
    private String password;

    @Column(name = "email")
    @Email
    @NotBlank(message = "Email cannot be null")
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<CustomerAddress> address;

}
