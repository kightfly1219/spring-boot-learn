package org.pactera.spring.boot.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;
import org.pactera.spring.boot.learn.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    /**
     * 用户服务
     */
    @Resource
    private IUserService userService;

    /**
     * 获取用户列表
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @GetMapping("/user")
    public List<UserDataVO> getUserList(UserDataDTO requestDto) {
        return userService.getUserList(requestDto);
    }

    /**
     * 通过id获取用户详情
     * @param id 用户ID
     * @return 用户详情
     */
    @GetMapping("/user/{id}")
    public UserDataVO getUserDetail(@PathVariable(name = "id") Long id) {
        log.info("用户详细情报取得API--- 用户ID:" + id);
        return userService.getUserDetail(id);
    }

    /**
     * 新增用户，单条
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @PostMapping("/user")
    public UserDataVO insertUser(@RequestBody UserDataDTO requestDto) {
        UserDataVO output = new UserDataVO();
        Boolean isSuccess = userService.insertUser(requestDto);
        log.info("用户情报新规API--- 结果:" + isSuccess);
        if (isSuccess) {
            BeanUtils.copyProperties(requestDto, output);
        }
        return output;
    }

    /**
     * 新增用户，多条
     * @param dtoList 用户DTOList
     * @return 用户VO
     */
    @PostMapping("/userList")
    public List<UserDataVO> insertUserList(@RequestBody List<UserDataDTO> dtoList) {
        List<UserDataVO> output = new ArrayList<>();
        Boolean isSuccess = userService.insertUserList(dtoList);
        log.info("用户情报新规API--- 结果:" + isSuccess);
        if (isSuccess) {
            for (UserDataDTO item : dtoList) {
                UserDataVO userDataVO = new UserDataVO();
                output.add(userDataVO);
                BeanUtils.copyProperties(item, userDataVO);
            }
        }
        return output;
    }

    /**
     * 更新用户，单条
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @PutMapping("/user")
    public UserDataVO updateUser(@RequestBody UserDataDTO requestDto) {
        UserDataVO output = new UserDataVO();
        Boolean isSuccess = userService.updateUser(requestDto);
        log.info("用户情报更新API--- 结果:" + isSuccess);
        if (isSuccess) {
            BeanUtils.copyProperties(requestDto, output);
        }
        return output;
    }

    /**
     * 通过id删除用户
     * @param id 用户ID
     * @return true:成功 false:失败
     */
    @DeleteMapping("/user/{id}")
    public Boolean deleteUser(@PathVariable(name = "id") Long id) {
        log.info("用户情报删除API--- 用户ID:" + id);
        return userService.deleteUser(id);
    }
}
