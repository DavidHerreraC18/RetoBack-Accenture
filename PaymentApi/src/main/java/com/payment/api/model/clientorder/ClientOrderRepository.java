package com.payment.api.model.clientorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {
    
}
