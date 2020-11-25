/*
Navicat MySQL Data Transfer

Source Server         : 111
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : myssp

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-10-29 15:30:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pssword` varchar(255) DEFAULT NULL,
  `telep` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '上海', '小华', '123', '18046315891');
