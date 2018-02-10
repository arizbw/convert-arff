package convert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.stream.DoubleStream;

public class CreateData {
	
	public void WriteFile (String writeText, int noRows, int noCols){
		FileWriter fw;
		Random randomGenerator = new Random();
		String header = "@relation "+writeText+"\n";
		
		for (int x=0;x<noCols;x++)
		{
			header += "@attribute " +"var"+x+" real"+"\n" ;
		}
		header += "\n"+"@data"+"\n";
		try {
			fw = new FileWriter(writeText, true);
			fw.write(header);
		    for (int rowCount=0;rowCount<noRows;rowCount++)
		    {
		    	String writeNumber = "";
		    	double tempNumber =0.0;
		    	 for (int colCount=0;colCount<noCols;colCount++)
				    {
		    		 	String spacer = ",";
		    		 	if(colCount == noCols-1)
		    		 		spacer = "";
		    		 	
		    		 	if(colCount%10 == 0)
		    		 	{
		    		 		tempNumber = randomGenerator.nextInt(10);
		    		 	}
		    		 	
		    		 	else
		    		 	{
		    		 		tempNumber = 100.0f*randomGenerator.nextGaussian()*5.0f;
		    		 		
		    		 	}
		    		 	
		    		 	writeNumber += Double.toString(tempNumber)+spacer;
				    }
		    	 System.out.println(writeNumber);
		    	 fw.write(writeNumber+"\n");
		    }
			
		    fw.close();
		}
		catch (IOException localIOException) {
		} 
	}

}
