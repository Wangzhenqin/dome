DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_type` int(10) NOT NULL COMMENT '用户类型',
  `name` VARCHAR(20) NOT NULL COMMENT '名字',
  `code` VARCHAR(32) NOT NULL COMMENT '密码',
  `faculty_id` int(10) NOT NULL COMMENT '系id',
  `phone` int(16) NOT NULL COMMENT '手机号码',
  `mail` VARCHAR(32) NOT NULL COMMENT '邮箱',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0,未删除; 1,已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_oi` (`user_id`),
  INDEX `idx_f` (`faculty_id`),
  INDEX `idx_t` (`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_faculty`;
CREATE TABLE `t_faculty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `faculty_id` int(10) NOT NULL COMMENT '系id',
  `faculty_name` VARCHAR(20) NOT NULL COMMENT '系名字',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0,未删除; 1,已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_oi` (`faculty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_title`;
CREATE TABLE `t_title` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title_id` bigint(20) NOT NULL COMMENT '题目id',
  `title_name` VARCHAR(32) NOT NULL COMMENT '题目名字',
  `faculty_id` int(10) NOT NULL COMMENT '系id',
  `teacher_id` bigint(20) NOT NULL COMMENT '出题人id',
  `student_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '选题人id',
  `status` int(10) NOT NULL COMMENT '题目状态:0,未被选择;1,等待教师确认;2,学生选择成功',
  `task` VARCHAR(64) COMMENT '任务书',
  `content` VARCHAR(512) NOT NULL COMMENT '题目内容',
  `status_report` VARCHAR(64) COMMENT '开题报告',
  `paper` VARCHAR(64) COMMENT '论文',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0,未删除; 1,已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_oi` (`title_id`),
  UNIQUE INDEX `uniq_oi` (`student_id`,`teacher_id`),
  INDEX `idx_s` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_time`;
CREATE TABLE `t_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `student_id` bigint(20) NOT NULL COMMENT '学生ID',
  `select_title_time` bigint(20) NOT NULL COMMENT '选题时间',
  `status_reply_time` bigint(20) COMMENT '开题报告提交时间',
  `paper_time` bigint(20) COMMENT '论文提交时间',
  `grade_time` bigint(20) COMMENT '成绩提交时间',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0,未删除; 1,已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uniq_oi` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `detail` VARCHAR(256) NOT NULL COMMENT '详情',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除: 0,未删除; 1,已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;