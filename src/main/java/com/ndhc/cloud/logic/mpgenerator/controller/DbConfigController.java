package com.ndhc.cloud.logic.mpgenerator.controller;


import com.alibaba.fastjson.JSON;
import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import com.ndhc.cloud.logic.mpgenerator.model.Dbparm;
import com.ndhc.cloud.logic.mpgenerator.model.ResultModel;
import com.ndhc.cloud.logic.mpgenerator.service.DbConfigService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author yangnian123
 * @since 2018-08-29
 */
@RestController
@RequestMapping("/generator")
public class DbConfigController {
@Resource
private DbConfigService dbConfigService;

  /**
   *根据库名查询这个库所有的表信息
   * @param dbConfig
   * @return
   */
  @PostMapping("/findTableInfo")
public ResultModel findTableInfo(@RequestBody DbConfig dbConfig){
  List<TableInfo> tableInfos= dbConfigService.findTableInfo(dbConfig);
    return ResultModel.buildSuccess(tableInfos);}

  /**
     *添加生成代码配置
     * @param params
     * @return
     */
    @PostMapping("/insertDbConfig")
    public ResultModel insertDbConfig( @RequestBody  String params){
         //Assert.isNull(params,"不能传空参数");
        String configJson = JSON.parseObject(params).get("configJson").toString();
        String projectName = JSON.parseObject(params).get("projectName").toString();
        UserConfig userConfig=new UserConfig();
        userConfig.setConfigJson(configJson);
        userConfig.setProjectName(projectName);
        dbConfigService.insertDbConfig(userConfig);
        return ResultModel.buildSuccess("保存成功");
    }

   /* *//**
     *保存生成代码配置
     * @param dbparm
     * @return
     *//*
    @PostMapping("/findUserConfig")
    public ResultModel findUserConfig(@RequestBody Dbparm dbparm){
        List<UserConfig> userConfigList= dbConfigService.findUserConfig(dbparm.getUserConfigs());
        return ResultModel.buildSuccess(userConfigList);
    }*/


  /**
   *根据选中的配置生成代码
   * @param dbparm
   * @return
   */
  @PostMapping("/generateCode")
  public ResultModel generateCode(@RequestBody Dbparm dbparm) {
         // ​dbConfigService.generateCode(dbparm);
    return ResultModel.buildSuccess( "生成成功");
  }

}

