package org.pactera.spring.boot.learn.mapper;

import org.apache.ibatis.annotations.Param;
import org.pactera.spring.boot.learn.entity.UserEntity;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;

import java.util.List;

/**
 * 用户映射器
 */
public interface UserMapper {

    /**
     * 获取用户列表
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    List<UserDataVO> getUserList(@Param("req") UserDataDTO requestDto);

    /**
     * 通过id获取用户详情
     *
     * @param id 用户ID
     * @return 用户详情
     */
    UserEntity getUserDetail(@Param("id") Long id);

    /**
     * 新增用户，单条
     * @param userEntity 用户Entity
     * @return 插入成功条数
     */
    int insertUser(@Param("req") UserEntity userEntity);

    /**
     * 更新用户，单条
     *
     * @param userEntity 用户Entity
     * @return 更新成功条数
     */
    int updateUser(@Param("req") UserEntity userEntity);

    /**
     * 新增用户，多条
     *
     * @param entityList 用户EntityList
     * @return 插入成功条数
     */
    int insertUserList(@Param("entityList") List<UserEntity> entityList);

    /**
     * 通过id删除用户
     * @param id 用户ID
     * @return 删除成功条数
     */
    int deleteUser(@Param("id") Long id);
}
