package com.shadow.service;

import com.shadow.domain.Dept;

import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 15:28
 */
public interface IDeptService {
    int deleteByPrimaryKey(Integer integer);

    int updateByPrimaryKeySelective(Dept dept);

    int insertSelective(Dept dept);

    List<Dept> querySelective(Dept dept);
}
