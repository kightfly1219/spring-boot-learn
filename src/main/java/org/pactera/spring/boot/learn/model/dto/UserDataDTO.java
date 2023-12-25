package org.pactera.spring.boot.learn.model.dto;

import lombok.Data;

@Data
public class UserDataDTO {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
