package com.project.tvp.service;

import com.project.tvp.entity.UserEntity;
import com.project.tvp.entity.UserInfoEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean register(UserEntity user, UserInfoEntity userInfo);
}
