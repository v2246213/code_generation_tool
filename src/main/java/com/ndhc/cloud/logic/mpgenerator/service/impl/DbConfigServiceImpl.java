package com.ndhc.cloud.logic.mpgenerator.service.impl;

import com.mysql.jdbc.JDBC4PreparedStatement;
import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.model.Dbparm;
import com.ndhc.cloud.logic.mpgenerator.service.DbConfigService;
import com.ndhc.cloud.logic.mpgenerator.util.DBUtil;
import com.ndhc.cloud.logic.mpgenerator.util.JsonUtil;
import com.ndhc.cloud.logic.mpgenerator.util.MapUtil;
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
 *
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
        Connection conn = DBUtil
                .getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = DBUtil.getConnection();
            DBUtil.printRealSql(sql,null);
            pstmt = conn.prepareStatement(sql);
                TableInfo tableInfo = null;
            rs=pstmt.executeQuery();
                while (rs.next()) {
                    tableInfo = new TableInfo();
                    tableInfo.setEngine(rs.getString("ENGINE"));
                    tableInfo.setCreateTime(rs.getString("createTime"));
                    tableInfo.setTableName(rs.getString("tableName"));
                    tableInfo.setTableComment(rs.getString("tableComment"));
                    tableInfos.add(tableInfo);
                }
        } catch (Exception e) {
            e.printStackTrace();
             LGR.info("查询报错", sql);
        } finally{
            //7.释放资源
            DBUtil.release(conn, pstmt, rs);
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
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "insert into t_dbconfig(project_name,config_json,create_time) values(?,?,?)";
        try {
            //1.获取连接
            conn = DBUtil.getConnection();
            DBUtil.printRealSql(sql,null);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userConfig.getProjectName());
            pstmt.setString(2, userConfig.getConfigJson());
            pstmt.setTimestamp(3,new java.sql.Timestamp(System.currentTimeMillis()));
            Object[] params = {userConfig.getProjectName(),userConfig.getConfigJson(),new java.sql.Timestamp(System.currentTimeMillis())};
            DBUtil.printRealSql(sql,params);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            LGR.error("添加配置文件报错");
        }
        finally{
            //7.释放资源
            DBUtil.release(conn, pstmt,rs);
        }
    }
    /**
     * 根据用户配置的id查询生成代码的内容
     * @param userConfigs
     * @return
     */
    @Override
    public List<UserConfig> findUserConfig(List<UserConfig> userConfigs) {
        List<UserConfig> userConfigList=new ArrayList<UserConfig>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql ="";
        for (UserConfig userConfigList1 : userConfigs) {
            try {
                int id= userConfigList1.getId();
                 sql = "select id,project_name,config_json,create_time  from t_dbconfig WHERE id="+id;
                //1.获取连接
                conn = DBUtil.getConnection();
                DBUtil.printRealSql(sql,null);
                pstmt = conn.prepareStatement(sql);
                //4.设置参数
                rs = pstmt.executeQuery(sql);//查询之后返回结果集
                while (rs.next()) {
                    UserConfig userConfig = new UserConfig();
                    userConfig.setProjectName(rs.getString("project_name"));
                    userConfig.setCreateTime(rs.getDate("create_time"));
                    userConfig.setConfigJson(rs.getString("config_json"));
                    userConfig.setId(rs.getInt("id"));
                    userConfigList.add(userConfig);
                }
            } catch (Exception e) {
                e.printStackTrace();
                LGR.info("查询报错", sql);
            }
            finally{
                //7.释放资源
                DBUtil.release(conn, pstmt,rs);
            }
        }
        return userConfigList;
    }

    /**
     * 根据选中的配置生成代码
     * @param dbparm
     * @return
     */
    @Override
    public List<DbConfig>  generateCode(Dbparm dbparm) {
        List<DbConfig> dbConfigs=new ArrayList<>();
       List<UserConfig> userConfigList= this.findUserConfig(dbparm.getUserConfigs());
        for (UserConfig userConfig : userConfigList) {
            Map<String, Object> map = JsonUtil.jsonToMap(userConfig.getConfigJson());
           String dbType= map.get("dbType").toString();
            String [] excludeTable = map.get("excludeTable").toString().split(",");
           String[] superEntityColumns=map.get("superEntityColumns").toString().split(",");
           String[]  tablePrefix=map.get("tablePrefix").toString().split(",");
           try {
               //map转javaBean
              DbConfig dbConfig=(DbConfig)MapUtil.mapToObject(map,DbConfig.class);
              dbConfig.setDbType(dbType);
               dbConfig.setExcludeTable(excludeTable);
               dbConfig.setSuperEntityColumns(superEntityColumns);
               dbConfig.setTablePrefix(tablePrefix);
               dbConfigs.add(dbConfig);
               MpGenerator.genCode(dbConfig);
           }catch (Exception e){
               e.printStackTrace();
               LGR.error("实体转换失败");
           }
        }
        //转换完毕
        return dbConfigs;

    }
    public  static void main(String args[])
    {
        Dbparm dbparm=new Dbparm();
        List<UserConfig> userConfigList=new ArrayList<UserConfig>();
        UserConfig userConfig=new UserConfig();
        userConfig.setId(33);
        userConfigList.add(userConfig);
                dbparm.setUserConfigs(userConfigList);
        DbConfigServiceImpl dbConfigService=new DbConfigServiceImpl();
        dbConfigService.generateCode(dbparm);
    }
}
