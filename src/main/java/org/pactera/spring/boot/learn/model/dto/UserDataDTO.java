package org.pactera.spring.boot.learn.model.dto;

import lombok.Data;

/**
 * 用户DTO
 */
@Data
public class UserDataDTO {
    /** 主键ID */
    private Long id;
    /** 姓名 */
    private String name;
    /** 年龄 */
    private Integer age;
    /** 邮箱 */
    private String email;
    /** 头像 */
    private String avatar;
}
