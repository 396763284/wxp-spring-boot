package per.wxp.hbase.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static  String format(Date date,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date parse(String dateString,String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        Date date = new Date();
        try {
             date=sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
