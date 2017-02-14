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

    public int count() {
        return this.jdbcTemplate.queryForInt("select count(*) from test.user");
    }

    @Test
    public void method() {
        //setup
        this.jdbcTemplate = mock(JdbcTemplate.class);
        String sql = "select count(*) from test.user";
        when(jdbcTemplate.queryForInt(sql)).thenReturn(10);

        //exercise
        int num = count();

        //verify
        verify(jdbcTemplate, times(1)).queryForInt(sql);
        assertEquals(20, num);

        //teardown

    }

}
