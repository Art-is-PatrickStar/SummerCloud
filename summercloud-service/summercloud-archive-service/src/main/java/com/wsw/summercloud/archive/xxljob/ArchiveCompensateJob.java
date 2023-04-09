package com.wsw.summercloud.archive.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 22:11
 */
@Slf4j
@Component
public class ArchiveCompensateJob {
    @XxlJob("archiveCompensateJobHandler")
    public void archiveCompensateJobHandler() throws Exception {
        XxlJobHelper.log("archiveCompensateJob start.");
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        XxlJobHelper.log("archiveCompensateJob end.");
    }
}
