package com.dummy.vessel.service.impl;

import com.dummy.vessel.repository.UserDao;
import com.dummy.vessel.entities.User;
import com.dummy.vessel.service.UserDas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDasImpl implements UserDas {
    @Autowired
    private UserDao userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(@Param("id") Long id) {
        return userRepository.getUserById(id);
    }

}
