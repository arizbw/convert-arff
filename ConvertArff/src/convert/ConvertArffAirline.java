package convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Hashtable;

public class ConvertArffAirline {
	public void readTextFile(String fileReadName, String fileWriteName, Hashtable<String, Hashtable> refCarrier, Hashtable<String, Hashtable> refAirport)
	{
	     String value = "";
	     FileReader file = null;
	     FileWriter fw = null;
	      String words = "";
	      
	      String[] wordsArray = new String[16];
	      String tempAttribute = "";
	      String outputLine = "";
	  
	      Double meanClass = Double.valueOf(0.0D);
	      Double sumClass = Double.valueOf(0.0D);
	      
	      String line="";
	      Double oriLatitude = Double.valueOf(0.0D);
	      Double oriLongitude = Double.valueOf(0.0D);
	      Double destLatitude = Double.valueOf(0.0D);
	      Double destLongitude = Double.valueOf(0.0D);
	      Double carrier = Double.valueOf(0.0D);

	      
	      DecimalFormat numberFormat =  new DecimalFormat("#.#;-#.#");
	      DecimalFormat latlangFormat=  new DecimalFormat("#.######;-#.######");
	          
	      String putBegin = "@relation " + fileWriteName + "\n" + 
	        "@attribute " + "year" + " " + "numeric" + "\n" + 
	        "@attribute " + "month" + " " + "numeric" + "\n" + 
	        "@attribute " + "dayofmonth" + " " + "numeric"+ "\n" + 
	        "@attribute " + "dayofweek" + " " + "numeric"+ "\n" + 
	        "@attribute " + "departure-time" + " " + "numeric"  + "\n" + 
	        "@attribute " + "arrival-time" + " " + "numeric" + "\n" + 
	        "@attribute " + "carrier" + " " + "numeric" + "\n" + 
	        "@attribute " + "fligh-num" + " " + "numeric" + "\n" + 
	        "@attribute " + "elapsed-time" + " " + "numeric" + "\n" + 
	        "@attribute " + "oriLat" + " " + "real" + "\n" + 
	        "@attribute " + "oriLang" + " " + "real" + "\n" + 
	        "@attribute " + "destLat" + " " + "real" + "\n" + 
	        "@attribute " + "destLong" + " " + "real" + "\n"  + 
	        "@attribute " + "distance" + " " + "numeric" + "\n" + 
	        "@attribute " + "divert" + " " + "numeric" + "\n" + 
	        "@attribute " + "status" + " " + "numeric" + "\n" +
	        "@attribute " + "delay-time" + " " + "numeric" + "\n" + "\n" + 
	        
	        "@data" + "\n";
	      
	      try
	      {

	        fw = new FileWriter(fileWriteName, true);
	        fw.write(putBegin);

	        int k = 0;
	        int m = 0;
	        double tempDelay =0.0;
	        double status = 1.0;
	        file = new FileReader(fileReadName);
	        BufferedReader reader = new BufferedReader(file);
	        while ((line = reader.readLine()) != null)
	        {
	        	
	          if (k >= 0)
	          {
	        	 // System.out.println(line);  
	            wordsArray = line.split(",");
	            //System.out.println(wordsArray.length); 
	            if (refCarrier.containsKey(wordsArray[6]))
	            {
	              carrier = (Double)((Hashtable)refCarrier.get(wordsArray[6])).get("inumber");
	             
	              if (wordsArray.length > 6)
	              {
	            	if (refAirport.containsKey(wordsArray[9]) && refAirport.containsKey(wordsArray[10]))
	  	            {
	            	//System.out.println(wordsArray[9]);
	  	              oriLatitude = (Double)((Hashtable)refAirport.get(wordsArray[9])).get("lat");
	  	              oriLongitude = (Double)((Hashtable)refAirport.get(wordsArray[9])).get("long");
	  	              destLatitude = (Double)((Hashtable)refAirport.get(wordsArray[10])).get("lat");
	  	              destLongitude = (Double)((Hashtable)refAirport.get(wordsArray[10])).get("long");
	  	            
	  	            }
	            	tempDelay =  Double.parseDouble(wordsArray[13]);
	            	if (tempDelay<0)
	            		status = 0.0;
	            	else
	            		status = 1.0;
	                outputLine = 
	                 numberFormat.format(Double.parseDouble(wordsArray[0]))+ "," + 
	                 numberFormat.format(Double.parseDouble(wordsArray[1]))+ "," +  
	                 numberFormat.format(Integer.parseInt(wordsArray[2]))+ "," + 	               
	                 numberFormat.format(Integer.parseInt(wordsArray[3]))+ "," +  
	                 numberFormat.format(Integer.parseInt(wordsArray[4]))+ "," + 
	                 numberFormat.format(Integer.parseInt(wordsArray[5]))+ "," + 
	                 numberFormat.format(carrier)+ "," + 
	                 numberFormat.format(Integer.parseInt(wordsArray[7]))+ "," + 
	                 numberFormat.format(Integer.parseInt(wordsArray[8]))+ "," + 
	                 latlangFormat.format(oriLatitude)+ "," + 
	                 latlangFormat.format(oriLongitude)+ "," + 
	                 latlangFormat.format(destLatitude)+ "," + 
	                 latlangFormat.format(destLongitude)+ "," + 
	                 numberFormat.format(Integer.parseInt(wordsArray[11]))+ "," + 
	                 numberFormat.format(Integer.parseInt(wordsArray[12]))+ "," + 
	                 numberFormat.format(status)+ "," + 
	                 numberFormat.format(Math.abs(tempDelay))+ "\n";
	               //System.out.println(outputLine);
	                fw.write(outputLine);
	            }
	          }         
	        }
	         k++;
	       }
	       file.close();
	       m++;
	       
	       fw.close();
	     }
	    catch (IOException localIOException) {}
	   }
	   
	   public void addTextBegin(String text, String fileName)
	  {
	    try
	     {
	      RandomAccessFile f = new RandomAccessFile(new File(fileName), "rw");
	       f.seek(0L);
	       f.write(text.getBytes());
	       f.close();
	     }
	     catch (Exception localException) {}
	   }
	   
	   public void getLatLang(Hashtable<String, Hashtable> table)
	   {
		   Enumeration items = table.keys();
		   String startlat = null, endlat, startlong, endlong = "";

		// Now iterate
		while(items.hasMoreElements()){
				
		    // Get the key (convert to string)
		    String strKey = (String)items.nextElement();		
		    // Use the key to then set the value in the hashtable;
					
		    // Use key to fetch the item and print out its value
		    startlat += table.get(strKey).get("startlat").toString()+",";
		    System.out.println(startlat);
		}
	   } 
}
