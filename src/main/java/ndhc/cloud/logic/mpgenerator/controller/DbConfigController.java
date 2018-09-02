package ndhc.cloud.logic.mpgenerator.controller;


import com.alibaba.fastjson.JSON;
import ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import ndhc.cloud.logic.mpgenerator.entity.TableInfo;
import ndhc.cloud.logic.mpgenerator.entity.UserConfig;
import ndhc.cloud.logic.mpgenerator.model.Dbparm;
import ndhc.cloud.logic.mpgenerator.model.ResultModel;
import ndhc.cloud.logic.mpgenerator.service.DbConfigService;
import ndhc.cloud.logic.mpgenerator.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

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
     * 根据库名查询这个库所有的表信息
     *
     * @param dbConfig
     * @return
     */
    @PostMapping("/findTableInfo")
    public ResultModel findTableInfo(@RequestBody DbConfig dbConfig) {
        List<TableInfo> tableInfos = dbConfigService.findTableInfo(dbConfig);
        return ResultModel.buildSuccess(tableInfos);
    }

    /**
     * 添加生成代码配置
     *
     * @param params
     * @return
     */
    @PostMapping("/insertDbConfig")
    public ResultModel insertDbConfig(@RequestBody String params) {
        //Assert.isNull(params,"不能传空参数");
        String configJson = JSON.parseObject(params).get("configJson").toString();
        String projectName = JSON.parseObject(params).get("projectName").toString();
        UserConfig userConfig = new UserConfig();
        userConfig.setConfigJson(configJson);
        userConfig.setProjectName(projectName);
        dbConfigService.saveDbConfig(userConfig);
        return ResultModel.buildSuccess("保存成功");
    }

    /**
     * 保存生成代码配置
     *
     * @param dbparm
     * @return
     */
    @PostMapping("/findUserConfig")
    public ResultModel findUserConfig(@RequestBody Dbparm dbparm) {
        List<UserConfig> userConfigList = dbConfigService.findUserConfig(dbparm.getUserConfigs());
        return ResultModel.buildSuccess(userConfigList);
    }


    /**
     * 根据选中的配置生成代码
     *
     * @param dbparm
     * @return
     */
    @PostMapping("/generateCode")
    public void generateCode(@RequestBody Dbparm dbparm, HttpServletResponse response, HttpServletRequest request) throws IOException {
       // List<DbConfig> dbConfigs = dbConfigService.generateCode(dbparm);
        //根据条件得到文件路径
        /*    try {
            String fileurl="C:\\Users\\Administrator\\Desktop\\mpgenerator\\static.zip";
            System.out.println("===========文件路径==========="+fileurl);
//将文件读入文件流
            InputStream inStream = new FileInputStream(fileurl);
//获得浏览器代理信息
            final String userAgent = request.getHeader("USER-AGENT");
//判断浏览器代理并分别设置响应给浏览器的编码格式
            String finalFileName ="static.zip";
            if(StringUtils.contains(userAgent, "MSIE")||StringUtils.contains(userAgent,"Trident")){//IE浏览器
                finalFileName = URLEncoder.encode("UTF8");
                System.out.println("IE浏览器");
            }else if(StringUtils.contains(userAgent, "Mozilla")){//google,火狐浏览器
                finalFileName = new String( "ISO8859-1");
            }else{
                finalFileName = URLEncoder.encode("UTF8");//其他浏览器
            }
//设置HTTP响应头
            response.reset();//重置 响应头
            response.setContentType("application/x-download");//告知浏览器下载文件，而不是直接打开，浏览器默认为打开
            response.addHeader("Content-Disposition" ,"attachment;filename=\"" +finalFileName+ "\"");//下载文件的名称
            //System.out.println(showValue);
// 循环取出流中的数据
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
            response.getOutputStream().close();
           }catch(Exception e) {
            e.printStackTrace();
            System.out.println("==========出错了！！===========");
        }
        System.out.println("============成功了！！===========");
}*/
        try {
            // path是指欲下载的文件的路径。
            String path = "C:\\Users\\Administrator\\Desktop\\mpgenerator\\static.zip";
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            //设置文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            //设置文件打下
            response.addHeader("Content-Length", "" + file.length());
            response.addHeader("Access-Control-Allow-Origin", "*");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-msdownload");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
