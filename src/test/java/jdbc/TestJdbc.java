package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * jdbc
 *
 * @author yy
 * @version 2017/1/24 15:53
 */
public class TestJdbc {
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useUnicode=true&autoReconnect=true&failOverReadOnly=false&useSSL=false&allowMultiQueries=true";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM USERS");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println(rs.getInt(1));
        }
    }


}
