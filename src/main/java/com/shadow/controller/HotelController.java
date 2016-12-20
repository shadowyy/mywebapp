package com.shadow.controller;

import com.shadow.dao.HotelDao;
import com.shadow.domain.HotelPO;
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
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    HotelDao hotelDao;

    @RequestMapping(value = "/query", method = {RequestMethod.POST})
    @ResponseBody
    public HotelPO getJson(@RequestBody HotelPO hotelPO) {
        return hotelDao.query(hotelPO);
    }
}
