package com.iimetra.order.ecommerce.repository;

import com.iimetra.order.ecommerce.model.OrderItem;
import com.iimetra.order.ecommerce.model.OrderItemPk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemPk> {
}
