package com.season.web;

import com.season.cons.CommonConstant;
import com.season.dao.Page;
import com.season.domain.MsgBean;
import com.season.domain.PersonData;
import com.season.exception.MyException;
import com.season.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class DataController extends BaseController {

    @Autowired
    DataService dataService;

    @RequestMapping("/data/data.html")
    public ModelAndView personData() {
        ModelAndView mav = new ModelAndView("person_data");
        List<Map> supportCount = new ArrayList<>();
        for (int i = 0; i < CommonConstant.PERSON_COUNT.length; i++) {
            Map<String, Object> kv = new HashMap<>();
            kv.put("code", i);
            kv.put("name", CommonConstant.PERSON_COUNT[i]);
            supportCount.add(kv);
        }
        mav.addObject("countKinds", supportCount);
        return mav;
    }

    @RequestMapping("/data/data-book.html")
    public ModelAndView personData_book() {
        ModelAndView mav = new ModelAndView("person_data_book");
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
        if (kind < 0 || kind >= CommonConstant.PERSON_COUNT.length) {
            return new MsgBean().setCode(-1).setMsg("不支持的统计类型");
        }
        switch (kind) {
            case 0://一周

                break;
            case 1://一个月

                break;
            case 2://三个月

                break;
            case 3://一年

                break;

        }

        return null;
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
