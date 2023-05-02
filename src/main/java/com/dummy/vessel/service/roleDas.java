package com.dummy.vessel.service;

import com.dummy.vessel.entities.Role;

import java.util.Collection;
import java.util.List;

public interface roleDas {


    List<Role> getAllRoles();

    Collection<Role> getRoleById(Long id);
}
