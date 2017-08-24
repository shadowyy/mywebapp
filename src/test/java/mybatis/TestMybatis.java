package mybatis;

import com.alibaba.fastjson.JSON;
import com.shadow.dao.UserDao;
import com.shadow.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shadowyy
 * @version 2017/8/21 19:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mybatis-spring.xml")
public class TestMybatis {

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
