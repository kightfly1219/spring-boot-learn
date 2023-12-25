package org.pactera.spring.boot.learn.service;

import org.pactera.spring.boot.learn.model.dto.UserDataDTO;

public interface IUserService {
    Boolean insertUser(UserDataDTO userDataDTO);
}
