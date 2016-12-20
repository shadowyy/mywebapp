package com.shadow.service;

import com.shadow.vo.HotelVO;

import java.util.List;

/**
 * hotel
 *
 * @author yy
 * @version 2016/12/20 20:51
 */
public interface IHotelService {

    List<HotelVO> query(HotelVO hotelVO);

}
