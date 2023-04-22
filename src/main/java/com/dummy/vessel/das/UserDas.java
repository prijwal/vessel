package com.dummy.vessel.das;

import com.dummy.vessel.entities.User;

public interface UserDas {
    void save(User user);

    User getUserByUserName(String userName);
}
