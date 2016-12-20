package com.shadow.controller;

import com.shadow.service.IHotelService;
import com.shadow.vo.HotelVO;
import com.shadow.vo.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * hotel
 *
 * @author yy
 * @version 2016/12/20 18:47
 */
@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    IHotelService hotelService;

    @RequestMapping(value = "/query", method = {RequestMethod.POST})
    @ResponseBody
    public JsonResult getJson(@RequestBody HotelVO hotelVO) {
        return JsonResult.success(hotelService.query(hotelVO));
    }
}
