package com.wsw.summercloud.task.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.wsw.summercloud.common.utils.SnowflakeIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/19 14:46
 */
@SpringBootTest
public class SnowflakeIdTests {
    @Test
    void test1() {
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            System.out.println(snowflakeIdWorker.nextId());
        }
    }

    @Test
    void test2() {
        Snowflake snowflake = IdUtil.getSnowflake();
        for (int i = 0; i < 1000; i++) {
            System.out.println(snowflake.nextId());
        }
    }
}
