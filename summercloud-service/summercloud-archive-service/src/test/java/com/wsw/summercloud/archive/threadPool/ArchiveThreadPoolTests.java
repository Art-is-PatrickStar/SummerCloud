package com.wsw.summercloud.archive.threadPool;

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
public class ArchiveThreadPoolTests {
    @Autowired
    @Qualifier("archiveThreadPool")
    private ThreadPoolExecutor archiveThreadPool;

    @Test
    void test() {
        for (int i = 0; i < 100; i++) {
            int temp = i;
            archiveThreadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 执行任务" + temp);
            });
        }
    }
}
