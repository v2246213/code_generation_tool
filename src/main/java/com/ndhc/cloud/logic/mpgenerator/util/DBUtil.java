package com.ndhc.cloud.logic.mpgenerator.util;

import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.service.impl.DbConfigServiceImpl;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yangnian
 * @datc 2018/8/30 10:08
 */
public class DBUtil {
    private static org.slf4j.Logger LGR = LoggerFactory.getLogger(DBUtil.class);
    /**
     * 获取连接方法
     *
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs){
        if (rs !=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt !=null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn !=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * 数据库驱动类型识别
     * @return
     */
 public static Boolean     driverType(DbConfig dbConfig){
    String IP= dbConfig.getIp();
    String port= dbConfig.getPort();
    String dataBaseName=dbConfig.getDataBaseName();
    String dbType= dbConfig.getDbType().getValue();
     //dbType.getValue();
     if (!RegexMatches.ipVoild(IP)){
       return false;
     }else if(!RegexMatches.ipVoild(port)){
         return false;
     }
     if("MYSQL".equals(dbType)){
         dbConfig.setDbUrl("jdbc:mysql://"+IP+":"+port+"/"+dataBaseName+"?characterEncoding=utf8");
     }else if("ORACLE".equals(dbType)){
         dbConfig.setDbUrl("jdbc:oracle:thin:@"+IP+":"+port+":"+dataBaseName);
     }else if("SQL_SERVER2000".equals(dbType)){
         dbConfig.setDbUrl("jdbc:microsoft:sqlserver://"+IP+":"+port+";DatabaseName="+dataBaseName);
     }else if("SQL_SERVER2005".equals(dbType)){
         dbConfig.setDbUrl("jdbc:sqlserver://"+IP+":"+port+";DatabaseName="+dataBaseName);
     }else if("POSTGRE_SQL".equals(dbType)){
         dbConfig.setDbUrl("jdbc:postgresql://"+IP+":"+port+"/"+dataBaseName);
     }
     return true;
 }



}
