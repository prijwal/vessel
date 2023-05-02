package com.dummy.vessel.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private Long roleId;
    boolean enabled;
}
