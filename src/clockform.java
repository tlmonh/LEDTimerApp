
import java.text.SimpleDateFormat;
import java.util.Calendar;
/***************************************************************
* file: clockform.java
* author: Timothy L. Monh
* class: CS 245
*
* assignment: LEDTimeAPP
* date last modified: 3/11
*
* purpose: To help set values to represent clock calculations
*
****************************************************************/

public class clockform {
    private static Calendar cal;
    private String seconds;
    private String minutes;
    private String hours;
    private SimpleDateFormat s;
    private SimpleDateFormat m;
    private SimpleDateFormat h;
    public int sDigit1;
    public int mDigit1;
    public int hDigit1;
    public int sDigit2;
    public int mDigit2;
    public int hDigit2;
    //To get current time and set out the minute,second,and hour strings
    public clockform()
    {
         cal = Calendar.getInstance();
                seconds = "ss";
                minutes = "mm";
                hours = "hh";
                int secondOut, minuteOut, hourOut;
                s = new SimpleDateFormat(seconds);
                m = new SimpleDateFormat(minutes);
                h = new SimpleDateFormat(hours);
                secondOut = Integer.parseInt(s.format(cal.getTime()));
                minuteOut = Integer.parseInt(m.format(cal.getTime()));
                hourOut = Integer.parseInt(h.format(cal.getTime()));
                sDigit1 = secondOut % 10;
                mDigit1 = minuteOut % 10;
                hDigit1 = hourOut % 10;
                sDigit2 = secondOut / 10;
                mDigit2 = minuteOut / 10;
                hDigit2 = hourOut / 10;
    }
    //All these functions return the string values to be converted to LED printout in the clock.
    public int getSD1(){
        return sDigit1;
    }
    public int getSD2(){
        return sDigit2;
    }
    public int getMD1(){
        return mDigit1;
    }
    public int getMD2(){
        return mDigit2;
    }
    public int getHD1(){
        return hDigit1;
    }
    public int getHD2(){
        return hDigit2;
    }
}
