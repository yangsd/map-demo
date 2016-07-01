package com.map.demo.action;

import com.map.demo.bean.Location;
import com.map.demo.service.LocationService;
import com.map.demo.vo.ReverseResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Created by sdyang on 2016/6/29.
 */
@Controller
public class TestAction {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "/baidu",method = {RequestMethod.GET})
     public String baidu(Model model){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations",locationList);
        return "baidu_map";
    }


    @RequestMapping(value = "/qq",method = {RequestMethod.GET})
    public String qq(Model model){
        List<Location> locationList = locationService.findAll();
        model.addAttribute("locations",locationList);
        return "qq_map";
    }

    @RequestMapping(value = "/reverse",method = {RequestMethod.GET})
    @ResponseBody
    public String test(Model model) throws IOException {
        ReverseResultVo result =    locationService.geocoding();
        return  result.getResult().getFormatted_address();
    }

    }
