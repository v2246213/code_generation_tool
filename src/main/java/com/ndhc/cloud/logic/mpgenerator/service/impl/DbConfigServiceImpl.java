package com.ndhc.cloud.logic.mpgenerator.service.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.service.DbConfigService;
import com.ndhc.cloud.logic.mpgenerator.util.DBUtil;
import com.ndhc.cloud.logic.mpgenerator.util.MpGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yangnian
 * @datc 2018/8/29 15:19
 */
@Service
public class DbConfigServiceImpl implements DbConfigService {
    private org.slf4j.Logger LGR = LoggerFactory.getLogger(DbConfigServiceImpl.class);
    /**
     * 根据库名查询这个库所有的表信息
     *
     * @param dbConfig
     * @return List<TableInfo>
     */
    @Override
    public List<TableInfo> findTableInfo(DbConfig dbConfig) {
        List<TableInfo> tableInfos = new ArrayList<TableInfo>();
        Map<String, Object> map = null;
        String sql = "select table_name tableName, ENGINE,table_comment tableComment,create_time createTime from information_schema.TABLES WHERE table_schema = ( SELECT DATABASE ( ) )";//查询user表的所有信息
        Connection con = com.ndhc.cloud.logic.mpgenerator.util.DBUtil.druid(dbConfig);
        try {
            if (!con.isClosed()) {//判断数据库是否链接成功
                PreparedStatement ptmt = con.prepareStatement(sql);
                ResultSet rs = ptmt.executeQuery(sql);//查询之后返回结果集
                TableInfo tableInfo = null;
                while (rs.next()) {
                    tableInfo = new TableInfo();
                    tableInfo.setEngine(rs.getString("ENGINE"));
                    tableInfo.setCreateTime(rs.getString("createTime"));
                    tableInfo.setTableName(rs.getString("tableName"));
                    tableInfo.setTableComment(rs.getString("tableComment"));
                    tableInfos.add(tableInfo);
                }
                rs.close();//关闭资源
                con.close();//关闭数据库
            }
        } catch (Exception e) {
            e.printStackTrace();
             LGR.info("查询报错", sql);
        }
        if (tableInfos != null && !tableInfos.isEmpty()) {
            return tableInfos;
        } else {
            return null;
        }

    }

    /**
     * 添加配置信息
     *
     * @param userConfig
     * @return
     */
    @Override
    public void insertDbConfig(UserConfig userConfig) {
        Connection con = com.ndhc.cloud.logic.mpgenerator.util.DBUtil.getConnection();
        try {
            //判断数据库是否链接成功
            if (!con.isClosed()) {
                //3、创建Statement对象
                String sql = "insert into t_dbconfig(project_name,config_json,create_time) values(?,?,?)";
                PreparedStatement ptmt = con.prepareStatement(sql);
                ptmt.setString(1, userConfig.getProjectName());
                ptmt.setString(2, userConfig.getConfigJson());
                ptmt.setTimestamp(3,new java.sql.Timestamp(System.currentTimeMillis()));
                Object[] params = {userConfig.getProjectName(),userConfig.getConfigJson(),new java.sql.Timestamp(System.currentTimeMillis())};
                DBUtil.printRealSql(sql,params);
                ptmt.execute();
                con.close();//关闭数据库
            }
        } catch (Exception e) {
            e.printStackTrace();
            LGR.error("添加配置文件报错");
        }
    }

    /**
     * 生成代码
     * @param dbConfig
     * @return
     */
    @Override
    public void  generateCode(DbConfig dbConfig) {
        MpGenerator.genCode(dbConfig);
    }
    public  static  void  main(String[] args){
       // net.sf.json.JSONObject jsonObject= net.sf.json.JSONObject.fromObject(ss);
        DbConfig dbConfig=new DbConfig();

    }
}
