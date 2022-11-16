package com.iimetra.order.ecommerce.repository;

import com.iimetra.order.ecommerce.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
