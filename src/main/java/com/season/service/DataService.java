package com.season.service;

import com.season.dao.DataDao;
import com.season.dao.Page;
import com.season.domain.MsgBean;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        dataDao.update(data);
    }


    public Page queryDataList(Integer no, Integer size, String startTime, String endTime) {
        return dataDao.query_data_list(no, size, startTime, endTime);
    }

    public MsgBean delData(List<PersonData> dataList) {
        dataDao.remove(dataList);
        return MsgBean.success();
    }
}
