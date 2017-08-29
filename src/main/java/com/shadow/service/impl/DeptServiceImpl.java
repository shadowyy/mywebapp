package com.shadow.service.impl;

import com.shadow.dao.DeptMapper;
import com.shadow.domain.Dept;
import com.shadow.service.IDeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/29 15:42
 */
@Service
public class DeptServiceImpl implements IDeptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Resource
    DeptMapper deptMapper;

    public int deleteByPrimaryKey(Integer id) {
        return deptMapper.deleteByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Dept dept) {
        return deptMapper.updateByPrimaryKeySelective(dept);
    }

    public int insertSelective(Dept dept) {
        return deptMapper.insertSelective(dept);
    }

    public List<Dept> querySelective(Dept dept) {
        return deptMapper.querySelective(dept);
    }
}


