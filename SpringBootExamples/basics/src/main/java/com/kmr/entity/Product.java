package com.kmr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private String category;

    private Double price;
}