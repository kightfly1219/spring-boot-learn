package org.pactera.spring.boot.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.pactera.spring.boot.learn.common.R;
import org.pactera.spring.boot.learn.exception.ServiceException;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;
import org.pactera.spring.boot.learn.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
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
    @Cacheable(value = "userCache", key = "#id")
    public R<List<UserDataVO>> getUserList(UserDataDTO requestDto) {
        List<UserDataVO> userList = userService.getUserList(requestDto);
        return R.ok(userList);
    }

    /**
     * 通过id获取用户详情
     * @param id 用户ID
     * @return 用户详情
     */
    @GetMapping("/user/{id}")
    @Cacheable(value = "userCache", key = "#id")
    public R<UserDataVO> getUserDetail(@PathVariable(name = "id") Long id) {
        log.info("用户详细情报取得API--- 用户ID:" + id);
        UserDataVO userDataVO = userService.getUserDetail(id);
        return R.ok(userDataVO);
    }

    /**
     * 新增用户，单条
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @PostMapping("/user")
    @CacheEvict(value = "userCache", key = "#id")
    public R<UserDataVO> insertUser(@RequestBody UserDataDTO requestDto) throws AccessDeniedException {
        if ("admin".equals(requestDto.getName())) {
            throw new ServiceException("insertUser 抛出的自定义异常");
        }
        if ("test".equals(requestDto.getName())) {
            throw new AccessDeniedException("insertUser 抛出的没有权限异常");
        }
        UserDataVO userDataVO = new UserDataVO();
        Boolean isSuccess = userService.insertUser(requestDto);
        log.info("用户情报新规API--- 结果:" + isSuccess);
        if (isSuccess) {
            BeanUtils.copyProperties(requestDto, userDataVO);
        }
        // 将用户缓存到内存中
        Long id = userDataVO.getId();
        return R.ok(userDataVO);
    }

    /**
     * 新增用户，多条
     * @param dtoList 用户DTOList
     * @return 用户VO
     */
    @PostMapping("/userList")
    @CacheEvict(value = "userCache", key = "#id")
    public R<List<UserDataVO>> insertUserList(@RequestBody List<UserDataDTO> dtoList) {
        List<UserDataVO> userList = new ArrayList<>();
        Boolean isSuccess = userService.insertUserList(dtoList);
        log.info("用户情报新规API--- 结果:" + isSuccess);
        if (isSuccess) {
            for (UserDataDTO item : dtoList) {
                UserDataVO userDataVO = new UserDataVO();
                userList.add(userDataVO);
                BeanUtils.copyProperties(item, userDataVO);
            }
        }
        return R.ok(userList);
    }

    /**
     * 更新用户，单条
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @PutMapping("/user")
    @CacheEvict(value = "userCache", key = "#id")
    public R<UserDataVO> updateUser(@RequestBody UserDataDTO requestDto) {
        UserDataVO userDataVO = new UserDataVO();
        Boolean isSuccess = userService.updateUser(requestDto);
        log.info("用户情报更新API--- 结果:" + isSuccess);
        if (isSuccess) {
            BeanUtils.copyProperties(requestDto, userDataVO);
        }
        return R.ok(userDataVO);
    }

    /**
     * 通过id删除用户
     * @param id 用户ID
     * @return true:成功 false:失败
     */
    @DeleteMapping("/user/{id}")
    @CacheEvict(value = "userCache", key = "#id")
    public R<Boolean> deleteUser(@PathVariable(name = "id") Long id) {
        log.info("用户情报删除API--- 用户ID:" + id);
        Boolean isSuccess = userService.deleteUser(id);
        return R.ok(isSuccess);
    }
}
