package org.pactera.spring.boot.learn.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.pactera.spring.boot.learn.model.dto.UserDataDTO;
import org.pactera.spring.boot.learn.service.IUserService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Override
    public Boolean insertUser(UserDataDTO userDataDTO) {
        if (userDataDTO == null) {
            log.info("userDataDTO : null");
            return false;
        }
        log.info("id :" + userDataDTO.getId());
        log.info("userName :" + userDataDTO.getUsername());
        log.info("age :" + userDataDTO.getAge());
        return true;
    }
}
