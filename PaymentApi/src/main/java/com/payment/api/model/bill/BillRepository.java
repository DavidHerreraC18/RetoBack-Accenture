package com.payment.api.model.bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* Repository for Bill
*/
public interface BillRepository extends JpaRepository<Bill, Long> {
    /**
     * Find Bills by the Clients that own them usign the user_client_id.
     * @param userClientId Long Id of the UserClient.
     */
    List<Bill> findByUserClientId(Long userClientId);

    /**
     * Find the Bill by its OrderId 
     * @param orderId Long Id of the ClientOrder.
     */
    List<Bill> findByClientOrderId(Long orderId);
}
