package com.payment.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.payment.api.exception.EntityNotFoundException;
import com.payment.api.model.bill.Bill;
import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.service.BillService;
import com.payment.api.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value="/api")
public class DeleteOrderController {

    @Autowired
    private OrderService clientOrderService;

    @Autowired
    private BillService billService;
    
    @DeleteMapping(value="/delete-clientOrder/{clientOrderId}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public Bill deleteClientOrder(@PathVariable("clientOrderId") Long clientOrderId) {
        ClientOrder clientOrder = clientOrderService.findClientOrderById(clientOrderId);
        if(clientOrder != null && clientOrder.getState().equals("Completed")){
            long hours = ChronoUnit.HOURS.between(clientOrder.getGenerationDate(), LocalDateTime.now());
            Bill bill = billService.findBillByClientOrderId(clientOrder.getId());
            if(bill != null)
            {
                if(hours<12){
                    billService.deleteBill(bill.getId());
                    clientOrderService.deleteClientOrder(clientOrder.getId());
                }
                else{
                    clientOrder.setState("Cancelled");
                    long newCost = (long) (clientOrder.getTotalCost()*0.1);
                    bill.setTotalCost(newCost);
                    bill.setClientOrder(clientOrder);
                    clientOrderService.updateClientOrder(clientOrder.getId(), clientOrder);
                    billService.updateBill(bill.getId(), bill);
                    return bill;
                }
                return bill;
            }
            throw new EntityNotFoundException("Bill Not Found");              
        }
        throw new EntityNotFoundException("User Client Not Found");
    }
}
