package com.payment.api.model.bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUserClientId(Long clienteId);
    List<Bill> findByClientOrderId(Long pedidoId);
}
