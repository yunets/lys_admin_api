
-- 密码QQww@1122


DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) NOT NULL COMMENT '姓名',
  `loginname` varchar(50) NOT NULL COMMENT '登陆帐号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status_flag` varchar(10) DEFAULT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user_id` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';