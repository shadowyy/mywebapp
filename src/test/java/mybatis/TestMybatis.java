package mybatis;

import com.alibaba.fastjson.JSON;
import com.shadow.dao.UserDao;
import com.shadow.dao.UsersMapper;
import com.shadow.domain.User;
import com.shadow.domain.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/21 19:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mybatis-spring.xml")
public class TestMybatis {
    @Resource
    private UsersMapper usersMapper;

    @Test
    public void test1() {
        Users users=new Users();
        //users.setName("xixi");
        users.setAge(60);
        List<Users> usersList = usersMapper.querySelective(users);
        System.out.println(usersList);
    }

    @Test
    public void test2() {
        Users users=new Users();
        users.setName("wen");
        users.setAge(70);
        System.out.println(usersMapper.insertSelective(users));
    }

    @Test
    public void test3() {
        Users users=new Users();
        users.setName("xixi");
        users.setAge(66);
        System.out.println(usersMapper.updateByPrimaryKeySelective(users));
    }

    @Test
    public void test4() {
        System.out.println(usersMapper.deleteByPrimaryKey(1));
    }

    @Test
    public void test0() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("spring/mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = null;
        // 方法1
        user = sqlSession.<User> selectOne("com.shadow.dao.UserDao.queryUserById", 1);
        // 方法2
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        user = userDao.queryUserById(3);
        System.out.println(JSON.toJSONString(user));
    }
}
