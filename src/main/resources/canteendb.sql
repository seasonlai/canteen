DROP DATABASE IF EXISTS canteendb;
CREATE DATABASE canteendb
  DEFAULT CHARACTER SET utf8;
USE canteendb;


DROP TABLE IF EXISTS t_user;

DROP TABLE IF EXISTS t_login_log;

#
# Source for table t_login_log
#

CREATE TABLE `t_login_log` (
  `login_log_id`   INT(11)     NOT NULL AUTO_INCREMENT,
  `user_id`        INT(11)              DEFAULT NULL,
  `ip`             VARCHAR(30) NOT NULL DEFAULT '',
  `login_datetime` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`login_log_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

#
# Dumping data for table t_login_log
#


#
# Source for table t_user
#

CREATE TABLE `t_user` (
  `user_id`     INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '用户Id',
  `user_name`   VARCHAR(30) NOT NULL
  COMMENT '用户名',
  `password`    VARCHAR(30) NOT NULL DEFAULT ''
  COMMENT '密码',
  `user_type`   TINYINT(4)  NOT NULL DEFAULT '1'
  COMMENT '1:普通用户 2:管理员',
  `locked`      TINYINT(4)  NOT NULL DEFAULT '0'
  COMMENT '0:未锁定 1:锁定',
  `id_card_img` VARCHAR(100)         DEFAULT NULL
  COMMENT '学生证图片路径',
  `last_visit`  DATETIME             DEFAULT NULL
  COMMENT '最后登陆时间',
  `last_ip`     VARCHAR(20)          DEFAULT NULL
  COMMENT '最后登陆IP',
  PRIMARY KEY (`user_id`),
  KEY `AK_AK_USER_USER_NAME` (`user_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

#
# Dumping data for table t_user
#

INSERT INTO `t_user` VALUES (1, 'root', 'root', 2, 0, NULL, NULL, NULL);

#
# Source for table t_food
#

CREATE TABLE `t_food` (
  `food_id`      INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '餐品Id',
  `food_name`    VARCHAR(30) NOT NULL
  COMMENT '餐品名',
  `food_price`   VARCHAR(30) NOT NULL
  COMMENT '餐品价格',
  `id_card_img`  VARCHAR(100)         DEFAULT NULL
  COMMENT '学生证图片路径',
  `publish_date` DATETIME             DEFAULT NULL
  COMMENT '发布时间',
  `kind_code`    INT(11)              DEFAULT NULL
  COMMENT '餐品类别',
  PRIMARY KEY (`food_id`),
  KEY `AK_AK_USER_USER_NAME` (`food_name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

#
# Source for table t_food_kind
#

CREATE TABLE `t_food_kind` (
  `kind_code`   INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '类别Id',
  `kind_name`   VARCHAR(30) NOT NULL
  COMMENT '类别名',
  `parent_code` INT(11)              DEFAULT NULL
  COMMENT '父类别',
  PRIMARY KEY (`kind_code`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

INSERT INTO `t_food_kind` VALUES (1, '肉食类', NULL);
INSERT INTO `t_food_kind` VALUES (2, '蔬菜类', NULL);
INSERT INTO `t_food_kind` VALUES (3, '粉面类', NULL);
INSERT INTO `t_food_kind` VALUES (4, '汤品类', NULL);
INSERT INTO `t_food_kind` VALUES (5, '甜品类', NULL);
