package com.dummy.vessel.repository;

import com.dummy.vessel.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    User getUserByUserName(@Param("email") String email);

    User getUserById(@Param("id") Long id);
}
