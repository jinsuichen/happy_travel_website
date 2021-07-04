package xyz.chenjinsui.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * Druid连接池的工具类
 * @author FengLing
 */
public class JdbcUtils {

    private static DataSource ds;


    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataSource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(ResultSet rs,Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close(); //归还连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public static void close(Statement stmt, Connection conn){
        JdbcUtils.close(null, stmt, conn);
    }

    public static DataSource getDataSource(){
        return ds;
    }

}
