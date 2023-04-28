package com.example.final_proj.services;

import com.example.final_proj.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Transactional
    public void updateStatus(int idStatus, int idOrder) {
        System.out.println(idStatus + " ++++++++ " +idOrder + "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        orderRepository.updateIdStatus(idStatus, idOrder);

    };
}
