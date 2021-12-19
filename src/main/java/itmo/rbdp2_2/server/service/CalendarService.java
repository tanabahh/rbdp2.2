package itmo.rbdp2_2.server.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class CalendarService {

    public String getDay(String year, String month, String day) throws ParseException {
        Date date = new SimpleDateFormat("dd/M/yyyy").parse(String.valueOf(day +
                "/" +
                month +
                "/" +
                year));
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }

    public Boolean isLeap(String year){
        int intYear = new Integer(year);
        return ((intYear % 4 == 0) && (intYear % 100 != 0) || (intYear % 400 == 0));
    }

    public String getInterval(String firstDate, String secondDate) throws ParseException {
        Date date1 = new SimpleDateFormat("dd.MM.yyyy").parse(firstDate);
        Date date2 = new SimpleDateFormat("dd.MM.yyyy").parse(secondDate);
        long milliseconds = Math.abs(date2.getTime() - date1.getTime());
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        return (String.valueOf(days));
    }
}
