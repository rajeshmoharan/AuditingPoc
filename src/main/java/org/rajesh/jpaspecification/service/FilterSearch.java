package org.rajesh.jpaspecification.service;


import org.rajesh.jpaspecification.dto.SearchFilter;
import org.rajesh.jpaspecification.entity.Order;
import org.springframework.data.jpa.domain.Specification;


public class FilterSearch {
    public static Specification<Order> getCustomerOrder(SearchFilter searchFilter) {
        return (root, query, cb) -> cb.equal(root.get("customer").get("id"), searchFilter.getCustomerId());
    }
}
