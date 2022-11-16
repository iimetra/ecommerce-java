package com.iimetra.order.ecommerce.repository;

import com.iimetra.order.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
