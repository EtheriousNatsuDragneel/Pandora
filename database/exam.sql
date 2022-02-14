/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-12-10 09:24:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_classes
-- ----------------------------
DROP TABLE IF EXISTS `t_classes`;
CREATE TABLE `t_classes` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `deleted` char(1) DEFAULT NULL,
  `create_user_name` varchar(255) DEFAULT NULL,
  `classes_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classes
-- ----------------------------

-- ----------------------------
-- Table structure for t_classes_user
-- ----------------------------
DROP TABLE IF EXISTS `t_classes_user`;
CREATE TABLE `t_classes_user` (
  `id` bigint(20) NOT NULL,
  `classes_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classes_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_paper`;
CREATE TABLE `t_exam_paper` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `paper_type` int(11) DEFAULT NULL,
  `grade_level` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `question_count` int(11) DEFAULT NULL,
  `suggest_time` int(11) DEFAULT NULL,
  `task_exam_id` int(11) DEFAULT NULL,
  `limit_start_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `limit_end_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `frame_varchar(255)_content_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exam_paper
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_paper_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_paper_answer`;
CREATE TABLE `t_exam_paper_answer` (
  `id` bigint(20) NOT NULL,
  `exam_paper_id` int(11) DEFAULT NULL,
  `paper_name` varchar(255) DEFAULT NULL,
  `paper_type` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `system_score` int(11) DEFAULT NULL,
  `user_score` int(11) DEFAULT NULL,
  `paper_score` int(11) DEFAULT NULL,
  `question_correct` int(11) DEFAULT NULL,
  `question_count` int(11) DEFAULT NULL,
  `do_time` int(11) DEFAULT NULL,
  `task_exam_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exam_paper_answer
-- ----------------------------

-- ----------------------------
-- Table structure for t_exam_paper_question_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_paper_question_customer_answer`;
CREATE TABLE `t_exam_paper_question_customer_answer` (
  `id` bigint(20) NOT NULL,
  `question_id` int(11) DEFAULT NULL,
  `exam_paper_id` int(11) DEFAULT NULL,
  `exam_paper_answer_id` int(11) DEFAULT NULL,
  `question_type` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `customer_score` int(11) DEFAULT NULL,
  `question_score` int(11) DEFAULT NULL,
  `question_varchar(255)_content_id` int(11) DEFAULT NULL,
  `answer` varchar(255) DEFAULT NULL,
  `varchar(255)_content_id` int(11) DEFAULT NULL,
  `do_right` char(1) DEFAULT NULL,
  `item_order` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_exam_paper_question_customer_answer
-- ----------------------------

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `send_user_id` int(11) DEFAULT NULL,
  `send_user_name` varchar(255) DEFAULT NULL,
  `send_real_name` varchar(255) DEFAULT NULL,
  `read_count` int(11) DEFAULT NULL,
  `receive_user_count` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_message_user
-- ----------------------------
DROP TABLE IF EXISTS `t_message_user`;
CREATE TABLE `t_message_user` (
  `id` bigint(20) NOT NULL,
  `message_id` int(11) DEFAULT NULL,
  `receive_user_id` int(11) DEFAULT NULL,
  `receive_user_name` varchar(255) DEFAULT NULL,
  `receive_real_name` varchar(255) DEFAULT NULL,
  `readed` char(1) DEFAULT NULL,
  `read_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `create_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` bigint(20) NOT NULL,
  `question_type` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `grade_level` int(11) DEFAULT NULL,
  `difficult` int(11) DEFAULT NULL,
  `correct` varchar(255) DEFAULT NULL,
  `info_varchar(255)_content_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `level_name` varchar(255) DEFAULT NULL,
  `item_order` int(11) DEFAULT NULL,
  `deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', '语文', '1', '一年级', null, 'f');
INSERT INTO `t_subject` VALUES ('2', '数学', '1', '一年级', null, 'f');
INSERT INTO `t_subject` VALUES ('3', '英语', '1', '一年级', null, 'f');
INSERT INTO `t_subject` VALUES ('4', '语文', '2', '二年级', null, 'f');
INSERT INTO `t_subject` VALUES ('5', '数学', '2', '二年级', null, 'f');
INSERT INTO `t_subject` VALUES ('6', '英语', '2', '二年级', null, 'f');
INSERT INTO `t_subject` VALUES ('7', '语文', '3', '三年级', null, 'f');
INSERT INTO `t_subject` VALUES ('8', '数学', '3', '三年级', null, 'f');
INSERT INTO `t_subject` VALUES ('9', '英语', '3', '三年级', null, 'f');
INSERT INTO `t_subject` VALUES ('10', '语文', '4', '四年级', null, 'f');
INSERT INTO `t_subject` VALUES ('11', '数学', '4', '四年级', null, 'f');
INSERT INTO `t_subject` VALUES ('12', '英语', '4', '四年级', null, 'f');
INSERT INTO `t_subject` VALUES ('13', '语文', '5', '五年级', null, 'f');
INSERT INTO `t_subject` VALUES ('14', '数学', '5', '五年级', null, 'f');
INSERT INTO `t_subject` VALUES ('15', '英语', '5', '五年级', null, 'f');
INSERT INTO `t_subject` VALUES ('16', '语文', '6', '六年级', null, 'f');
INSERT INTO `t_subject` VALUES ('17', '数学', '6', '六年级', null, 'f');
INSERT INTO `t_subject` VALUES ('18', '英语', '6', '六年级', null, 'f');
INSERT INTO `t_subject` VALUES ('19', '语文', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('20', '数学', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('21', '英语', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('22', '政治', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('23', '历史', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('24', '地理', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('25', '生物', '7', '初一', null, 'f');
INSERT INTO `t_subject` VALUES ('26', '语文', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('27', '数学', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('28', '英语', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('29', '政治', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('30', '历史', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('31', '地理', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('32', '生物', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('33', '物理', '8', '初二', null, 'f');
INSERT INTO `t_subject` VALUES ('34', '语文', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('35', '数学', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('36', '英语', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('37', '政治', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('38', '历史', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('39', '物理', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('40', '化学', '9', '初三', null, 'f');
INSERT INTO `t_subject` VALUES ('41', '语文', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('42', '数学', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('43', '英语', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('44', '历史', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('45', '政治', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('46', '地理', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('47', '化学', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('48', '物理', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('49', '生物', '10', '高一', null, 'f');
INSERT INTO `t_subject` VALUES ('50', '语文', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('51', '数学', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('52', '英语', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('53', '历史', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('54', '政治', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('55', '地理', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('56', '化学', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('57', '物理', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('58', '生物', '11', '高二', null, 'f');
INSERT INTO `t_subject` VALUES ('59', '语文', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('60', '数学', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('61', '英语', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('62', '历史', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('63', '政治', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('64', '地理', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('65', '化学', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('66', '物理', '12', '高三', null, 'f');
INSERT INTO `t_subject` VALUES ('67', '生物', '12', '高三', null, 'f');

-- ----------------------------
-- Table structure for t_task_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_task_exam`;
CREATE TABLE `t_task_exam` (
  `id` bigint(20) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `grade_level` int(11) DEFAULT NULL,
  `frame_varchar(255)_content_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_user_name` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `deleted` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task_exam
-- ----------------------------

-- ----------------------------
-- Table structure for t_task_exam_customer_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_task_exam_customer_answer`;
CREATE TABLE `t_task_exam_customer_answer` (
  `id` bigint(20) NOT NULL,
  `task_exam_id` int(11) DEFAULT NULL,
  `varchar(255)_content_id` int(11) DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task_exam_customer_answer
-- ----------------------------

-- ----------------------------
-- Table structure for t_text_content
-- ----------------------------
DROP TABLE IF EXISTS `t_text_content`;
CREATE TABLE `t_text_content` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_text_content
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `user_uuid` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `birth_day` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `user_level` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `modify_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `last_active_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `deleted` char(1) DEFAULT NULL,
  `wx_open_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'd2d29da2-dcb3-4013-b874-727626236f47', 'student', 'i+/ukCFsMYsIwiSNW1JVXMUCSHe1bugya5u/XagGlja5uEQyBNyeBJCZpQZz0ebdHCQI1NDdgrXYKISyrauwLh3aO9IGRqqjDN+4S8ogAob3Yu85TEmCIaX0OpSkn0sj3+ulfGCC+C+ugVbZlvQ/EjWxio1UdiOf7KXlHHsYMGE=', '学生', '17', '1', '2019-12-10 09:14:56.494618', '12', null, '1', '1', null, '2019-07-16 15:13:02.000000', '2019-07-16 15:13:10.000000', '2019-07-16 15:13:13.000000', 'f', null);
INSERT INTO `t_user` VALUES ('2', '52045f5f-a13f-4ccc-93dd-f7ee8270ad4c', 'admin', 'D1AGFL+Gx37t0NPG4d6biYP5Z31cNbwhK5w1lUeiHB2zagqbk8efYfSjYoh1Z/j1dkiRjHU+b0EpwzCh8IGsksJjzD65ci5LsnodQVf4Uj6D3pwoscXGqmkjjpzvSJbx42swwNTA+QoDU8YLo7JhtbUK2X0qCjFGpd+8eJ5BGvk=', '管理员', '30', '1', '2019-12-10 09:14:56.562760', null, null, '3', '1', null, '2019-07-16 15:14:12.000000', '2019-07-16 15:14:15.000000', '2019-07-16 15:14:17.000000', 'f', null);

-- ----------------------------
-- Table structure for t_user_event_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_event_log`;
CREATE TABLE `t_user_event_log` (
  `id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_event_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_user_token`;
CREATE TABLE `t_user_token` (
  `id` bigint(20) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `wx_open_id` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `end_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_token
-- ----------------------------
