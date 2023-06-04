package com.wsw.summercloud.task.threadPool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/6/4 16:41
 */
@SpringBootTest
public class TaskThreadPoolTests {
    @Autowired
    @Qualifier("taskThreadPool")
    private ThreadPoolExecutor taskThreadPool;

    @Test
    void test() {
        for (int i = 0; i < 100; i++) {
            int temp = i;
            taskThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 执行任务" + temp);
            });
        }
    }
}
