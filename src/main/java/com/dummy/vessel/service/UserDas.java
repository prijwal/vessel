package com.dummy.vessel.service;

import com.dummy.vessel.entities.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDas {
    void save(User user);

    User getUserByUserName(String userName);

    List<User> findAllUsers();

    void removeUser(Long userId);

    User getUserById(@Param("id") Long id);
}
