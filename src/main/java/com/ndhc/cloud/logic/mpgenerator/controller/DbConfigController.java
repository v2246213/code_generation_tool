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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
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
        dbConfigService.saveDbConfig(userConfig);
        return ResultModel.buildSuccess("保存成功");
    }

    /**
     *保存生成代码配置
     * @param dbparm
     * @return
     */
    @PostMapping("/findUserConfig")
    public ResultModel findUserConfig(@RequestBody Dbparm dbparm){
        List<UserConfig> userConfigList= dbConfigService.findUserConfig(dbparm.getUserConfigs());
        return ResultModel.buildSuccess(userConfigList);
    }


  /**
   *根据选中的配置生成代码
   * @param dbparm
   * @return
   */
  @PostMapping("/generateCode")
  public ResultModel generateCode(@RequestBody Dbparm dbparm, HttpServletResponse response) throws          IOException{
      List<DbConfig>  dbConfigs=dbConfigService.generateCode(dbparm);
      for (DbConfig dbConfig : dbConfigs) {
          InputStream input = new FileInputStream(file);
          byte[] content = new byte[input.available()];
          InputStream is = new ByteArrayInputStream(content);
      // 设置response参数，可以打开下载页面
      response.reset();
      response.setContentType("application/vnd.ms-excel;charset=utf-8");
      response.addHeader("Access-Control-Allow-Origin", "*");
      response.addHeader("Content-Disposition", "attachment;filename="+new String(dbConfig.getProjectName().getBytes("utf-8"), "iso8859-1")+".zip");
      ServletOutputStream out = response.getOutputStream();
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      try {
          bis = new BufferedInputStream(is);
          bos = new BufferedOutputStream(out);
          byte[] buff = new byte[2048];
          int bytesRead;
          // Simple read/write loop.
          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
              bos.write(buff, 0, bytesRead);
          }
      } catch (final IOException e) {
          throw e;
      } finally {
          if (bis != null)
              bis.close();
          if (bos != null)
              bos.close();
      }
      }
    return ResultModel.buildSuccess(dbConfigs);
  }

}

