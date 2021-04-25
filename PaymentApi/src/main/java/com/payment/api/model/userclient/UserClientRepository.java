package com.payment.api.model.userclient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClientRepository extends JpaRepository<UserClient, Long> {
    
}
