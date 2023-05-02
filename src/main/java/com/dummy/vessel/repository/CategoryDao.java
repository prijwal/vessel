package com.dummy.vessel.repository;

import com.dummy.vessel.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {


}
