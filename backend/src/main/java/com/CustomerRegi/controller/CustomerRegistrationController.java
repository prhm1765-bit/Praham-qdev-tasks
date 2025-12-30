package com.CustomerRegi.controller;

import com.CustomerRegi.dto.CustomerReqDTO;
import com.CustomerRegi.dto.CustomerResDTO;
import com.CustomerRegi.service.CustomerRegistrationService;
import com.CustomerRegi.validation.OnCreate;
import com.CustomerRegi.validation.OnUpdate;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer-registration")
public class CustomerRegistrationController {

	private final CustomerRegistrationService customerRegistrationService;

	@PostMapping
	public ResponseEntity<CustomerResDTO> save(@Validated(OnCreate.class) @RequestBody CustomerReqDTO customerReqDTO) {
		return  ResponseEntity.ok(customerRegistrationService.saveOrUpdate(customerReqDTO));
	}

	@GetMapping
	public ResponseEntity<List<CustomerResDTO>> getAll() {
		return ResponseEntity.ok(customerRegistrationService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerResDTO> getById(@PathVariable int id) {
		return ResponseEntity.ok(customerRegistrationService.getById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
		customerRegistrationService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerResDTO> edit(
			@PathVariable Integer id,
			@Validated(OnUpdate.class) @RequestBody CustomerReqDTO dto) {
		dto.setId(id);
		return ResponseEntity.ok(customerRegistrationService.saveOrUpdate(dto));
	}

}
