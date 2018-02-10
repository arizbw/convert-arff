 package convert;
 
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Hashtable;
 
 public class LinkRefer
 {
   Hashtable<String, Hashtable> tableRef;
   
   public boolean readRefFile(String fileReadName)
   {
     String value = "";
     FileReader file = null;
     FileWriter fw = null;
     
     String[] wordsArray = new String[11];
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
           
           if(wordsArray.length>11)
           {
           //System.out.println(wordsArray.length);
           intableRef.put("startlat", Double.valueOf(Double.parseDouble(wordsArray[8])));
           intableRef.put("endlat", Double.valueOf(Double.parseDouble(wordsArray[9])));
           intableRef.put("startlng", Double.valueOf(Double.parseDouble(wordsArray[10])));
           intableRef.put("endlng", Double.valueOf(Double.parseDouble(wordsArray[11])));
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



/* Location:           D:\Data Traffic\2009-extracted\coonver.jar

 * Qualified Name:     convert.LinkRefer

 * JD-Core Version:    0.7.0.1

*/