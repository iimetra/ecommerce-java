package com.iimetra.order.ecommerce.repository;

import com.iimetra.order.ecommerce.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {
}
