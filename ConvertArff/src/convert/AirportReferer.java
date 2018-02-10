package convert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class AirportReferer {
	   Hashtable<String, Hashtable> tableRef;
	   public boolean readRefFile(String fileReadName)
	   {
	     String value = "";
	     FileReader file = null;
	     FileWriter fw = null;
	     
	     String[] wordsArray = new String[9];
	     String words = "";
	     
	     this.tableRef = new Hashtable();
	     Hashtable<String, Double> intableRef = new Hashtable();
	     try
	     {
	       file = new FileReader(fileReadName);
	       BufferedReader reader = new BufferedReader(file);
	       
	       String line = "";
	       int k = 0;
	       while ((line = reader.readLine()) != null)
	       {
	         if (k > 0)
	         {
	           wordsArray = line.split(",");
	           if(wordsArray.length>4)
	           {
	        	 
	           //System.out.println(wordsArray.length);
	           intableRef.put("lat", Double.valueOf(Double.parseDouble(wordsArray[5])));
	           intableRef.put("long", Double.valueOf(Double.parseDouble(wordsArray[6])));
	           this.tableRef.put(new String(wordsArray[0]), new Hashtable(intableRef));
	           }
	         }
	         k++;
	       }
	       file.close();
	       return true;
	     }
	     catch (IOException e) {}
	     return false;
	   }
	   
	   public Hashtable gettableRef()
	   {
	     return this.tableRef;
	   }
}
