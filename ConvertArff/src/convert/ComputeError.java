package convert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ComputeError {
	Double sumMae=0.0,sumRmse=0.0, sumMape = 0.0, sumReal = 0.0, sumPred = 0.0;
	Double mae,rmse,smape = 0.0;
	FileReader fileReadCompute;
	FileWriter fw;

	Integer n =1;
public void readWriteFile (String readText, String writeText, Double noInstances){
	String line;
	Integer n =1;
	String[] wordsArray = new String[2];
	try {
		fileReadCompute = new FileReader(readText);
		fw = new FileWriter(writeText, true);
	 
    BufferedReader reader = new BufferedReader(fileReadCompute);
    while ((line = reader.readLine()) != null)
    {

    	wordsArray = line.split(",");
        if (!wordsArray[0].isEmpty() & !wordsArray[1].isEmpty() )	
        {
        	this.computeMAE(Double.parseDouble(wordsArray[1]), Double.parseDouble((wordsArray[0])));
        	this.computeRMSE(Double.parseDouble(wordsArray[1]), Double.parseDouble((wordsArray[0])));
        	this.computeMAPE(Double.parseDouble(wordsArray[1]), Double.parseDouble((wordsArray[0])));
        	this.computeSumPred(Double.parseDouble((wordsArray[0])));
        	this.computeSumReal(Double.parseDouble((wordsArray[1])));
    	}
    	
    	if(n%noInstances == 0)
    	{
    		Double tempMae  = this.sumMae /n;
    		Double tempRmse = Math.sqrt(this.sumRmse / n);
    		Double tempMape = this.sumMape*100 / n;
    		//Double tempSmape = this.sumSmape*100 / n;
    		Double tempReal = this.sumReal / n;
    		Double tempPredict = this.sumPred / n;
    		fw.write(n + "," + tempMae +","+ tempRmse+","+ tempMape+","+ tempReal+","+ tempPredict+"\n");
    		System.out.println("Instances : " +n+ " MAE :" + tempMae + " RMSE: " +tempRmse + " MAPE: " +tempMape +" Real: " +tempReal +" Predict: " +tempPredict);
    		//this.sumMae = 0.0;
    		//this.sumRmse = 0.0;
    		//this.sumSmape = 0.0;
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

public void computeMAE(Double act, Double pred)
{
	sumMae += Math.abs((act - pred));	
}
public void computeSumReal(Double realNum)
{
	sumReal += realNum;	
}
public void computeSumPred(Double predNum)
{
	sumPred += predNum;	
}
public void computeRMSE (Double act, Double pred)
{
	sumRmse += Math.pow(act-pred,2); 
}
public void computeMAPE (Double act, Double pred)
{
	Double temp = Math.abs((act - pred) / act);
	if (temp.isInfinite() || temp.isNaN())
		temp =2.0;
	
	//System.out.println(temp);
	sumMape += temp;
	/*
	///(Math.abs((act-pred)) / (Math.abs((Math.abs(act)+Math.abs(pred))))) ; 
	Double tempVariable = (Math.abs((act-pred)) / (Math.abs((Math.abs(act)+Math.abs(pred))))) ; 
	//System.out.println(sumSmape);
	if(tempVariable.isNaN() || tempVariable.isInfinite())
		tempVariable = 0.0;
	sumSmape += tempVariable ; 
	*/
}

public Double getMae()
{
	return this.mae;
}
public Double getRmse()
{
	return this.rmse;
}
public Double getSmape()
{
	return this.smape;
}

}
