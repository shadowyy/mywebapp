package time;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GJChronology;

import java.util.Date;
import java.util.Locale;

/**
 * @author shadowyy
 * @version 2017/9/7 17:20
 */
public class TestJodaTime {
    public static void main(String[] args) throws Exception {
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1);
        DateTime dateTime2 = new DateTime(2016, 2, 14, 0, 0, 0);
        System.out.println(dateTime2); // out: 2016-02-14T00:00:00.000+08:00
        DateTime dateTime3 = new DateTime(System.currentTimeMillis());
        System.out.println(dateTime3);
        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4);
        DateTime dateTime5 = new DateTime("2016-02-15T00:00:00.000+08:00");
        System.out.println(dateTime5); // out: 2016-02-15T00:00:00.000+08:00


        // is开头的方法，判断时间发生前后
        dateTime1.isBeforeNow();


        // with开头的方法将时间切换，如果不存在自适应
        DateTime dateTime2000Year = new DateTime(2000, 2, 29, 0, 0, 0);
        System.out.println(dateTime2000Year); // out: 2000-02-29T00:00:00.000+08:00
        DateTime dateTime1997Year = dateTime2000Year.withYear(1997);
        System.out.println(dateTime1997Year); // out: 1997-02-28T00:00:00.000+08:00

        //plus/minus开头的方法
        DateTime now = new DateTime();
        System.out.println(now);
        DateTime tomorrow = now.plusDays(1);
        System.out.println(tomorrow);
        DateTime lastMonth = now.minusMonths(1);
        System.out.println(lastMonth);

        //Property是DateTime中的属性，保存了一些有用的信息
        DateTime now2 = new DateTime();
        now2.monthOfYear().getAsText(); // February
        now2.monthOfYear().getAsText(Locale.KOREAN); //
        now2.dayOfWeek().getAsShortText(); // Fri
        now2.dayOfWeek().getAsShortText(Locale.CHINESE); // 星期五

        Chronology coptic = CopticChronology.getInstance();
        Chronology gregorianJuian = GJChronology.getInstance(DateTimeZone.forID("Asia/Tokyo"));//时区是作为chronology的一部分来被实现的


        DateTime dt = new DateTime(2005, 3, 26, 12, 0, 0, 0);
        DateTime plusPeriod = dt.plus(Period.days(1));// Period：它保存了一段时间，可以直接创建Period，或者从Interval对象构建。
        DateTime plusDuration = dt.plus(new Duration(24L*60L*60L*1000L));// Duration：它保存了一个精确的毫秒数。同样地，可以直接创建Duration，也可以从Interval对象构建。

    }
}
