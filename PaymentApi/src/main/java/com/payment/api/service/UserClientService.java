package com.payment.api.service;

import java.util.List;

import com.payment.api.model.clientorder.ClientOrder;
import com.payment.api.model.userclient.UserClient;
import com.payment.api.model.userclient.UserClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserClientService {
    
    @Autowired
    private UserClientRepository userClientRepository;

    @Autowired
    private OrderService clientOrderService;

    public UserClient findUserClientById(Long id){
        return userClientRepository.findById(id).get();
    }

    public List<UserClient> findAllUserClients(){
        return userClientRepository.findAll();
    }

    public UserClient createUserClient(UserClient newUserClient){
        UserClient found = this.findUserClientById(newUserClient.getId());

        if(found == null)
            return userClientRepository.save(newUserClient);
        else
            return null;
    }

    public void createActualClientOrder(Long id){
        UserClient found = this.findUserClientById(id);
        if(found != null){
            ClientOrder clientOrder = new ClientOrder();
            clientOrder.setId(null);
            clientOrder.setUserClient(found);
            clientOrder.setState("In-Process");
            clientOrder.setDeliveryCost(Long.valueOf(5000));
            clientOrderService.createClientOrder(clientOrder);
            found.setActualClientOrder(clientOrder);
            userClientRepository.save(found);
        }
    }

    public void updateUserClient(Long id, UserClient updatedUserClient){
        UserClient found = this.findUserClientById(id);
        if(found != null){
            found.setDocument(updatedUserClient.getDocument());
            found.setAddress(updatedUserClient.getAddress());
            found.setDocumentType(updatedUserClient.getDocumentType());
            found.setActualClientOrder(updatedUserClient.getActualClientOrder());
            userClientRepository.save(found);
        }
    }

    public void deleteClient(Long id){
        UserClient userClient = this.findUserClientById(id);
        userClientRepository.delete(userClient);
    }
}
