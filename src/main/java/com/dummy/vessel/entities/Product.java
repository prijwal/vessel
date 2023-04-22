package com.dummy.vessel.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "APP_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cId;
    private String name;
    private String category;
    private String price;
    private String imageUrl;
    private String rating;

    @ManyToOne
    private User user;
}
