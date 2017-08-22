package com.shadow.service.impl;

import com.google.common.collect.Lists;
import com.shadow.dao.HotelDao;
import com.shadow.domain.HotelPO;
import com.shadow.service.IHotelService;
import com.shadow.util.beanCopier.HotelBeanCopier;
import com.shadow.vo.HotelVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * hotel
 *
 * @author yy
 * @version 2016/12/20 20:51
 */
@Service
public class HotelServiceImpl implements IHotelService {
    @Resource
    HotelDao hotelDao;

    @Resource
    HotelBeanCopier beanCopier;

    @Override
    public List<HotelVO> query(HotelVO hotelVO) {
        List<HotelPO> tmp = Lists.transform(Lists.newArrayList(hotelVO), beanCopier);
        return Lists.transform(hotelDao.query(tmp.get(0)), beanCopier.getReverse());
    }
}


