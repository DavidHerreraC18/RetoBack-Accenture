package com.payment.api.model.clientorder;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* Repository for ClientOrder
*/
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    
}
