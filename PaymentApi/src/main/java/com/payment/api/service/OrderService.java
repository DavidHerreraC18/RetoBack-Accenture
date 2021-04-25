package com.payment.api.service;

import java.util.List;

import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.model.clientorder.ClientOrderRepository;
import com.payment.api.model.product.OrderProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    
    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Autowired
    private ProductService productService;

    public ClientOrder findClientOrderById(Long id){
        return clientOrderRepository.findById(id).get();
    }

    public List<ClientOrder> findAllClientOrders(){
        return clientOrderRepository.findAll();
    }

    public ClientOrder createClientOrder(ClientOrder newClientOrder){

        if(newClientOrder != null)
            return clientOrderRepository.save(newClientOrder);
        else
            return null;
    }

    public ClientOrder updateClientOrderCost(Long id, boolean withIva){
        ClientOrder found = this.findClientOrderById(id);

        if(found != null)
        {
            found.updateTotalCost(withIva);
            return clientOrderRepository.save(found);
        }
        return null;
    }

    public void updateClientOrder(Long id, ClientOrder updatedClientOrder){
        ClientOrder found = this.findClientOrderById(id);
        if(found != null){
            found.setDeliveryCost(updatedClientOrder.getDeliveryCost());
            found.setTotalCost(updatedClientOrder.getTotalCost());
            found.setState(updatedClientOrder.getState());
            clientOrderRepository.save(found);
        }
    }

    public void deleteOrderProducts(Long id){
        ClientOrder clientOrder = this.findClientOrderById(id);
        for(OrderProduct productoP: clientOrder.getOrderProducts())
        {   
            productService.deleteOrderProduct(productoP.getId());
        }
    }

    public void deleteClientOrder(Long id){
        ClientOrder clientOrder = this.findClientOrderById(id);
        for(OrderProduct productoP: clientOrder.getOrderProducts())
        {   
            productService.deleteOrderProduct(productoP.getId());
        }
        clientOrderRepository.delete(clientOrder);
    }
}
