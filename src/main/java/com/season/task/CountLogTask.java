package com.season.task;

import com.season.utils.DateUtil;
import com.season.utils.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Wellhope on 2018/4/21.
 */
public class CountLogTask extends QuartzJobBean {

    static Logger logger = Logger.getLogger(CountLogTask.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        delLogFile();
    }

    private void delLogFile() {
        File d = MyFileUtil.getServerFile("/count_log");

        if (!d.exists())
            return;

        try {
            FileUtils.deleteDirectory(d);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
    }
}
