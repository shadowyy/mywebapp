package com.shadow.utils.beanCopier;

import com.shadow.domain.HotelPO;
import com.shadow.utils.BeanCopier;
import com.shadow.vo.HotelVO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * hotel
 *
 * @author yy
 * @version 2016/12/20 23:23
 */
@Component
public class HotelBeanCopier extends BeanCopier<HotelVO, HotelPO> {

    @PostConstruct
    public void init() {
        setSourceClass(HotelVO.class);
        setTargetClass(HotelPO.class);
        super.init();
    }

    @Override
    public HotelPO afterCopy(HotelVO source, HotelPO target) {
        // target.setAddress(StringUtils.trimToEmpty(source.getAddress()));
        return target;
    }
}
