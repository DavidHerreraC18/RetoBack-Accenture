package com.payment.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import com.payment.api.exception.EntityNotFoundException;
import com.payment.api.model.bill.Bill;
import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.model.userclient.UserClient;
import com.payment.api.service.UserClientService;
import com.payment.api.service.BillService;
import com.payment.api.service.OrderService;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping(value="/api")
public class CreateOrderController {

    @Autowired
    private UserClientService clientService;

    @Autowired
    private OrderService clientOrderService;

    @Autowired
    private BillService billService;


    @PostMapping(value="/create-clientOrder/{id}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill createClientOrder(@PathVariable("id") Long userId) {
        UserClient userClient = clientService.findUserClientById(userId);
        if(userClient != null)
        {
            ClientOrder clientOrder = userClient.getActualClientOrder();
            if(clientOrder != null)
            {
                if(70000 >= clientOrder.getTotalCost() && clientOrder.getTotalCost() < 100000){
                    clientOrder.updateTotalCost(true);
                }
                else if(clientOrder.getTotalCost() < 70000){
                    clientOrder.updateTotalCost(false);
                }
                else if(clientOrder.getTotalCost() >= 100000){
                    clientOrder.updateTotalCost(true);
                }
                clientOrderService.updateClientOrder(clientOrder.getId(), clientOrder);
                Bill bill = new Bill();
                bill.setUserClient(userClient);
                bill.setTotalCost(clientOrder.getTotalCost());
                bill.setGenerationDate(LocalDateTime.now());
                bill.setId(null);
                bill.setClientOrder(clientOrder);
                userClient.setActualClientOrder(null);
                clientService.updateUserClient(userClient.getId(), userClient);
                return billService.createBill(bill);
            }
            else
                return null;          
        }
        throw new EntityNotFoundException("User Client Not Found");

    }
    
    
}
