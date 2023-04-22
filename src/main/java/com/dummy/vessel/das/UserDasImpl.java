package com.dummy.vessel.das;

import com.dummy.vessel.dao.UserRepository;
import com.dummy.vessel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserDasImpl implements UserDas{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user){
        userRepository.save(user);
    }
    @Override
    public User getUserByUserName(String userName){
        return userRepository.getUserByUserName(userName);
    }
}
