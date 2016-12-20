package com.shadow.dao;

import com.shadow.domain.HotelPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * hotel
 *
 * @author yy
 * @version 2016/12/20 18:52
 */
@Repository
public interface HotelDao {
    List<HotelPO> query(HotelPO hotelPO);
}
