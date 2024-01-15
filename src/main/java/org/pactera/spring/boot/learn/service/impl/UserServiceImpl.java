package org.pactera.spring.boot.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.pactera.spring.boot.learn.entity.UserEntity;
import org.pactera.spring.boot.learn.mapper.UserMapper;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.model.vo.UserDataVO;
import org.pactera.spring.boot.learn.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务实现
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户列表
     *
     * @param requestDto 用户DTO
     * @return 用户VO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserDataVO> getUserList(UserDataDTO requestDto) {
        return userMapper.getUserList(requestDto);
    }

    /**
     * 通过id获取用户详情
     *
     * @param id 用户ID
     * @return 用户详情
     */
    @Override
    @Transactional(rollbackFor = Exception.class
            , isolation = Isolation.READ_COMMITTED)
    public UserDataVO getUserDetail(Long id) {
        UserDataVO vo = new UserDataVO();
        UserEntity userEntity = userMapper.getUserDetail(id);
        BeanUtils.copyProperties(userEntity, vo);
        return vo;
    }

    /**
     * 新增用户，单条
     *
     * @param requestDto 用户DTO
     * @return true:成功 false:失败
     *
     * Spring事务传播类型
     * propagation = Propagation.REQUIRED
     *
     * 理解事务的4种隔离级别
     * isolation = Isolation.DEFAULT
     */
    @Override
    @Transactional(rollbackFor = Exception.class
            , isolation = Isolation.DEFAULT
            , propagation = Propagation.REQUIRED)
    public Boolean insertUser(UserDataDTO requestDto) {
        if (requestDto == null) {
            log.info("userDataDTO : null");
            return false;
        }
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(requestDto, userEntity);
        boolean success = userMapper.insertUser(userEntity) == 1;
        if (success) {
            BeanUtils.copyProperties(userEntity, requestDto);
        }
        return success;
    }

    /**
     * 新增用户，多条
     *
     * @param dtoList 用户DTOList
     * @return true:成功 false:失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertUserList(List<UserDataDTO> dtoList) {
        if (dtoList == null || dtoList.size() == 0) {
            return false;
        }
        List<UserEntity> entityList = new ArrayList<>();
        for (UserDataDTO item : dtoList) {
            UserEntity entity = new UserEntity();
            entityList.add(entity);
            BeanUtils.copyProperties(item, entity);
        }

        boolean success = userMapper.insertUserList(entityList) >= 1;
        if (success) {
            dtoList.clear();
            for (UserEntity item : entityList) {
                UserDataDTO dto = new UserDataDTO();
                dtoList.add(dto);
                BeanUtils.copyProperties(item, dto);
            }
        }

        return success;
    }

    /**
     * 更新用户，单条
     *
     * @param requestDto 用户DTO
     * @return true:成功 false:失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUser(UserDataDTO requestDto) {
        if (requestDto == null || requestDto.getId() == null) {
            log.info("userDataDTO : null");
            return false;
        }
        UserEntity userEntity = userMapper.getUserDetail(requestDto.getId());
        if (Strings.isNotEmpty(requestDto.getName())) {
            userEntity.setName(requestDto.getName());
        }
        if (requestDto.getAge() != null) {
            userEntity.setAge(requestDto.getAge());
        }
        if (Strings.isNotEmpty(requestDto.getEmail())) {
            userEntity.setEmail(requestDto.getEmail());
        }
        if (Strings.isNotEmpty(requestDto.getAvatar())) {
            userEntity.setAvatar(requestDto.getAvatar());
        }

        boolean success = userMapper.updateUser(userEntity) == 1;
        if (success) {
            BeanUtils.copyProperties(userEntity, requestDto);
        }
        return success;
    }

    /**
     * 通过id删除用户
     *
     * @param id 用户ID
     * @return true:成功 false:失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUser(Long id) {
        if (id == null) {
            return false;
        }
        return userMapper.deleteUser(id) == 1;
    }
}
