package com.season.algorithm;

import com.season.dao.DataDao;
import com.season.domain.PersonDataTmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 预估人数
 */
@Repository
public class PersonAlgorithm {

    @Autowired
    DataDao dataDao;

    // 1、创建一个单线程池
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    static List<PersonDataTmp> tasks;

    public static synchronized void addTask(PersonDataTmp dataTmp) {
        if(tasks==null){
            tasks=new LinkedList<>();
        }
        tasks.add(dataTmp);
//        executorService.submit()
    }

    public static void stop() {
        if (!executorService.isShutdown())
            executorService.shutdown();
    }


}
