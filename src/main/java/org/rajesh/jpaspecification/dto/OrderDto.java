package org.rajesh.jpaspecification.dto;

import lombok.*;
import org.rajesh.jpaspecification.annotation.OrderCustomerAnnotaion;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDto {
    private Long id;

//    @OrderCustomerAnnotaion
    private Long customerId;
    private Long productId;
    private Date orderDate;
    private Double totalAmount;
}
