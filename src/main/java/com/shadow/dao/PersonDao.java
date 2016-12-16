package com.shadow.dao;

import com.shadow.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alice on 2016/12/2 10:38
 */
@Repository
public interface PersonDao {
    List<Person> queryPersonById(int id);

}
