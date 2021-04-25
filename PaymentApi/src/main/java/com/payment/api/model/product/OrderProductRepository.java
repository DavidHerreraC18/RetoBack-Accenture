package com.payment.api.model.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* Repository for OrderProduct
*/
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    
}
