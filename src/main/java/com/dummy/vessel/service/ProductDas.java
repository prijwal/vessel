package com.dummy.vessel.service;

import com.dummy.vessel.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDas {

    List<Product> getAllProduct();

    void addProduct(Product product);

    void removeProduct(Long id);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProductByCategoryId(int id);
}
