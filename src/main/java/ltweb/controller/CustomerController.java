package ltweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ltweb.entity.UserInfo;
import ltweb.model.Customer;
import ltweb.repository.UserInfoRepository;

@RestController
@EnableMethodSecurity
public class CustomerController {
	
	@Autowired UserInfoRepository userInfoRepository;
	@Autowired PasswordEncoder passwordEncoder;

    final private List<Customer> customers = List.of(
        Customer.builder().id("001").name("Nguyen Huu Trung").email("trung@gmail.com").build(),
        Customer.builder().id("002").name("Huu Trung").email("user@gmail.com").build()
    );

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Chỉ Admin mới được xem
    public ResponseEntity<List<Customer>> getCustomerList() {
        return ResponseEntity.ok(this.customers);
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')") // User có thể xem chi tiết
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") String id) {
        List<Customer> customers = this.customers.stream()
			.filter(c -> c.getId().equals(id))
			.toList();
        return ResponseEntity.ok(customers.get(0));
    }
    
    @PostMapping("/new-user")
    public String addUser(@RequestBody UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User added successfully";
    }
}
