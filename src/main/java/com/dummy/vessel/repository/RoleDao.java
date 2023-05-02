package com.dummy.vessel.repository;

import com.dummy.vessel.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
    Collection<Role> getRoleById(Long id);
}
