package com.dummy.vessel.das;

import com.dummy.vessel.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class DummyUserDetailsImpl implements UserDetailsService {
    @Autowired
    UserDasImpl userDasImpl;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("####### loadUserByUsername function");

        User user = userDasImpl.getUserByUserName(username); // fetch user by database

        if(user == null){
            throw new UsernameNotFoundException("could not found user");
        }

        System.out.println("####### user details from userDetailServiceImpl" + user);
        System.out.println("####### user encoded password" + user.getPassword());
        DummyUserDetails dummyUserDetails = new DummyUserDetails(user);
        return dummyUserDetails;

    }
}