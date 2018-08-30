package com.ndhc.cloud.logic.mpgenerator.util;

import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.service.impl.DbConfigServiceImpl;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangnian
 * @datc 2018/8/30 10:08
 */
public class DBUtil {
    private static org.slf4j.Logger LGR = LoggerFactory.getLogger(DBUtil.class);
    private static Connection conn = null;
    private static  final   String  dburl="jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true";
    private static  final  String userName="root";
    private static  final  String password="123456";
    public  static  Connection druid(DbConfig dbConfig){
        try
        {
            // 1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获得数据库的连接
            conn = DriverManager.getConnection(dbConfig.getDbUrl(), dbConfig.getDbUserName(), dbConfig.getDbPassword());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }


        public static Connection getConnection(){

          try
    {
        // 1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        // 2.获得数据库的连接
        conn = DriverManager.getConnection(dburl, userName, password);
    }
        catch (ClassNotFoundException e)
    {
        e.printStackTrace();
    }
        catch (SQLException e)
    {
        e.printStackTrace();
    }
        return conn;
}
    /**
     * 在开发过程，SQL语句有可能写错，如果能把运行时出错的 SQL 语句直接打印出来，那对排错非常方便，因为其可以直接拷贝到数据库客户端进行调试。
     *
     * @param sql
     *            SQL 语句，可以带有 ? 的占位符
     * @param params
     *            插入到 SQL 中的参数，可单个可多个可不填
     * @return 实际 sql 语句
     */
    public static String printRealSql(String sql, Object[] params) {
        if(params == null || params.length == 0) {
            LGR.info("The SQL is------------>\n" + sql);
            return sql;
        }

        if (!match(sql, params)) {
            LGR.info("SQL 语句中的占位符与参数个数不匹配。SQL：" + sql);
            return null;
        }

        int cols = params.length;
        Object[] values = new Object[cols];
        System.arraycopy(params, 0, values, 0, cols);

        for (int i = 0; i < cols; i++) {
            Object value = values[i];
            if (value instanceof Date) {
                values[i] = "'" + value + "'";
            } else if (value instanceof String) {
                values[i] = "'" + value + "'";
            } else if (value instanceof Boolean) {
                values[i] = (Boolean) value ? 1 : 0;
            }
        }

        String statement = String.format(sql.replaceAll("\\?", "%s"), values);

        LGR.info("The SQL is------------>\n" + statement);

        //ConnectionMgr.addSql(statement); // 用来保存日志

        return statement;
    }

    /**
     * ? 和参数的实际个数是否匹配
     *
     * @param sql
     *            SQL 语句，可以带有 ? 的占位符
     * @param params
     *            插入到 SQL 中的参数，可单个可多个可不填
     * @return true 表示为 ? 和参数的实际个数匹配
     */
    private static boolean match(String sql, Object[] params) {
        if(params == null || params.length == 0) return true; // 没有参数，完整输出
        Matcher m = Pattern.compile("(\\?)").matcher(sql);
        int count = 0;
        while (m.find()) {
            count++;
        }

        return count == params.length;
    }




}
