package com.shadow.dao;

import com.shadow.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User queryUserById(int id);

    User queryUser(@Param("user") User user);
}
