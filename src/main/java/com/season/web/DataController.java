package com.season.web;

import com.season.cons.CommonConstant;
import com.season.domain.MsgBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class DataController {

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

}
