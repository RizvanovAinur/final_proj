package com.example.final_proj.repository;

import com.example.final_proj.models.Order;
import com.example.final_proj.models.Person;
import com.example.final_proj.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);

    @Modifying
    @Query(value = "update orders set status = ?1 where id = ?2", nativeQuery = true)
    void updateIdStatus(int status,  int id);

    @Query(value = "select * from orders where ((lower(number) LIKE %?1%) or (lower(number) LIKE '?1%'))", nativeQuery = true)
    List<Order> findByName(String number);

}
