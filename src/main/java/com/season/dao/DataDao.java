package com.season.dao;

import com.season.domain.PersonData;
import com.season.utils.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by season on 2018/4/16.
 */
@Repository
public class DataDao extends BaseDao<PersonData> {

    private static final String QUERY_DATA_LIST =
            "from PersonData";
    private static final String QUERY_DATA_LIST_START =
            "from PersonData d where d.dataDate >= ?";
    private static final String QUERY_DATA_LIST_END =
            "from PersonData d where d.dataDate <= ?";
    private static final String QUERY_DATA_LIST_START_END =
            "from PersonData d where d.dataDate between ? and ?";
    private static final String DATE_FORMAT =
            "yyyy-MM-dd";


    public Page query_data_list(Integer no, Integer size,String startTime,String endTime){

        if(StringUtils.isEmpty(startTime)&&StringUtils.isEmpty(endTime)) {
            return pagedQuery(QUERY_DATA_LIST, no, size);
        }else if(!StringUtils.isEmpty(startTime)&&!StringUtils.isEmpty(endTime)){
            return pagedQuery(QUERY_DATA_LIST_START_END, no, size,
                    DateUtil.convert2Date(startTime,DATE_FORMAT), DateUtil.convert2Date(endTime,DATE_FORMAT));
        }else if(!StringUtils.isEmpty(startTime)){
            return pagedQuery(QUERY_DATA_LIST_START, no, size, DateUtil.convert2Date(startTime,DATE_FORMAT));
        }else {
            return pagedQuery(QUERY_DATA_LIST_END, no, size,  DateUtil.convert2Date(endTime,DATE_FORMAT));
        }
    }

}
