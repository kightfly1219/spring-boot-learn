package org.pactera.spring.boot.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.pactera.spring.boot.learn.common.MinioTemplate;
import org.pactera.spring.boot.learn.common.R;
import org.pactera.spring.boot.learn.exception.ServiceException;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;
import org.pactera.spring.boot.learn.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    private final IUserService userService;

    /**
     * MinioTemplate
     */
    private final MinioTemplate minioTemplate;

    public UserController(IUserService userService, MinioTemplate minioTemplate) {
        this.userService = userService;
        this.minioTemplate = minioTemplate;
    }

    /**
     * 获取用户列表
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @GetMapping("/user")
    @Cacheable(value = "userCache")
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
        return R.ok(userDataVO);
    }

    /**
     * 新增用户，多条
     * @param dtoList 用户DTOList
     * @return 用户VO
     */
    @PostMapping("/userList")
    @CacheEvict("userCache")
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
    @CacheEvict(value = "userCache", key = "#requestDto.id")
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

    @PostMapping("upload")
    public R<Boolean> upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String uploadDir = "/Users/eric/SpringBoot_训练营/upload/20240115";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                Path filePath = Path.of(uploadDir, file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File uploaded: " + file.getOriginalFilename());
                return R.ok(true);
            } catch (IOException e) {
                e.printStackTrace();
                return R.error(e);
            }
        } else {
            return R.error();
        }
    }

    /**
     * 长传头像
     *
     * @return {@link String} 图片链接
     */
    @PostMapping("uploadAvatar")
    public R<String> uploadAvatar(@RequestParam("file") MultipartFile file) throws Exception {
        return R.ok(minioTemplate.putObject(file.getInputStream(), file.getContentType()));
    }
}
