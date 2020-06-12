# wwzq
你的男朋友女朋友
CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `p_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `category` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '类别',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `value` varchar(100) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '值',
  `remark` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
INSERT INTO `wwzq`.`dict`(`id`, `p_id`, `category`, `name`, `value`, `remark`) VALUES (1, 0, '小程序配置', 'appid', 'wxd8e954dce20c1a51', '');
INSERT INTO `wwzq`.`dict`(`id`, `p_id`, `category`, `name`, `value`, `remark`) VALUES (2, 0, '小程序配置', 'secret', 'ceb9eddc0bda4105976ed43650b36662', '');
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信用户id',
  `name` varchar(80) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '真实姓名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `phone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电话',
  `openid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '应用id',
  `avatar_url` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `country` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '国家',
  `gender` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别',
  `language` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '语言',
  `province` varchar(255) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `create_by_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';