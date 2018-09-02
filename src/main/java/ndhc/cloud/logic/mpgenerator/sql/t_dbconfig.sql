/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50637
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-09-02 19:07:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dbconfig
-- ----------------------------
DROP TABLE IF EXISTS `t_dbconfig`;
CREATE TABLE `t_dbconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT '' COMMENT '项目名称',
  `config_json` text COMMENT '项目配置都在这一个字段',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dbconfig
-- ----------------------------
INSERT INTO `t_dbconfig` VALUES ('1', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 00:00:00');
INSERT INTO `t_dbconfig` VALUES ('2', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 00:00:00');
INSERT INTO `t_dbconfig` VALUES ('3', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 00:00:00');
INSERT INTO `t_dbconfig` VALUES ('4', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 00:00:00');
INSERT INTO `t_dbconfig` VALUES ('5', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:14:10');
INSERT INTO `t_dbconfig` VALUES ('18', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:37:38');
INSERT INTO `t_dbconfig` VALUES ('19', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:41:31');
INSERT INTO `t_dbconfig` VALUES ('20', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:42:21');
INSERT INTO `t_dbconfig` VALUES ('21', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:45:09');
INSERT INTO `t_dbconfig` VALUES ('22', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:46:07');
INSERT INTO `t_dbconfig` VALUES ('23', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:46:30');
INSERT INTO `t_dbconfig` VALUES ('24', 'hello,world', 'username:root,password:123456,packName:com.hello.world', '2018-08-30 15:49:24');
INSERT INTO `t_dbconfig` VALUES ('25', 'it', '{\"name\":\"Java\",\"id\":1,\"ide\":\"Eclipse\"}', '2018-08-30 17:58:33');
INSERT INTO `t_dbconfig` VALUES ('26', 'it', '{\"name\":\"Java\",\"ide\":\"Eclipse\",\"packName\":\"1\"}', '2018-08-30 18:16:11');
INSERT INTO `t_dbconfig` VALUES ('27', 'it', '{\"packName1\":\"1\",\"name\":\"Java\",\"ide\":\"Eclipse\"}', '2018-08-30 18:16:52');
INSERT INTO `t_dbconfig` VALUES ('28', 'it', '{\"packName1\":\"1\",\"name\":\"Java\",\"ide\":\"Eclipse\"}', '2018-08-30 18:17:00');
INSERT INTO `t_dbconfig` VALUES ('29', 'it', '{\"packName1\":\"1\",\"name\":\"Java\",\"ide\":\"Eclipse\"}', '2018-08-30 18:17:07');
INSERT INTO `t_dbconfig` VALUES ('38', 'ios', '{\"superEntityColumns\":\"id,create_time,update_time\",\"baseResultMap\":true,\"author\":\"yangnian\",\"baseColumnList\":true,\"ip\":\"127.0.0.1\",\"dbType\":\"mysql\",\"superMapperClass\":\"com.baomidou.mybatisplus.mapper.BaseMapper\",\"fileOverride\":true,\"dbUserName\":\"root\",\"superControllerClass\":\"com.hello.framework.controller.BaseController\",\"outDir\":\"C:\\\\Users\\\\76708\\\\Desktop\\\\newhope\",\"activeRecord\":true,\"port\":\"3306\",\"tablePrefix\":\"\",\"driverName\":\"com.mysql.jdbc.Driver\",\"superServiceClass\":\"com.hello.base.BaseService\",\"excludeTable\":\"admin_role,role_menu,act_*,admin,admin_attribute,admin_attribute_option,log,organization,organization_admin,resource\",\"dataBaseName\":\"mydb\",\"packageName\":\"com.hello\",\"superServiceImplClass\":\"com.hello.base.BaseServiceImp\",\"enableCache\":true,\"dbUrl\":\"127.0.0.1\",\"superEntityClass\":\"com.hello.base.BaseEntity\",\"dbPassword\":\"123456\"}', '2018-09-01 11:24:11');
