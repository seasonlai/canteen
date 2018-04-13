DROP DATABASE IF EXISTS canteendb;
CREATE DATABASE canteendb DEFAULT CHARACTER SET utf8;
USE canteendb;


drop table if exists t_user;

drop table if exists t_login_log;

#
# Source for table t_login_log
#

CREATE TABLE `t_login_log` (
  `login_log_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `ip` varchar(30) NOT NULL default '',
  `login_datetime` varchar(30) NOT NULL,
  PRIMARY KEY  (`login_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Dumping data for table t_login_log
#


#
# Source for table t_user
#

CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL auto_increment COMMENT '用户Id',
  `user_name` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(30) NOT NULL default '' COMMENT '密码',
  `user_type` tinyint(4) NOT NULL default '1' COMMENT '1:普通用户 2:管理员',
  `locked` tinyint(4) NOT NULL default '0' COMMENT '0:未锁定 1:锁定',
  `id_card_img` varchar(100) default NULL COMMENT '学生证图片路径',
  `last_visit` datetime default NULL COMMENT '最后登陆时间',
  `last_ip` varchar(20) default NULL COMMENT '最后登陆IP',
  PRIMARY KEY  (`user_id`),
  KEY `AK_AK_USER_USER_NAME` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Dumping data for table t_user
#

INSERT INTO `t_user` VALUES (1,'root','root',2,0,NULL,NULL,NULL);