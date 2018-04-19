package com.season.web;

import com.season.cons.CommonConstant;
import com.season.dao.FoodKindDao;
import com.season.dao.Page;
import com.season.domain.FoodKind;
import com.season.domain.MsgBean;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import com.season.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class DataController extends BaseController {

    @Autowired
    DataService dataService;
    @Autowired
    FoodKindDao foodKindDao;

    @RequestMapping("/data/data.html")
    public ModelAndView personData() {
        ModelAndView mav = new ModelAndView("person_data");

        mav.addObject("countKinds", getPersonCount());
        return mav;
    }

    @RequestMapping("/data/data-book.html")
    public ModelAndView personData_book() {
        ModelAndView mav = new ModelAndView("person_data_book");
        List<FoodKind> foodKinds = foodKindDao.loadAll();
        mav.addObject("foodKinds",foodKinds);
        mav.addObject("foodTime",getMealKind());
        return mav;
    }

    @RequestMapping("/data/data-edit.html")
    public ModelAndView personData_edit() {
        ModelAndView mav = new ModelAndView("person_data_edit");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/data/data-count")
    public MsgBean countData(@RequestParam("kind") Integer kind) {
        return dataService.countData(kind);
    }

    @RequestMapping("/data/data-modify")
    @ResponseBody
    public MsgBean dataModify(PersonData data,
                              @RequestParam("type") String type) {
        try {
            switch (type) {
                case "1":
                    dataService.add(data);
                    break;
                case "2":
                    dataService.modify(data);
                    break;
                default:
                    return new MsgBean().setCode(-1)
                            .setMsg("不支持操作类型: " + type);
            }
        } catch (MyException e) {
            return new MsgBean().setCode(-1)
                    .setMsg(e.getMessage());
        }

        return new MsgBean().setCode(0);
    }


    @RequestMapping(value = "/data/data-list", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean queryDataList(@RequestParam("pageNo") Integer no,
                                 @RequestParam("pageSize") Integer size,
                                 @RequestParam("startTime") String startTime,
                                 @RequestParam("endTime") String endTime
                                 ) {
        Page page = dataService.queryDataList(no, size, startTime, endTime);

        return new MsgBean().setCode(0)
                .setData(page);
    }


    @RequestMapping(value = "/data/data-del", method = RequestMethod.POST)
    @ResponseBody
    public MsgBean delData(@RequestBody List<PersonData> dataList){
       return dataService.delData(dataList);
    }


}
