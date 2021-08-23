import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanItem {
    private Date planDate;
    private String detail;
    private String peoples;

    public static Date getDateFromString(String strDate){
        Date date = null;
        try{
            date = new SimpleDateFormat("yyy-MM-dd").parse(strDate);
        }catch (ParseException e){
            e.printStackTrace();
            System.out.println("Class : PlanItem  Method : getDateFromString, Str to Date type parse err");
        }
        return date;
    }

    public PlanItem(String strDate, String detail) {
        planDate = PlanItem.getDateFromString(strDate);
        this.detail = detail;
    }

    public String getDetail(){
        return this.detail;
    }

        public Date getDate(){
        return this.planDate;
    }

    public void addPeople(String name){
        this.peoples += name + ",";
    }

}
