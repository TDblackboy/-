/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : hexagon

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2019-06-13 16:19:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dredgepersonnel
-- ----------------------------
DROP TABLE IF EXISTS `dredgepersonnel`;
CREATE TABLE `dredgepersonnel` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dredgepersonnel
-- ----------------------------
INSERT INTO `dredgepersonnel` VALUES ('183', '飞段', '1593211544', '基教一层103', '0');
INSERT INTO `dredgepersonnel` VALUES ('219', '奈良', '1316958474', '基教一层106', '1');

-- ----------------------------
-- Table structure for exceptiondata
-- ----------------------------
DROP TABLE IF EXISTS `exceptiondata`;
CREATE TABLE `exceptiondata` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `persons` varchar(255) DEFAULT NULL,
  `datatime` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exceptiondata
-- ----------------------------
INSERT INTO `exceptiondata` VALUES ('00000000062', '基教一层112', '8', '2019-1-23', '0');
INSERT INTO `exceptiondata` VALUES ('00000000063', '基教一层112', '4', '2019-4-24', '0');
INSERT INTO `exceptiondata` VALUES ('00000000064', '基教一层106', '3', '2019-9-28', '0');
INSERT INTO `exceptiondata` VALUES ('00000000065', '基教一层111', '13', '2019-8-30', '1');
INSERT INTO `exceptiondata` VALUES ('00000000066', '基教一层111', '0', '2019-7-1', '0');
INSERT INTO `exceptiondata` VALUES ('00000000067', '基教一层112', '14', '2019-6-26', '1');
INSERT INTO `exceptiondata` VALUES ('00000000068', '基教一层111', '0', '2019-12-21', '0');
INSERT INTO `exceptiondata` VALUES ('00000000069', '基教一层111', '11', '2019-12-1', '1');
INSERT INTO `exceptiondata` VALUES ('00000000070', '基教一层112', '7', '2019-4-6', '0');
INSERT INTO `exceptiondata` VALUES ('00000000071', '基教一层112', '18', '2019-12-11', '1');
INSERT INTO `exceptiondata` VALUES ('00000000072', '基教一层112', '2', '2019-10-25', '0');
INSERT INTO `exceptiondata` VALUES ('00000000073', '基教一层112', '15', '2019-2-1', '1');
INSERT INTO `exceptiondata` VALUES ('00000000074', '基教一层106', '15', '2019-1-26', '1');
INSERT INTO `exceptiondata` VALUES ('00000000075', '基教一层112', '12', '2019-8-2', '1');
INSERT INTO `exceptiondata` VALUES ('00000000076', '基教一层111', '2', '2019-3-11', '0');
INSERT INTO `exceptiondata` VALUES ('00000000077', '基教一层111', '7', '2019-8-26', '0');
INSERT INTO `exceptiondata` VALUES ('00000000078', '基教一层106', '15', '2019-1-5', '1');
INSERT INTO `exceptiondata` VALUES ('00000000079', '基教一层111', '11', '2019-11-27', '1');
INSERT INTO `exceptiondata` VALUES ('00000000080', '基教一层111', '1', '2019-2-21', '0');
INSERT INTO `exceptiondata` VALUES ('00000000081', '基教一层112', '0', '2019-4-11', '0');

-- ----------------------------
-- Table structure for realtime
-- ----------------------------
DROP TABLE IF EXISTS `realtime`;
CREATE TABLE `realtime` (
  `id` int(11) NOT NULL,
  `loaction` varchar(255) DEFAULT NULL,
  `persons` varchar(255) DEFAULT NULL,
  `datatime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of realtime
-- ----------------------------
