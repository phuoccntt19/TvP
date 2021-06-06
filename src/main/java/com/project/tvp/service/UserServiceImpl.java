package com.project.tvp.service;

import java.util.List;

import com.project.tvp.dao.UserInfoRepository;
import com.project.tvp.dao.UserRepository;
import com.project.tvp.entity.UserEntity;
import com.project.tvp.entity.UserInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public boolean register(UserEntity user, UserInfoEntity userInfo) {
        user.setRole("ROLE_USER");
        user.setEnabled(true);
        userInfo.setUserEntity(user);
        BCryptPasswordEncoder bPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bPasswordEncoder.encode(user.getPassword()));

        if (userRepository.findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
            userInfoRepository.save(userInfo);
            return true;
        } else {
            return false;
        }
    }
}
