import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Calender {

    private static final int[] MAX_DAYS = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    private static final int[] LEAP_MAX_DAYS = {0,31,29,31,30,31,30,31,31,30,31,30,31};

    private HashMap <Date, PlanItem> planMap;

    public Calender(){
        planMap = new HashMap<Date,PlanItem>();
    }

    private boolean isLeapYear(int year){
        if(year%4==0 && (year%100!=0 || year %400==0))
            return true;
        return false;
    }

    private int getMaxDaysOfMonth(int year, int month){
        if(isLeapYear(year))
            return LEAP_MAX_DAYS[month];
        return MAX_DAYS[month];
    }

    private int getWeekDay(int year, int month){
        final int STANDARD_YEAR = 1970;
        final int STANDARD_WEEKDAY = 4; // 1970 / jan / 1st = Thursday

        int count=0;
        for(int i = STANDARD_YEAR;  i < year; i++){
            int delta = isLeapYear(i) ? 366 : 365;
            count += delta;
            count %= 7;
        }
        for(int i = 1; i < month; i++){
            int delta = getMaxDaysOfMonth(year,i);
            count += delta;
            count %= 7;
        }

        int weekday = (count + STANDARD_WEEKDAY) % 7;
        return weekday;
    }

    /*
     * @param year 년도, month 달
     * */
    public void printCalendar(int year, int month){

        System.out.printf("      << %4d년 %2d월 >>\n", year, month);
        System.out.println(" SUN MON TUE WED THU FRI SAT");

        int weekday = getWeekDay(year, month);
        for(int i=0; i<weekday; i++){
            System.out.print("    ");
        }

        int count = weekday;
        for(int i=1; i <= getMaxDaysOfMonth(year,month); i++){
            if( count%7 == 0 && i!=1) System.out.println();
            System.out.printf("%4d", i);
            count += 1;
        }
        System.out.println();
    }

    public void registerPlan(String strDate, String plan){
        PlanItem newPlanIteam = new PlanItem(strDate, plan);
        planMap.put(newPlanIteam.getDate(),newPlanIteam);
    }

    public PlanItem searchPlan(String strDate){
        Date date = PlanItem.getDateFromString(strDate);
        return planMap.get(date);
    }

/*    public static void main(String[] args) {
        Calender cal = new Calender();

        cal.registerPlan("2017-03-26", "Let's beging");
        System.out.println(cal.searchPlan("2017-03-26"));
    }*/

}