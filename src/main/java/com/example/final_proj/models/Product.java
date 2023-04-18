package com.example.final_proj.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message="Наименование не может быть пустым")
    @Column(name="title", nullable = false, columnDefinition = "text", unique = true)
    private String title;
    @Column(name="description", nullable = false, columnDefinition = "text")
    @NotEmpty(message="Описание товара не может быть пустым")
    private String description;
    @Column(name="price", nullable = false)
    @Min(value=1, message="Цена не может быть меньше 1 рубля")
    private float price;
    @Column(name="warehouse", nullable = false)
    @NotEmpty(message = "Склад по нахождению товара не может быть пустым")
    private String warehouse;

    @Column(name="seller", nullable = false)
    @NotEmpty(message="Информация о продавце не может быть пустым")
    private String seller;
    @ManyToOne(optional = false)
    private Category category;

    private LocalDateTime dateTime;

    // Данный метод будет заполнять поле даты и времени при создании объекта класса
    @PrePersist
    private void init(){
        dateTime=LocalDateTime.now();
    }



}
