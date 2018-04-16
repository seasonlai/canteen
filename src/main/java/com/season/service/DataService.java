package com.season.service;

import com.season.dao.DataDao;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by season on 2018/4/16.
 */
@Service
public class DataService {

    @Autowired
    DataDao dataDao;

    public void add(PersonData data){
        if(data.getActualNum()<=0){
            throw new MyException("信息有误");
        }
        dataDao.save(data);

    }

    public void modify(PersonData data){
        if(data.getActualNum()<=0){
            throw new MyException("信息有误");
        }
        dataDao.update(data);
    }


}
