package com.season.dao;

import com.season.domain.PersonData;
import com.season.utils.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by season on 2018/4/16.
 */
@Repository
public class DataDao extends BaseDao<PersonData> {

    private static final String QUERY_DATA_LIST =
            "from PersonData";

    private static final String QUERY_DATA_FOR_DATE =
            "from PersonData d where d.dataDate = ?";
    private static final String QUERY_DATA_LIST_START =
            "from PersonData d where d.dataDate >= ?";
    private static final String QUERY_DATA_LIST_END =
            "from PersonData d where d.dataDate <= ?";
    private static final String QUERY_DATA_LIST_START_END =
            "from PersonData d where d.dataDate between ? and ?";
    private static final String DATE_FORMAT =
            "yyyy-MM-dd";
    private static final String QUERY_DATA_NEC =
            "from PersonData d where d.dataDate between ? and ? or d.dataDate between ? and ?";

    private static final String QUERY_DATA_NORMAL =
            "select count(d.actualNum)/count(*) from PersonData d where DAYOFWEEK(d.dataDate) = 1 or DAYOFWEEK(d.dataDate) = 7";
    private static final String QUERY_DATA_WEEKEND =
            "select count(d.actualNum)/count(*) from PersonData d where DAYOFWEEK(d.dataDate) != 1 and DAYOFWEEK(d.dataDate) != 7";


    public PersonData query_data_for_date(Date date) {
        List list = find(QUERY_DATA_FOR_DATE, date);
        return list.size() > 0 ? (PersonData) list.get(0) : null;
    }

    public Page query_data_nec(Integer no, Integer size, String startTime, String endTime) {

        if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
            return pagedQuery(QUERY_DATA_LIST, no, size);
        } else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            return pagedQuery(QUERY_DATA_LIST_START_END, no, size,
                    DateUtil.convert2Date(startTime, DATE_FORMAT), DateUtil.convert2Date(endTime, DATE_FORMAT));
        } else if (!StringUtils.isEmpty(startTime)) {
            return pagedQuery(QUERY_DATA_LIST_START, no, size, DateUtil.convert2Date(startTime, DATE_FORMAT));
        } else {
            return pagedQuery(QUERY_DATA_LIST_END, no, size, DateUtil.convert2Date(endTime, DATE_FORMAT));
        }
    }

    /**
     * 查询必要的数据进行预估计算
     *
     * @param count
     * @return
     */
    public List<PersonData> query_data_nec(int count) {

        Date today = new Date();
        Date startShowDate = DateUtil.addDays(today, -count + 1);
        Date startQryDate = DateUtil.addDays(startShowDate, -3);
        Date lastStartQryDate = DateUtil.addYears(startShowDate, -1);
        Date lastEndQryDate = DateUtil.addYears(DateUtil.addDays(today, 1), -1);

        return find(QUERY_DATA_NEC, startQryDate, today, lastStartQryDate, lastEndQryDate);
    }

    public double query_data_M() {
        double nomal = (double) find(QUERY_DATA_NORMAL).get(0);
        double weekend = (double) find(QUERY_DATA_WEEKEND).get(0);
        return nomal / weekend;
    }

    public static void main(String... arg) {
        Date today = new Date();
        Date startShowDate = DateUtil.addDays(today, 1);
        Date startQryDate = DateUtil.addDays(startShowDate, -3);
        TreeMap<Date, Integer> treeMap = new TreeMap<>(new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
        treeMap.put(today, 1);
        treeMap.put(startQryDate, 3);
        treeMap.put(startShowDate, 2);
        System.out.println(DateUtil.diffDay(today, new Date()));
//        for (Date date : treeMap.keySet()) {
//            System.out.println(treeMap.get(date));
//        }

//        System.out.println(treeMap.get(DateUtil.addDays(today,  1)));
//        System.out.println(treeMap.get(DateUtil.addDays(startShowDate, - 3)));
    }
}
