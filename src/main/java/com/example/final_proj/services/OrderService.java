package com.example.final_proj.services;

import com.example.final_proj.models.Order;
import com.example.final_proj.models.Product;
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
        //orderRepository.updateIdStatus(idStatus, idOrder);

    }

    @Transactional
    public void updateOrder(int id, Order order){

        System.out.println(order.getId()+"===========================================================");
        System.out.println(order.getStatus()+"===========================================================");
        System.out.println(order.getPrice()+"===========================================================");
        System.out.println(order.getCount()+"===========================================================");
        System.out.println(order.getProduct()+"===========================================================");
        System.out.println(order.getDateTime()+"===========================================================");
        System.out.println(order.getPerson()+"===========================================================");
        System.out.println(order.getNumber()+"===========================================================");

        //order.setId(id);
        //orderRepository.save(order);
    }
}
