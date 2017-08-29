package mybatis;

import com.shadow.dao.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author shadowyy
 * @version 2017/8/21 19:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mybatis-spring.xml")
public class TestMybatis {
    @Resource
    private DeptMapper deptMapper;
    @Test
    public void test1() throws Exception {
        //Dept dept=new Dept();
        //dept.setId(1);
        //dept.setName("dasdad");
        ////deptMapper.querySelective(dept);
        //deptMapper.updateByPrimaryKeySelective(dept);
        //deptMapper.deleteByPrimaryKey(1);
    }

    //@Test
    //public void test0() throws Exception {
    //    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
    //            .build(Resources.getResourceAsStream("spring/mybatis-config.xml"));
    //    SqlSession sqlSession = sqlSessionFactory.openSession();
    //    User user = null;
    //    // 方法1
    //    user = sqlSession.<User> selectOne("com.shadow.dao.UserDao.queryUserById", 1);
    //    // 方法2
    //    UserDao userDao = sqlSession.getMapper(UserDao.class);
    //    user = userDao.queryUserById(3);
    //    System.out.println(JSON.toJSONString(user));
    //}
}
