package cn.zealon.mvc.config;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @auther: Zealon
 * @Date: 2018-07-12 17:27
 */
public class DateConvert  implements Formatter<Date> {
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date d = null;
        try {
            d = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public String print(Date date, Locale locale) {
        return null;
    }

}
