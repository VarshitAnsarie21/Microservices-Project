package com.microservice.order.service;

import com.microservice.order.client.UserServiceClient;
import com.microservice.order.dto.UserDTO;
import com.microservice.order.model.Order;
import com.microservice.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserServiceClient userServiceClient;

    public OrderService(OrderRepository orderRepository, UserServiceClient userServiceClient){
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
    }

    public Order placeOrder(Order order) {
        UserDTO userDTO = userServiceClient.getUserById(order.getOrderId());
        Order newOrder = orderRepository.findById(order.getOrderId()).orElse(new Order());
        if(newOrder.getOrderId() != null) {
            throw new RuntimeException("Order already exists");
        }
        newOrder.setUserId(userDTO.getUserId());
        newOrder.setOrderAmount(order.getOrderAmount());
        newOrder.setQuantity(order.getQuantity());
        newOrder.setOrderDate(LocalDateTime.now());

        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

