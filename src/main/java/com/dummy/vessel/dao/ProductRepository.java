package com.dummy.vessel.dao;

import com.dummy.vessel.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("from Product as p where p.user.id =:userId")
    public List<Product> findProductByUser(@Param("userId")String userId);

}
