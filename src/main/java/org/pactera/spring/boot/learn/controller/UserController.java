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

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/user")
    public List<UserDataVO> getUserList() {
        List<UserDataVO> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserDataVO item = new UserDataVO();
            item.setAge(i);
            item.setUsername("username-YB" + i);
            userList.add(item);
        }
        return userList;
    }

    @GetMapping("/user/{id}")
    public UserDataVO getUserDetail(@PathVariable(name = "id") String id) {
        log.info("用户详细情报取得API--- 用户ID:" + id);
        UserDataVO userDataVO = new UserDataVO();
        userDataVO.setAge(10);
        userDataVO.setUsername("username-YB" + 10);
        return userDataVO;
    }

    @PostMapping("/user")
    public UserDataVO insertUser(@RequestBody UserDataVO input) {
        UserDataVO output = new UserDataVO();
        UserDataDTO userDataDTO = new UserDataDTO();
        BeanUtils.copyProperties(input, userDataDTO);
        Boolean isSuccess = userService.insertUser(userDataDTO);
        log.info("用户情报新规API--- 结果:" + isSuccess);
        BeanUtils.copyProperties(input, output);
        return output;
    }

    @PutMapping("/user")
    public UserDataVO updateUser(@RequestBody UserDataVO input) {
        UserDataVO output = new UserDataVO();
        BeanUtils.copyProperties(input, output);
        return output;
    }

    @DeleteMapping("/user/{id}")
    public Boolean deleteUser(@PathVariable(name = "id") String id) {
        log.info("用户情报删除API--- 用户ID:" + id);
        return true;
    }
}
