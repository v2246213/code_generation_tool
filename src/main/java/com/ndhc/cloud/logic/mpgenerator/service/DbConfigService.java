package com.ndhc.cloud.logic.mpgenerator.service;

import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.model.Dbparm;
import com.ndhc.cloud.logic.mpgenerator.model.ResultModel;
import org.apache.catalina.User;

import java.util.List;

/**
 * @author yangnian
 * @datc 2018/8/29 15:18
 */
public interface DbConfigService {
    /**
     * 根据库名查询这个库所有的表信息
     * @param dbConfig
     * @return  List<TableInfo>
     */
    List<TableInfo> findTableInfo(DbConfig dbConfig);

    /**
     * 添加配置信息
     * @param userConfig
     * @return
     */
    void     saveDbConfig(UserConfig userConfig);
    /**
     * 根据选中的配置生成代码
     * @return
     */
    List<DbConfig> generateCode(Dbparm dbparm);

    /**
     * 根据用户配置的id查询生成代码的内容
     * @param userConfigs
     * @return
     */
    List<UserConfig> findUserConfig(List<UserConfig> userConfigs);
}
