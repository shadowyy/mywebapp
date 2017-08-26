package com.shadow.task;

import com.shadow.domain.User;
import com.shadow.service.IUserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.shadow.aop.LogAspect.getLIST;
import static com.shadow.aop.LogAspect.setLIST;

/**
 * @author yy
 * @version 2017/2/18 0:14
 */
@Component
public class SpringTask {
    @Resource
    private IUserService userServiceImpl;

    //@Scheduled(cron = "*/30 * * * * ?")
     @Scheduled(cron = "0 0 */1 * * ?")
    public void m() {
        List<User> list = getLIST();
        System.out.println("存储数据"+list);
        setLIST(new ArrayList<>());
        System.out.println(userServiceImpl.insertBatch(list));
    }
}
