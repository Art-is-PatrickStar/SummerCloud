package com.wsw.summercloud.task.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: wangsongwen
 * @Date: 2023/4/9 21:21
 */
@Slf4j
@Component
public class SendTaskMsgJob {
    @XxlJob("sendTaskMsgJobHandler")
    public void sendTaskMsgJobHandler() throws Exception {
        XxlJobHelper.log("sendTaskMsgJobHandler start.");
        for (int i = 0; i < 5; i++) {
            XxlJobHelper.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        XxlJobHelper.log("sendTaskMsgJobHandler end.");
    }
}
