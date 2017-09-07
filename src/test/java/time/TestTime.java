package time;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Date;
import java.util.TimeZone;

/**
 * 抛弃JDK，使用其他类库中的时间格式化类：
 * 1.使用Apache commons 里的FastDateFormat，宣称是既快又线程安全的SimpleDateFormat, 可惜它只能对日期进行format, 不能对日期串进行解析。
 * 2.使用Joda-Time类库来处理时间相关问题
 * <p>
 * DateFormatUtils是将时间转化为字符串的工具类
 * <p>
 * DateUtils 提供了很多很方便的功能，减轻了使用Date的复杂性。把原来需用 Calendar 才能完成的功能统一集中了起来，也就是说没有对应的 CalendarUtils 类
 * 在JDK中，Date与Calendar概念本身就有些混淆，只是为了保持兼容性才引入的Calendar
 * 相对于Calendar提供的方法，DateUtils提供了更加合理的方法，对时间的单个字段操作变得更加的容易。
 *
 * @author shadowyy
 * @version 2017/9/7 16:42
 */
public class TestTime {
    private static final String DATE_VALUE = "2016-03-11(星期四)15:47:02.1234";
    private static final String PATTERN = "yyyy-MM-dd(EEEE)HH:mm:ss.SSSS";

    public static void main(String[] args) throws Exception {
        FastDateFormat fdf = FastDateFormat.getInstance(PATTERN, TimeZone.getTimeZone("UTC"));
        System.out.println(fdf.parse(DATE_VALUE));
        System.out.println(fdf.format(new Date()));


        DateFormatUtils.format(System.currentTimeMillis(), PATTERN);

        DateUtils.isSameDay(new Date(), new Date());
        DateUtils.parseDate(DATE_VALUE, new String[]{PATTERN});

        //StopWatch 是一个方便的计时器
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatch.stop();
        System.out.println(stopWatch.getTime());
    }
}
