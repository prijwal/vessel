package com.dummy.vessel.constants;

import com.dummy.vessel.entities.Role;

public class enums {

    public enum RoleType {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");
        private final String roleName;

        RoleType(String roleName) {
            this.roleName = roleName;
        }

        public String getRoleName() {
            return roleName;
        }

        public Role toRole() {
            Role role = new Role();
            role.setRoleValue(this.getRoleName());
            return role;
        }
    }

}
