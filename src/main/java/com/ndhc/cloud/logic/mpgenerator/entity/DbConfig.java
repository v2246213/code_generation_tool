package com.ndhc.cloud.logic.mpgenerator.entity;
import com.baomidou.mybatisplus.generator.config.rules.DbType;

/** 数据库配置属性
 * @author yangnian
 * @datc 2018/8/29 15:03
 */
public class DbConfig {
    //数据库地址
    private String dbUrl;
   //用户名
    private String dbUserName;
   //密码
    private String dbPassword;
  //驱动名(对应数据库)
    private String driverName;
   //数据库类型
    private String dbType;
  //service 父类
    private String superServiceClass;
    //service 实现类父类
    private String superServiceImplClass;
 //实体父级
    private String superEntityClass;
 //mapper父类
    private String superMapperClass;
  //controller父类
    private String superControllerClass;

    private String superRestControllerClass;
 //实体公共字段
    private String[] superEntityColumns;
   //包名
    private String packageName;
 //活动记录
    private Boolean fileOverride;
//是否覆盖
    private Boolean activeRecord;
//开启二级缓存
    private Boolean enableCache;
//生成结果集
    private Boolean baseResultMap;
//生成结果列
    private Boolean baseColumnList;
   //作者
    private String author;
 //表前缀
    private String[] tablePrefix;
  //输出路径
    private String outDir;
    //ip
    private String ip;
    //端口
    private String port;
    /**
     * 库名
     */
    private  String dataBaseName;
//排除表
    private String[] excludeTable;
    /**
     *大小写
     */
    private Boolean capitalMode = Boolean.FALSE;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String[] getExcludeTable() {
        return excludeTable;
    }

    public void setExcludeTable(String[] excludeTable) {
        this.excludeTable = excludeTable;
    }

    public String getOutDir() {
        return outDir;
    }

    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }

    public String[] getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String[] tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public String[] getSuperEntityColumns() {
        return superEntityColumns;
    }

    public void setSuperEntityColumns(String[] superEntityColumns) {
        this.superEntityColumns = superEntityColumns;
    }

    public DbType getDbType() {
        DbType[] types = DbType.values();
        for (DbType type : types){
            if(type.getValue().equals(dbType)){
                return type;
            }
        }
        return null;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Boolean getFileOverride() {
        return fileOverride;
    }

    public void setFileOverride(Boolean fileOverride) {
        this.fileOverride = fileOverride;
    }

    public Boolean getActiveRecord() {
        return activeRecord;
    }

    public void setActiveRecord(Boolean activeRecord) {
        this.activeRecord = activeRecord;
    }

    public Boolean getEnableCache() {
        return enableCache;
    }

    public void setEnableCache(Boolean enableCache) {
        this.enableCache = enableCache;
    }

    public Boolean getBaseResultMap() {
        return baseResultMap;
    }

    public void setBaseResultMap(Boolean baseResultMap) {
        this.baseResultMap = baseResultMap;
    }

    public Boolean getBaseColumnList() {
        return baseColumnList;
    }

    public void setBaseColumnList(Boolean baseColumnList) {
        this.baseColumnList = baseColumnList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSuperServiceClass() {
        return superServiceClass;
    }

    public void setSuperServiceClass(String superServiceClass) {
        this.superServiceClass = superServiceClass;
    }

    public String getSuperServiceImplClass() {
        return superServiceImplClass;
    }

    public void setSuperServiceImplClass(String superServiceImplClass) {
        this.superServiceImplClass = superServiceImplClass;
    }

    public String getSuperEntityClass() {
        return superEntityClass;
    }

    public void setSuperEntityClass(String superEntityClass) {
        this.superEntityClass = superEntityClass;
    }

    public String getSuperMapperClass() {
        return superMapperClass;
    }

    public void setSuperMapperClass(String superMapperClass) {
        this.superMapperClass = superMapperClass;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSuperControllerClass() {
        return superControllerClass;
    }

    public void setSuperControllerClass(String superControllerClass) {
        this.superControllerClass = superControllerClass;
    }

    public String getSuperRestControllerClass() {
        return superRestControllerClass;
    }

    public void setSuperRestControllerClass(String superRestControllerClass) {
        this.superRestControllerClass = superRestControllerClass;
    }

    public Boolean getCapitalMode() {
        return capitalMode;
    }

    public void setCapitalMode(Boolean capitalMode) {
        this.capitalMode = capitalMode;
    }


}
