package com.season.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by season on 2018/4/15.
 */
@Controller
public class DataController {

    @RequestMapping("/data/data")
    public ModelAndView personData(){
        ModelAndView mav=new ModelAndView("person_data");
        return mav;
    }
    @RequestMapping("/data/data-book")
    public ModelAndView personData_book(){
        ModelAndView mav=new ModelAndView("person_data_book");
        return mav;
    }
    @RequestMapping("/data/data-edit")
    public ModelAndView personData_edit(){
        ModelAndView mav=new ModelAndView("person_data_edit");
        return mav;
    }

}
