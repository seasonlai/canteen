package com.season.dao;

import com.season.domain.PersonDataTmp;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by season on 2018/4/17.
 */
public class DataTmpDao extends BaseDao<PersonDataTmp> {

    public static final String SELECT_DATA="from PersonDataTmp";

//    public List<PersonDataTmp> getData(int num){
//        Query query = createQuery(SELECT_DATA,null);
//        List list = query.setFirstResult(1).setMaxResults(num).list();
//        return list;
//    }

}
