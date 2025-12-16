package com.CustomerRegi.controller;

import com.CustomerRegi.dto.CustomerReqDTO;
import com.CustomerRegi.dto.CustomerResDTO;
import com.CustomerRegi.service.CustomerRegistrationService;
import com.CustomerRegi.service.CustomerRegistrationServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer-registration")
public class CustomerRegistrationController {

    private final CustomerRegistrationService customerRegistrationService;

    @PostMapping
    public ResponseEntity<CustomerResDTO> save(@Valid @RequestBody CustomerReqDTO customerReqDTO) {
        return  ResponseEntity.ok(customerRegistrationService.save(customerReqDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResDTO>> getAll(){
        return ResponseEntity.ok(customerRegistrationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResDTO> getById(@PathVariable int id){
        return ResponseEntity.ok(customerRegistrationService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        customerRegistrationService.delete(id);
        return ResponseEntity.ok("Customer Deleted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResDTO> edit(@PathVariable int id, @Valid @RequestBody CustomerReqDTO customerReqDTO){
        return ResponseEntity.ok(customerRegistrationService.edit(id,customerReqDTO));
    }
}
