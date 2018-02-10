package convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testmain {
	   public static void main(String[] args)
	   {
		   String inputDate = "2014-12-27";
		   String timeDate = "13";
		   Date lineDate = new Date();
		   SimpleDateFormat fromFile = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           try
           {
             
			lineDate = fromFile.parse(inputDate);
            lineDate.setTime(lineDate.getTime() + (900000*(Long.parseLong(timeDate))));
           }
           catch (ParseException e)
           {
           }
           System.out.println(fromFile.format(lineDate));
	   }

}
