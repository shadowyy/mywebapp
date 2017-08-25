package test;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * 面试题
 *
 * @author yy
 * @version 2017/1/23 19:14
 */
public class junitTest {
    private JdbcTemplate jdbcTemplate;
    private static final String COUNT_SQL="select count(*) from test.user";

    public int count() {
        return this.jdbcTemplate.queryForObject(COUNT_SQL,null,Integer.class);
    }

    @Test
    public void method() {
        //setup
        this.jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.queryForObject(COUNT_SQL,null,Integer.class)).thenReturn(10);

        //exercise
        int num = count();

        //verify
        verify(jdbcTemplate, times(1)).queryForObject(COUNT_SQL,null,Integer.class);
        assertEquals(20, num);

        //teardown
    }

    public static void main(String[] args) throws Exception {
        //Class clz=Class.forName("com.shadow.domain.Users");
        //System.out.println(clz.getSimpleName());



    }

}
