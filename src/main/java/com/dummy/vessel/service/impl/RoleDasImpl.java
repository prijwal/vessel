package com.dummy.vessel.service.impl;

import com.dummy.vessel.entities.Role;
import com.dummy.vessel.repository.RoleDao;
import com.dummy.vessel.service.roleDas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RoleDasImpl implements roleDas {

    @Autowired
    RoleDao roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Collection<Role> getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }
}
