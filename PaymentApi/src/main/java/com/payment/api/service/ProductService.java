package com.payment.api.service;

import java.util.List;

import com.payment.api.model.product.Product;
import com.payment.api.model.product.OrderProduct;
import com.payment.api.model.product.ProductRepository;
import com.payment.api.model.product.OrderProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public Product findProductById(Long id){
        return productRepository.findById(id).get();
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(Product newProduct){
        if(newProduct != null)
            return productRepository.save(newProduct);
        else
            return null;
    }

    public void updateProduct(Long id, Product updatedProduct){
        Product found = this.findProductById(id);
        if(found != null){
            found.setName(updatedProduct.getName());
            found.setPrice(updatedProduct.getPrice());
            productRepository.save(found);
        }
    }

    public void deleteProduct(Long id){
        Product product = this.findProductById(id);
        productRepository.delete(product);
    }


    public OrderProduct findOrderProductById(Long id){
        return orderProductRepository.findById(id).get();
    }

    public List<OrderProduct> findAllOrderProducts(){
        return orderProductRepository.findAll();
    }

    public OrderProduct createOrderProduct(OrderProduct newOrderProduct){
        if(newOrderProduct != null)
            return orderProductRepository.save(newOrderProduct);
        else
            return null;
    }

    public OrderProduct increaseOrderProduct(OrderProduct orderProduct){
        OrderProduct found = this.findOrderProductById(orderProduct.getId());
        if(found != null)
        {
            found.setQuantity(found.getQuantity()+1);
            found.updateCost();
            return orderProductRepository.save(found);
        }
        else
        {
            orderProduct.setId(null);
            orderProduct.setQuantity(Long.valueOf(1));
            orderProduct.updateCost();
            orderProductRepository.save(orderProduct);
        }   
        return found;
    }

    public OrderProduct decreaseOrderProduct(Long id){
        OrderProduct found = this.findOrderProductById(id);
        if(found != null)
        {
            found.setQuantity(found.getQuantity()-1);
            if(found.getQuantity()!= 0)
            {
                found.updateCost();
                return orderProductRepository.save(found);
            }
            else{
                orderProductRepository.delete(found);
            }
        }
        return null;
    }

    public void actualizarProductoPedido(Long id, OrderProduct updatedOrderProduct){
        OrderProduct found = this.findOrderProductById(id);
        if(found != null){
            found.setQuantity(updatedOrderProduct.getQuantity());
            found.setCost(updatedOrderProduct.getCost());
        }
    }

    public void deleteOrderProduct(Long id){
        OrderProduct orderProduct = this.findOrderProductById(id);
        orderProductRepository.delete(orderProduct);
    }
}
