package com.season.dao;

import com.season.domain.PersonData;
import com.season.utils.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * Created by season on 2018/4/16.
 */
@Repository
public class DataDao extends BaseDao<PersonData> {

    private static final String QUERY_DATA_LIST =
            "from PersonData d";

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
            "from PersonData d where (d.dataDate between ? and ?) or (d.dataDate between ? and ?)";

    private static final String QUERY_DATA_WEEKEND =
            "select sum(d.actual_num)/count(*) from t_person_data d where DAYOFWEEK(d.data_date) = 1 or DAYOFWEEK(d.data_date) = 7";
    private static final String QUERY_DATA_NORMAL =
            "select sum(d.actual_num)/count(*) from t_person_data d where DAYOFWEEK(d.data_date) != 1 and DAYOFWEEK(d.data_date) != 7";


    public PersonData query_data_for_date(Date date) {
        List list = find(QUERY_DATA_FOR_DATE, date);
        return list.size() > 0 ? (PersonData) list.get(0) : null;
    }

    public Page query_data_nec(Integer no, Integer size, String startTime, String endTime,Integer sortKind) {

        String  orderBy;
        if(sortKind==null||sortKind==0){//升序
            orderBy = " order by d.dataDate asc";
        }else {
            //降序
            orderBy = " order by d.dataDate desc";
        }

        if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
            return pagedQuery(QUERY_DATA_LIST+orderBy, no, size);
        } else if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            return pagedQuery(QUERY_DATA_LIST_START_END+orderBy, no, size,
                    DateUtil.convert2Date(startTime, DATE_FORMAT), DateUtil.convert2Date(endTime, DATE_FORMAT));
        } else if (!StringUtils.isEmpty(startTime)) {
            return pagedQuery(QUERY_DATA_LIST_START+orderBy, no, size, DateUtil.convert2Date(startTime, DATE_FORMAT));
        } else {
            return pagedQuery(QUERY_DATA_LIST_END+orderBy,no, size, DateUtil.convert2Date(endTime, DATE_FORMAT));
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
        List nomalList = runSql(QUERY_DATA_NORMAL);
        BigDecimal nomal = nomalList.get(0) != null ? (BigDecimal) nomalList.get(0) : new BigDecimal(0);
        List weekList = runSql(QUERY_DATA_WEEKEND);
        BigDecimal weekend = weekList.get(0) != null ? (BigDecimal) weekList.get(0) : new BigDecimal(1);

        return nomal.divide(weekend,10,ROUND_HALF_DOWN).doubleValue();
    }

}
