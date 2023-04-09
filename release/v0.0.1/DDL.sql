CREATE TABLE `resource_info`
(
    `resource_id`    bigint NOT NULL COMMENT '资源id',
    `data`           json        DEFAULT NULL COMMENT '数据',
    `enable_type`    int         DEFAULT '1' COMMENT '是否有效 1:是 0:否',
    `archive_status` int         DEFAULT '0' COMMENT '归档状态 0:待处理 1:已处理',
    `created_time`   datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '资源入库时间',
    `updated_time`   datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '资源修改时间',
    PRIMARY KEY (`resource_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_20230401`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `job_status`   int         DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint     DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint     DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_20230501`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `job_status`   int         DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint     DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint     DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230401`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `job_status`   int         DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint     DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230501`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `job_status`   int         DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint     DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;