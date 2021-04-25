package com.payment.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.payment.api.exception.EntityNotFoundException;
import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.model.product.OrderProduct;
import com.payment.api.service.OrderService;
import com.payment.api.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value="/api")
public class EditOrderController {

    /**
     * Service for OrderClient necessary for the
     * validation, creation and modification of orders
     */
    @Autowired
    private OrderService clientOrderService;

    /**
     * Service for Product necessary for the
     * validation, creation and modification of products or orderproducts
     */
    @Autowired
    private ProductService servicioProducto;
    
    /**
     * Function that deletes the client order and returns a new bill if 12 hours or more have passed
     * @param clientOrderId Long Client_Order_Id necessary to find the Order to edit.
     * @param updatedOrderProducts List<OrderProduct> List of new OrderProducts.
     * @return Edited ClientOrder or null if not found or 5 hours or more have passed since the order was created
     */
    @PutMapping(value="/edit-clientOrder/{clientOrderId}", produces = "application/json; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public ClientOrder editClientOrder(@PathVariable("clientOrderId") Long clientOrderId, @RequestBody List<OrderProduct> updatedOrderProducts) {
        ClientOrder clientOrder = clientOrderService.findClientOrderById(clientOrderId);
        if(clientOrder != null){
            long hours = ChronoUnit.HOURS.between(clientOrder.getGenerationDate(), LocalDateTime.now());
            if(hours<5 && clientOrder.getState().equals("Completed")){
                Long newCost = 0L;
                for (OrderProduct orderProduct : updatedOrderProducts) {
                    newCost += orderProduct.getCost();
                }
                if(newCost >= clientOrder.getTotalCost()){
                    clientOrderService.deleteOrderProducts(clientOrder.getId());
                    for (OrderProduct orderProduct : updatedOrderProducts) {
                        orderProduct.setId(null);
                        orderProduct.setClientOrder(clientOrder);
                        servicioProducto.createOrderProduct(orderProduct);
                    }
                    clientOrder.setOrderProducts(updatedOrderProducts);
                    if(newCost> 70000)
                        clientOrder.updateTotalCost(true);
                    else
                        clientOrder.updateTotalCost(false);
                    clientOrderService.updateClientOrder(clientOrder.getId(), clientOrder);
                    return clientOrder;
                }
                else{
                    return null;
                }
            }
            else
                return null;
        }
        throw new EntityNotFoundException("ClientOrder Not Found");
    }


}
