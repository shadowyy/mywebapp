package mywebapp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.shadow.dao.UserDao;
import com.shadow.domain.User;

public class TestMybatis {
	@Test
	public void test() throws Exception {
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

	@Test
	public void testGuava() throws Exception {
		LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
			@Override
			public String load(String key) throws Exception {
				String strProValue = "hello " + key + "!";
				return strProValue;
			}

		});
//		System.out.println("jerry value:" + cahceBuilder.apply("jerry"));
		System.out.println("jerry value:" + cahceBuilder.get("jerry"));
		System.out.println("peida value:" + cahceBuilder.get("peida"));
//		System.out.println("peida value:" + cahceBuilder.apply("peida"));
//		System.out.println("lisa value:" + cahceBuilder.apply("lisa"));
		cahceBuilder.put("harry", "ssdded");
		System.out.println("harry value:" + cahceBuilder.get("harry"));
	}

}
