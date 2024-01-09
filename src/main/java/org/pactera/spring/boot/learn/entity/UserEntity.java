package org.pactera.spring.boot.learn.entity;

import lombok.Data;

/**
 * 用户Entity
 */
@Data
public class UserEntity {
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
