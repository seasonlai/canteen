package com.season.algorithm;

import com.season.dao.DataDao;
import com.season.dao.DataTmpDao;
import com.season.domain.PersonDataTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 预估人数
 */
public class PersonAlgorithm {

    static DataDao dataDao;
    static DataTmpDao dataTmpDao;

    static volatile boolean hasData;

    static boolean isStart;

    static ThreadPoolExecutor threadPoolExecutor;

    public static void start() {
        dataDao = new DataDao();
        dataTmpDao = new DataTmpDao();
        threadPoolExecutor = new ThreadPoolExecutor(3, 8,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new MyRejectHandler());

        isStart = true;
//        List<PersonDataTmp> data = dataTmpDao.getData(8);
//        for (PersonDataTmp dataTmp : data) {
//            threadPoolExecutor.execute(new MyTask(dataTmp,dataDao,dataTmpDao));
//        }
//        hasData = data.size() >= 8;
    }

    public static void stop() {
        if (!isStart)
            return;
        if (!threadPoolExecutor.isShutdown())
            threadPoolExecutor.shutdownNow();
        isStart = false;
    }

    public static synchronized void addTask(PersonDataTmp dataTmp) {
        if (!isStart)
            throw new RuntimeException("PersonAlgorithm had not start");
        threadPoolExecutor.execute(new MyTask(dataTmp, dataDao, dataTmpDao));
    }

    static class MyTask implements Runnable {

        PersonDataTmp dataTmp;
        DataDao dataDao;
        DataTmpDao dataTmpDao;

        public MyTask(PersonDataTmp dataTmp, DataDao dataDao, DataTmpDao dataTmpDao) {
            this.dataTmp = dataTmp;
            this.dataDao = dataDao;
            this.dataTmpDao = dataTmpDao;
        }

        @Override
        public void run() {

            //查当前日期的前两天+



//            threadPoolExecutor.get
        }
    }

    static class MyRejectHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            MyTask task = (MyTask) r;
            task.dataTmpDao.save(task.dataTmp);
        }
    }


}
