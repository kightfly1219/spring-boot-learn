package org.pactera.spring.boot.learn.service;

import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;

import java.util.List;

/**
 * 用户服务接口
 */
public interface IUserService {
    /**
     * 获取用户列表
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    List<UserDataVO> getUserList(UserDataDTO requestDto);

    /**
     * 通过id获取用户详情
     * @param id 用户ID
     * @return 用户详情
     */
    UserDataVO getUserDetail(Long id);

    /**
     * 新增用户，单条
     * @param requestDto 用户DTO
     * @return true:成功 false:失败
     */
    Boolean insertUser(UserDataDTO requestDto);

    /**
     * 新增用户，多条
     * @param dtoList 用户DTOList
     * @return true:成功 false:失败
     */
    Boolean insertUserList(List<UserDataDTO> dtoList);

    /**
     * 更新用户，单条
     * @param requestDto 用户DTO
     * @return true:成功 false:失败
     */
    Boolean updateUser(UserDataDTO requestDto);

    /**
     * 通过id删除用户
     * @param id 用户ID
     * @return true:成功 false:失败
     */
    Boolean deleteUser(Long id);
}
