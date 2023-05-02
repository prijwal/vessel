package com.dummy.vessel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

    private Long id;
    private String name;

    private int categoryId;
    private double price;
    private String description;

}
