package com.ndhc.cloud.logic.mpgenerator.service;

import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.model.ResultModel;

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
    void     insertDbConfig(UserConfig userConfig);
    /**
     * 生成代码按钮
     * @return
     */
    void generateCode(DbConfig dbConfig);
}
