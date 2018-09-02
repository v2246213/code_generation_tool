package ndhc.cloud.logic.mpgenerator.util;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/9/2 0002.
 */
public class AutoGenerator {
    private static final Logger logger = LoggerFactory.getLogger(com.baomidou.mybatisplus.generator.AutoGenerator.class);
    protected ConfigBuilder config;
    protected InjectionConfig injectionConfig;
    private DataSourceConfig dataSource;
    private StrategyConfig strategy;
    private PackageConfig packageInfo;
    private TemplateConfig template;
    private GlobalConfig globalConfig;
    private VelocityEngine engine;

    public AutoGenerator() {
    }

    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        this.initConfig();
        this.mkdirs(this.config.getPathInfo());
        Map<String, VelocityContext> ctxData = this.analyzeData(this.config);
        Iterator i$ = ctxData.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<String, VelocityContext> ctx = (Map.Entry)i$.next();
            this.batchOutput((String)ctx.getKey(), (VelocityContext)ctx.getValue());
        }
        logger.debug("==========================文件生成完成！！！==========================");



    }

    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        return config.getTableInfoList();
    }

    private Map<String, VelocityContext> analyzeData(ConfigBuilder config) {
        List<TableInfo> tableList = this.getAllTableInfoList(config);
        Map<String, String> packageInfo = config.getPackageInfo();
        Map<String, VelocityContext> ctxData = new HashMap();
        String superEntityClass = this.getSuperClassName(config.getSuperEntityClass());
        String superMapperClass = this.getSuperClassName(config.getSuperMapperClass());
        String superServiceClass = this.getSuperClassName(config.getSuperServiceClass());
        String superServiceImplClass = this.getSuperClassName(config.getSuperServiceImplClass());
        String superControllerClass = this.getSuperClassName(config.getSuperControllerClass());
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
        Iterator i$ = tableList.iterator();

        while(i$.hasNext()) {
            TableInfo tableInfo = (TableInfo)i$.next();
            VelocityContext ctx = new VelocityContext();
            if(null != this.injectionConfig) {
                this.injectionConfig.initMap();
                ctx.put("cfg", this.injectionConfig.getMap());
            }

            if(config.getGlobalConfig().isActiveRecord()) {
                tableInfo.setImportPackages(Model.class.getCanonicalName());
            }

            if(tableInfo.isConvert()) {
                tableInfo.setImportPackages(TableName.class.getCanonicalName());
            }

            if(tableInfo.isLogicDelete(config.getStrategyConfig().getLogicDeleteFieldName())) {
                tableInfo.setImportPackages(TableLogic.class.getCanonicalName());
            }

            if(StringUtils.isNotEmpty(config.getStrategyConfig().getVersionFieldName())) {
                tableInfo.setImportPackages(Version.class.getCanonicalName());
            }

            if(StringUtils.isNotEmpty(config.getSuperEntityClass())) {
                tableInfo.setImportPackages(config.getSuperEntityClass());
            } else {
                tableInfo.setImportPackages(Serializable.class.getCanonicalName());
            }

            if(config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()) {
                Iterator is = tableInfo.getFields().iterator();

                while(is.hasNext()) {
                    TableField field = (TableField)i$.next();
                    if(field.getPropertyType().equalsIgnoreCase("boolean") && field.getPropertyName().startsWith("is")) {
                        field.setPropertyName(config.getStrategyConfig(), StringUtils.removePrefixAfterPrefixToLower(field.getPropertyName(), 2));
                    }
                }
            }

            if(config.getStrategyConfig().isControllerMappingHyphenStyle()) {
                ctx.put("controllerMappingHyphenStyle", Boolean.valueOf(config.getStrategyConfig().isControllerMappingHyphenStyle()));
                ctx.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
            }

            ctx.put("restControllerStyle", Boolean.valueOf(config.getStrategyConfig().isRestControllerStyle()));
            ctx.put("package", packageInfo);
            GlobalConfig globalConfig = config.getGlobalConfig();
            ctx.put("author", globalConfig.getAuthor() + "123");
            ctx.put("idType", globalConfig.getIdType() == null?null:globalConfig.getIdType().toString());
            ctx.put("logicDeleteFieldName", config.getStrategyConfig().getLogicDeleteFieldName());
            ctx.put("versionFieldName", config.getStrategyConfig().getVersionFieldName());
            ctx.put("activeRecord", Boolean.valueOf(globalConfig.isActiveRecord()));
            ctx.put("kotlin", Boolean.valueOf(globalConfig.isKotlin()));
            ctx.put("date", date);
            ctx.put("table", tableInfo);
            ctx.put("enableCache", Boolean.valueOf(globalConfig.isEnableCache()));
            ctx.put("baseResultMap", Boolean.valueOf(globalConfig.isBaseResultMap()));
            ctx.put("baseColumnList", Boolean.valueOf(globalConfig.isBaseColumnList()));
            ctx.put("entity", tableInfo.getEntityName());
            ctx.put("entityColumnConstant", Boolean.valueOf(config.getStrategyConfig().isEntityColumnConstant()));
            ctx.put("entityBuilderModel", Boolean.valueOf(config.getStrategyConfig().isEntityBuilderModel()));
            ctx.put("entityLombokModel", Boolean.valueOf(config.getStrategyConfig().isEntityLombokModel()));
            ctx.put("entityBooleanColumnRemoveIsPrefix", Boolean.valueOf(config.getStrategyConfig().isEntityBooleanColumnRemoveIsPrefix()));
            ctx.put("superEntityClass", superEntityClass);
            ctx.put("superMapperClassPackage", config.getSuperMapperClass());
            ctx.put("superMapperClass", superMapperClass);
            ctx.put("superServiceClassPackage", config.getSuperServiceClass());
            ctx.put("superServiceClass", superServiceClass);
            ctx.put("superServiceImplClassPackage", config.getSuperServiceImplClass());
            ctx.put("superServiceImplClass", superServiceImplClass);
            ctx.put("superControllerClassPackage", config.getSuperControllerClass());
            ctx.put("superControllerClass", superControllerClass);
            ctxData.put(tableInfo.getEntityName(), ctx);
        }

        return ctxData;
    }

    private String getSuperClassName(String classPath) {
        return StringUtils.isEmpty(classPath)?null:classPath.substring(classPath.lastIndexOf(".") + 1);
    }

    private void mkdirs(Map<String, String> pathInfo) {
        Iterator i$ = pathInfo.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)i$.next();
            File dir = new File((String)entry.getValue());
            if(!dir.exists()) {
                boolean result = dir.mkdirs();
                if(result) {
                    logger.debug("创建目录： [" + (String)entry.getValue() + "]");
                }
            }
        }

    }

    private void batchOutput(String entityName, VelocityContext context) {
        try {
            TableInfo tableInfo = (TableInfo)context.get("table");
            Map<String, String> pathInfo = this.config.getPathInfo();
            String entityFile = String.format((String)pathInfo.get("entity_path") + File.separator + "%s" + this.suffixJavaOrKt(), new Object[]{entityName});
            String mapperFile = String.format((String)pathInfo.get("mapper_path") + File.separator + tableInfo.getMapperName() + this.suffixJavaOrKt(), new Object[]{entityName});
            String xmlFile = String.format((String)pathInfo.get("xml_path") + File.separator + tableInfo.getXmlName() + ".xml", new Object[]{entityName});
            String serviceFile = String.format((String)pathInfo.get("serivce_path") + File.separator + tableInfo.getServiceName() + this.suffixJavaOrKt(), new Object[]{entityName});
            String implFile = String.format((String)pathInfo.get("serviceimpl_path") + File.separator + tableInfo.getServiceImplName() + this.suffixJavaOrKt(), new Object[]{entityName});
            String controllerFile = String.format((String)pathInfo.get("controller_path") + File.separator + tableInfo.getControllerName() + this.suffixJavaOrKt(), new Object[]{entityName});
            TemplateConfig template = this.config.getTemplate();
            if(this.isCreate(entityFile)) {
                this.vmToFile(context, template.getEntity(this.config.getGlobalConfig().isKotlin()), entityFile);
            }

            if(this.isCreate(mapperFile)) {
                this.vmToFile(context, template.getMapper(), mapperFile);
            }

            if(this.isCreate(xmlFile)) {
                this.vmToFile(context, template.getXml(), xmlFile);
            }

            if(this.isCreate(serviceFile)) {
                this.vmToFile(context, template.getService(), serviceFile);
            }

            if(this.isCreate(implFile)) {
                this.vmToFile(context, template.getServiceImpl(), implFile);
            }

            if(this.isCreate(controllerFile)) {
                this.vmToFile(context, template.getController(), controllerFile);
            }

            if(this.injectionConfig != null) {
                List<FileOutConfig> focList = this.injectionConfig.getFileOutConfigList();
                if(CollectionUtils.isNotEmpty(focList)) {
                    Iterator i$ = focList.iterator();

                    while(i$.hasNext()) {
                        FileOutConfig foc = (FileOutConfig)i$.next();
                        if(this.isCreate(foc.outputFile(tableInfo))) {
                            this.vmToFile(context, foc.getTemplatePath(), foc.outputFile(tableInfo));
                        }
                    }
                }
            }
        } catch (IOException var15) {
            logger.error("无法创建文件，请检查配置信息！", var15);
        }

    }

    protected String suffixJavaOrKt() {
        return this.config.getGlobalConfig().isKotlin()?".kt":".java";
    }

    private void vmToFile(VelocityContext context, String templatePath, String outputFile) throws IOException {
        if(!StringUtils.isEmpty(templatePath)) {
            VelocityEngine velocity = this.getVelocityEngine();
            Template template = velocity.getTemplate(templatePath, ConstVal.UTF8);
            File file = new File(outputFile);
            if(!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                logger.debug("创建文件所在的目录失败!");
            } else {
                FileOutputStream fos = new FileOutputStream(outputFile);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
                template.merge(context, writer);
                writer.close();
                logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
            }
        }
    }

    private VelocityEngine getVelocityEngine() {
        if(this.engine == null) {
            Properties p = new Properties();
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("file.resource.loader.path", "");
            p.setProperty("UTF-8", ConstVal.UTF8);
            p.setProperty("input.encoding", ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
            this.engine = new VelocityEngine(p);
        }

        return this.engine;
    }

    private boolean isCreate(String filePath) {
        File file = new File(filePath);
        return !file.exists() || this.config.getGlobalConfig().isFileOverride();
    }

    protected void initConfig() {
        if(null == this.config) {
            this.config = new ConfigBuilder(this.packageInfo, this.dataSource, this.strategy, this.template, this.globalConfig);
            if(null != this.injectionConfig) {
                this.injectionConfig.setConfig(this.config);
            }
        }

    }

    public DataSourceConfig getDataSource() {
        return this.dataSource;
    }

    public AutoGenerator setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public StrategyConfig getStrategy() {
        return this.strategy;
    }

    public AutoGenerator setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
        return this;
    }

    public PackageConfig getPackageInfo() {
        return this.packageInfo;
    }

    public AutoGenerator setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
        return this;
    }

    public TemplateConfig getTemplate() {
        return this.template;
    }

    public AutoGenerator setTemplate(TemplateConfig template) {
        this.template = template;
        return this;
    }

    public ConfigBuilder getConfig() {
        return this.config;
    }

    public AutoGenerator setConfig(ConfigBuilder config) {
        this.config = config;
        return this;
    }

    public GlobalConfig getGlobalConfig() {
        return this.globalConfig;
    }

    public AutoGenerator setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    public InjectionConfig getCfg() {
        return this.injectionConfig;
    }

    public AutoGenerator setCfg(InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
        return this;
    }
}
