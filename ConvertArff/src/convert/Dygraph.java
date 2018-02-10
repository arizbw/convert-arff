package convert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Dygraph {
	FileReader fileReadCompute;
	FileWriter fw;

	Integer n =1;
public void readWriteFile (String readText, String writeText){
	String line;
	Integer n =1;
	String[] wordsArray = new String[2];
	Double real =0.0;
	Double predict =0.0;
	try {
		fileReadCompute = new FileReader(readText);
		fw = new FileWriter(writeText, true);
	 
    BufferedReader reader = new BufferedReader(fileReadCompute);
    fw.write("Instances"+","+"Real"+","+"Predicted"+"\n");
    while ((line = reader.readLine()) != null)
    {

    	wordsArray = line.split(",");
        if (!wordsArray[0].isEmpty() & !wordsArray[1].isEmpty() )	
        {
        	real = Double.parseDouble(wordsArray[1]);
        	predict = Double.parseDouble(wordsArray[0]);
    		fw.write(n + "," + real+";"+ real+";"+ real+","+ predict+";"+ predict+";"+ predict +"\n");
        }
        n++;
    }
    fileReadCompute.close();
    fw.close();
	}
    catch (IOException localIOException) {
	}
	//mae = this.sumMae / n;
	//rmse = Math.sqrt(this.sumRmse / n);
	//smape = this.sumSmape*100 / n;
}


}
