package com.CustomerRegi.service;

import com.CustomerRegi.dto.CustomerAddressReqDTO;
import com.CustomerRegi.dto.CustomerReqDTO;
import com.CustomerRegi.dto.CustomerResDTO;
import com.CustomerRegi.mapper.CustomerAddressMapper;
import com.CustomerRegi.mapper.CustomerMapper;
import com.CustomerRegi.model.Customer;
import com.CustomerRegi.model.CustomerAddress;
import com.CustomerRegi.repository.CustomerAddressRepo;
import com.CustomerRegi.repository.CustomerRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {

    private final CustomerAddressRepo customerAddressRepo;
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final CustomerAddressMapper customerAddressMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomerResDTO save(CustomerReqDTO customerReqDTO) {
        Customer customer = customerMapper.toEntity(customerReqDTO);
        customer.setPassword(bCryptPasswordEncoder.encode(customerReqDTO.getPassword()));
        List<CustomerAddress> addressList = new ArrayList<>();

        for (CustomerAddressReqDTO addressDTO : customerReqDTO.getAddress()) {
            CustomerAddress address = customerAddressMapper.toEntity(addressDTO);
            address.setCustomer(customer);
            addressList.add(address);
        }
        customer.setAddress(addressList);
        Customer savedCustomer = customerRepo.save(customer);

        return customerMapper.toDTO(savedCustomer);
    }

    @Override
    public List<CustomerResDTO> findAll() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResDTO> customerResDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerResDTOS.add(customerMapper.toDTO(customer));
        }
        return customerResDTOS;
    }

    @Override
    public CustomerResDTO getById(int id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerResDTO edit(int id, CustomerReqDTO customerReqDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setFirstName(customerReqDTO.getFirstName());
        customer.setLastName(customerReqDTO.getLastName());
        customer.setMobilenumber(customerReqDTO.getMobilenumber());
        customer.setDob(customerReqDTO.getDob());
        customer.setGender(customerReqDTO.getGender());
        customer.setEmail(customerReqDTO.getEmail());
        if (customerReqDTO.getPassword() != null && !customerReqDTO.getPassword().isEmpty()) {
            customer.setPassword(bCryptPasswordEncoder.encode(customerReqDTO.getPassword()));
        }
        customer.getAddress().clear();

        for (CustomerAddressReqDTO adressDTO : customerReqDTO.getAddress()) {
            CustomerAddress address = customerAddressMapper.toEntity(adressDTO);
            address.setCustomer(customer);
            customer.getAddress().add(address);
        }
        return customerMapper.toDTO( customerRepo.save(customer));
    }

    @Override
    public void delete(int id) {
        customerRepo.deleteById(id);
    }
}
