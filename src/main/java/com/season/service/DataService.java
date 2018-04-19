package com.season.service;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.season.cons.CommonConstant;
import com.season.dao.DataDao;
import com.season.dao.Page;
import com.season.domain.MsgBean;
import com.season.domain.MyParam;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import com.season.utils.DateUtil;
import com.season.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;

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
        PersonData personData = dataDao.query_data_for_date(data.getDataDate());
        if (personData != null)
            throw new MyException("该日期已存在");
        dataDao.save(data);

    }

    public void modify(PersonData data) {
        if (data.getActualNum() <= 0) {
            throw new MyException("信息有误");
        }
        PersonData personData = dataDao.query_data_for_date(data.getDataDate());
        if (personData != null)
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
        int showCount = 0;
        switch (kind) {
            case 0://三天
                personDatas = dataDao.query_data_nec(3);
                showCount = 4;
                break;
            case 1://一周
                personDatas = dataDao.query_data_nec(7);
                showCount = 8;
                break;
            case 2://一个月
                personDatas = dataDao.query_data_nec(30);
                showCount = 31;
                break;
            case 3://三个月
                personDatas = dataDao.query_data_nec(90);
                showCount = 91;
                break;
//            case 4://一年
//
//                break;

        }

        double M = dataDao.query_data_M();
        List<PersonData> datas;
        try {
            datas = beginCount(personDatas, M, showCount);
        } catch (MyException e) {
            return MsgBean.error().setMsg(e.getMessage());
        }

        return MsgBean.success().setData(datas);
    }

    private List<PersonData> beginCount(List<PersonData> personDatas, double M, int showCount) throws MyException{

        TreeMap<String, PersonData> treeMap = new TreeMap<>();

        final String format = "yyyy-MM-dd";

        for (PersonData personData : personDatas) {
            treeMap.put(DateUtil.convert2String(personData.getDataDate(), format), personData);
        }
        List<MyParam> myParams;
        try (BufferedInputStream bufferedInputStream = (BufferedInputStream) Resources.getResource("param.json").getContent()) {
//            byte[] bs = new byte[1024];
//            StringBuffer sb = new StringBuffer();
//            while (bufferedInputStream.read(bs) != -1) {
//                sb.append(new String(bs));
//            }

//            System.out.println("==========="+sb.toString());
            Type type = new TypeToken<ArrayList<MyParam>>() {
            }.getType();
            JsonReader jsonReader = new JsonReader(new InputStreamReader(bufferedInputStream));
            jsonReader.setLenient(true);
            myParams = new Gson().fromJson(jsonReader,type);
//            myParams = new Gson().fromJson(sb.toString(), type);
        } catch (IOException e) {
            throw new MyException("参数读取错误");
        }


        Date showEndDate = DateUtil.addDays(new Date(), 1);

        List<PersonData> result = new LinkedList<>();
        for (int i = 0; i <= showCount; i++) {
            Date date = DateUtil.addDays(showEndDate, -i);
            PersonData data = new PersonData();
            data.setDataDate(date);
            result.add(0, data);
            String curDay = DateUtil.convert2String(date, format);
            if (treeMap.containsKey(curDay)) {
                data.setActualNum(treeMap.get(curDay).getActualNum());
            }
            int num_day3 = 0, num_day2 = 0, num_day1 = 0, num_lastYear;
            //取前三天、去年当天
            Date date_lastYear = DateUtil.addYears(showEndDate, -1);
            Date date3 = DateUtil.addDays(showEndDate, -3);
            Date date2 = DateUtil.addDays(showEndDate, -2);
            String lastYear = DateUtil.convert2String(date_lastYear, format);
            Date date1 = DateUtil.addDays(showEndDate, -1);
            if (!treeMap.containsKey(lastYear)) {
                continue;//去年都没，不搞了
            }
            num_lastYear = treeMap.get(lastYear).getActualNum();
            String day3 = DateUtil.convert2String(date3, format);
            if (!treeMap.containsKey(day3)) {
                actualCount(date, data, myParams.get(0), M, date_lastYear, date3, date2, date1, num_lastYear, num_day3, num_day2, num_day1);
                continue;
            }
            num_day3 = treeMap.get(day3).getActualNum();
            String day2 = DateUtil.convert2String(date2, format);
            if (!treeMap.containsKey(day2)) {
                actualCount(date, data, myParams.get(1), M, date_lastYear, date3, date2, date1, num_lastYear, num_day3, num_day2, num_day1);
                continue;
            }
            num_day2 = treeMap.get(day2).getActualNum();
            String day1 = DateUtil.convert2String(date1, format);
            if (!treeMap.containsKey(day1)) {
                actualCount(date, data, myParams.get(2), M, date_lastYear, date3, date2, date1, num_lastYear, num_day3, num_day2, num_day1);
                continue;
            }
            num_day1 = treeMap.get(day1).getActualNum();

            actualCount(date, data, myParams.get(3), M, date_lastYear, date3, date2, date1, num_lastYear, num_day3, num_day2, num_day1);

        }

        return result;
    }

    private void actualCount(Date date, PersonData personData, MyParam myParam, double M
            , Date date_lastYear, Date date_day3, Date date_day2, Date date_day1
            , int num_lastYear, int num_day3, int num_day2, int num_day1) {

        //开始计算
        double e = DateUtil.isWeekend(date) ? 1 / M : 1;
        double a = DateUtil.isWeekend(date_day3) ? 1 / M : 1;
        double b = DateUtil.isWeekend(date_day2) ? 1 / M : 1;
        double c = DateUtil.isWeekend(date_day1) ? 1 / M : 1;
        double d = DateUtil.isWeekend(date_lastYear) ? 1 / M : 1;

        double A = myParam.getA();
        double B = myParam.getB();
        double C = myParam.getC();
        double D = myParam.getD();

        int result = (int) Math.round(e * (a * A * num_day3 + b * B * num_day2 + c * C * num_day1 + d * D * num_lastYear));
        personData.setEstimateNum(result);
    }

}
