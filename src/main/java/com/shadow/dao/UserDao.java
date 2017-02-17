package com.shadow.dao;

import com.shadow.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User queryUserById(int id);

    User queryUser(User user);

    int insertBatch(List<User> list);
}
