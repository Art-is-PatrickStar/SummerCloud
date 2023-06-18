CREATE TABLE `resource_info`
(
    `resource_id`    bigint NOT NULL COMMENT '资源id',
    `data`           json DEFAULT NULL COMMENT '数据',
    `enable_type`    int  DEFAULT '1' COMMENT '是否有效 1:是 0:否',
    `archive_status` int  DEFAULT '0' COMMENT '归档状态 0:待处理 1:已处理',
    `created_time`   datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '资源入库时间',
    `updated_time`   datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '资源修改时间',
    PRIMARY KEY (`resource_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `archive_node`
(
    `archive_id`   bigint NOT NULL COMMENT '归档id',
    `archive_rule` varchar(2000) DEFAULT '' COMMENT '归档规则',
    `enable`       int           DEFAULT '0' COMMENT '是否启用 1:是 0:否',
    `is_delete`    int           DEFAULT '0' COMMENT '是否删除 1:是 0:否',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '归档节点创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '归档节点修改时间',
    PRIMARY KEY (`archive_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `user_info`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`     varchar(50)  DEFAULT '' COMMENT '用户名',
    `password`     varchar(200) DEFAULT '' COMMENT '密码',
    `email`        varchar(50)  DEFAULT '' COMMENT '邮箱',
    `is_delete`    int          DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '用户信息创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '用户信息修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `task_job_20230401`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `archive_id`   bigint NOT NULL COMMENT '归档id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_20230501`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `archive_id`   bigint NOT NULL COMMENT '归档id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_20230601`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `archive_id`   bigint NOT NULL COMMENT '归档id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_20230701`
(
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `resource_id`  bigint NOT NULL COMMENT '资源id',
    `archive_id`   bigint NOT NULL COMMENT '归档id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_lock`      tinyint DEFAULT '0' COMMENT '是否已上锁占用 0:否 1:是',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务修改时间',
    PRIMARY KEY (`job_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230401`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230501`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230601`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;

CREATE TABLE `task_job_record_20230701`
(
    `record_id`    bigint NOT NULL COMMENT '任务记录id',
    `job_id`       bigint NOT NULL COMMENT '任务id',
    `job_status`   int     DEFAULT '1' COMMENT '任务状态 1:待处理 2:处理中 3:处理完成 4:不处理',
    `is_delete`    tinyint DEFAULT '0' COMMENT '是否逻辑删除 0:否 1:是',
    `created_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '任务记录创建时间',
    `updated_time` datetime(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '任务记录修改时间',
    PRIMARY KEY (`record_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;