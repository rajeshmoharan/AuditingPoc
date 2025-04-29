package org.rajesh.jpaspecification.dto;

import lombok.*;
import org.rajesh.jpaspecification.annotation.OrderCustomerAnnotaion;

@Data @AllArgsConstructor @NoArgsConstructor
public class SearchFilter {

//    @OrderCustomerAnnotaion
    private Long customerId;
}
