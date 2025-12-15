package com.CustomerRegi.dto;

import com.CustomerRegi.enums.Gender;
import com.CustomerRegi.model.CustomerAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class CustomerReqDTO {

    @NotBlank(message = "First Name can not b null cannot be null")
    private String firstName;

    @NotBlank(message = "Last Name can not be null")
    private String lastName;

    @NotBlank(message = "Mobile Number can not be null")
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobilenumber;

    @NotBlank(message = "Dob can not be null")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;

    @NotBlank(message = "Gender can not be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotBlank(message = "message can not be null")
    private String password;

    @Email
    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotEmpty(message = "addresses can not be null")
    private List<CustomerAddress> addresses;
}
