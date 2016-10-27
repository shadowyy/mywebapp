package com.shadow.dao;

import com.shadow.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	User queryUserById(int id);
}
