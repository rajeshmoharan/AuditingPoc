package org.rajesh.jpaspecification.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rajesh.jpaspecification.dto.OrderDto;
import org.rajesh.jpaspecification.dto.SearchFilter;
import org.rajesh.jpaspecification.entity.Customer;
import org.rajesh.jpaspecification.entity.Order;
import org.rajesh.jpaspecification.exception.ResourseNotFoundException;
import org.rajesh.jpaspecification.repo.CustomerRepository;
import org.rajesh.jpaspecification.repo.OrderRepository;
import org.rajesh.jpaspecification.service.FilterSearch;
import org.rajesh.jpaspecification.service.UserAuditorContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/filter")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class FilterController {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    @GetMapping("/search")
    public ResponseEntity<List<OrderDto>> search(@Valid @RequestBody SearchFilter searchFilter,
                                 @RequestParam (defaultValue = "1") Integer page,
                                 @RequestParam (defaultValue = "10") Integer size
                              ) {
        Pageable pageable = PageRequest.of(page-1,size);
        Specification<Order> spec = FilterSearch.getCustomerOrder(searchFilter);
        Page<Order> orders = orderRepository.findAll(spec,pageable);

        List<OrderDto> orderDto = orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        throw new ResourseNotFoundException("No such element found");
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<String > create(@RequestBody Customer customer,@PathVariable Integer id) {
        try{
            String string = UserAuditorContext.setCurrentUser(String.valueOf(id));
            customerRepository.save(customer);
            return ResponseEntity.ok("Success");
        }catch (Exception e){
            throw new ResourseNotFoundException("No such element found");
        }
    }

    @PatchMapping("/update/{id}/{name}")
    public ResponseEntity<String > update(@PathVariable Long id,@PathVariable String name) {
        UserAuditorContext.setCurrentUser(String.valueOf(name));
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("No such element found"));
        customer.setName(name);
        customerRepository.save(customer);
        return ResponseEntity.ok("Success");
    }
}
