package com.season.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.season.cons.CommonConstant;
import com.season.dao.DataDao;
import com.season.dao.Page;
import com.season.domain.MsgBean;
import com.season.domain.MyParam;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import com.season.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by season on 2018/4/16.
 */
@Service
public class DataService {

    @Autowired
    DataDao dataDao;

    public void add(PersonData data) {
        if (data.getActualNum() <= 0) {
            throw new MyException("信息有误");
        }
        dataDao.save(data);

    }

    public void modify(PersonData data) {
        if (data.getActualNum() <= 0) {
            throw new MyException("信息有误");
        }
        PersonData personData = dataDao.query_data_for_date(data.getDataDate());
        if(personData!=null)
            throw new MyException("该日期已存在");
        dataDao.update(data);
    }


    public Page queryDataList(Integer no, Integer size, String startTime, String endTime) {
        return dataDao.query_data_nec(no, size, startTime, endTime);
    }

    public MsgBean delData(List<PersonData> dataList) {
        dataDao.remove(dataList);
        return MsgBean.success();
    }

    public MsgBean countData(int kind) {
        if (kind < 0 || kind >= CommonConstant.PERSON_COUNT.length) {
            return new MsgBean().setCode(-1).setMsg("不支持的统计类型");
        }
        List<PersonData> personDatas = null;
        switch (kind) {
            case 0://三天
                personDatas = dataDao.query_data_nec(3);
                break;
            case 1://一周

                break;
            case 2://一个月

                break;
            case 3://三个月

                break;
//            case 4://一年
//
//                break;

        }

        double M = dataDao.query_data_M();

        try {
            beginCount(personDatas, M);
        }catch (MyException e){
            return MsgBean.error().setMsg(e.getMessage());
        }

        return MsgBean.success().setData(personDatas);
    }

    private void beginCount(List<PersonData> personDatas, double M) {

        TreeMap<Date,PersonData> treeMap = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });

        for (PersonData personData : personDatas) {
            treeMap.put(personData.getDataDate(),personData);
        }
        MyParam myParam;
        try ( BufferedInputStream bufferedInputStream = (BufferedInputStream) Resources.getResource("test/test.txt").getContent()){
            byte[] bs = new byte[1024];
            StringBuffer sb = new StringBuffer();
            while (bufferedInputStream.read(bs) != -1) {
                sb.append(new String(bs));
            }
            myParam =  new ObjectMapper().readValue(sb.toString(), MyParam.class);
        } catch (IOException e) {
            throw new MyException("参数读取错误");
        }



    }
}
