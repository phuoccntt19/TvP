package com.project.tvp.dao;

import com.project.tvp.entity.UserEntity;
import com.project.tvp.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
    UserInfoEntity findByUserEntity(UserEntity user);
}
