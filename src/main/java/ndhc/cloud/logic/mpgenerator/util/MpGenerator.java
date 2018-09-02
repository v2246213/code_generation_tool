package ndhc.cloud.logic.mpgenerator.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import ndhc.cloud.logic.mpgenerator.entity.DbConfig;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jesus on 2017/7/10.
 */
public class MpGenerator {

    public static void genCode(DbConfig config) {
        ndhc.cloud.logic.mpgenerator.util.AutoGenerator mpg = new ndhc.cloud.logic.mpgenerator.util.AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir" ) + "\\src\\main\\resources\\static");
        gc.setFileOverride(config.getFileOverride());
        gc.setActiveRecord(config.getActiveRecord());
        // XML 二级缓存
        gc.setEnableCache(config.getEnableCache());
        // XML ResultMap
        gc.setBaseResultMap(config.getBaseResultMap());
        // XML columList
        gc.setBaseColumnList(config.getBaseColumnList());

        gc.setAuthor(config.getAuthor());

        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(config.getDbType());
        dsc.setDriverName(config.getDriverName());
        dsc.setUsername(config.getDbUserName());
        dsc.setPassword(config.getDbPassword());
        dsc.setUrl(config.getDbUrl());
        mpg.setDataSource(dsc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(config.getCapitalMode());
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setTablePrefix(config.getTablePrefix());
        // 排除生成的表
        strategy.setExclude(config.getExcludeTable());

//         自定义 service 父类
        strategy.setSuperServiceClass(config.getSuperServiceClass());
//         自定义 service 实现类父类
        strategy.setSuperServiceImplClass(config.getSuperServiceImplClass());

        strategy.setSuperEntityClass(config.getSuperEntityClass());

        strategy.setSuperMapperClass(config.getSuperMapperClass());

        strategy.setSuperEntityColumns(config.getSuperEntityColumns());

        strategy.setSuperControllerClass(config.getSuperControllerClass());




        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                if(config.getSuperRestControllerClass()!=null){
                    String packageStr =  config.getSuperRestControllerClass();
                    map.put("superRestControllerClass",packageStr.substring(packageStr.lastIndexOf(".")+1, packageStr.length()) );
                }
                map.put("superControllerClassPackage", config.getSuperRestControllerClass());
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
         focList.add(new FileOutConfig("/templates/list_ftl.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = config.getPackageName().replaceAll("\\.", "\\" + File.separator);
                return config.getOutDir() + File.separator + path + File.separator + "ftl" + File.separator + tableInfo.getEntityName().toLowerCase() + File.separator+"list" + ".ftl" ;
            }
        });
        focList.add(new FileOutConfig("/templates/add_ftl.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = config.getPackageName().replaceAll("\\.", "\\" + File.separator);
                return config.getOutDir() + File.separator + path + File.separator + "ftl" + File.separator + tableInfo.getEntityName().toLowerCase() + File.separator+ "add" + ".ftl" ;
            }
        });
        focList.add(new FileOutConfig("/templates/edit_ftl.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = config.getPackageName().replaceAll("\\.", "\\" + File.separator);
                return config.getOutDir() + File.separator + path + File.separator + "ftl" + File.separator + tableInfo.getEntityName().toLowerCase() + File.separator + "edit" + ".ftl" ;
            }
        });

        focList.add(new FileOutConfig("/templates/rest_controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = config.getPackageName().replaceAll("\\.", "\\" + File.separator);
                return config.getOutDir()+File.separator + path + File.separator + "api" + File.separator + tableInfo.getEntityName() + "Controller.java" ;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setStrategy(strategy);

        PackageConfig pc = new PackageConfig();
        pc.setParent(config.getPackageName());
        mpg.setPackageInfo(pc);
        mpg.execute();


    }
}
