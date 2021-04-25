package com.payment.api.model.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* Repository for Product
*/
public interface ProductRepository extends JpaRepository<Product, Long> {
}
