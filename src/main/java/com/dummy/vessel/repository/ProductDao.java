package com.dummy.vessel.repository;

import com.dummy.vessel.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
//    @Query("from Product as p where p.user.id =:userId")
//    public List<Product> findProductByUser(@Param("userId")String userId);

    public List<Product> findAllByCategory_Id(int id);


}
