 package convert;
 
 import java.io.PrintStream;
 
 public class ConvertMain
 {

   public static void main(String[] args)
   {
	  
     if (args.length == 0)
     {
       System.out.println("Error- fd please type a string");
     }
     else
     {
    	 /*Compute MAE RMSE and SMAPE for every instance number
    	  * arg[0] = -error
    	  * arg[1] = file input
    	  * arg[2] = file output
    	  * arg[3] = instance number
    	  */
    	 
    	 
    	 //System.out.println(args[0]);
    	 if (args[0].equals("-error"))
    	 {
    		 System.out.println("Counting Error :");
    		 ComputeError newComputeError = new ComputeError();
    		 newComputeError.readWriteFile(args[1], args[2], Double.parseDouble(args[3]));
    		 
    	 }
    	 else if (args[0].equals("-createdata")){
    		 System.out.println("CreateData :");
    		 CreateData newData = new CreateData();
    		 newData.WriteFile(args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));   
     	 }    	
    	 else if (args[0].equals("-realpredict")){
    		 System.out.println("RealPredict :");
    		 Dygraph newDygraph = new Dygraph();
    		 newDygraph.readWriteFile(args[1], args[2]);   
     	 } 
    	 /*
    	  * args 0 mode
    	  * args 1 reference to carrier
    	  * args 2 reference to airport
    	  * args 3 source filename
    	  * args 4 dest filename
    	  */
    	 else if (args[0].equals("-airline"))
    	 {
    		   ConvertArffAirline newFile = new ConvertArffAirline();
    		   CarrierReferer carRefer = new CarrierReferer();
    		   AirportReferer airRefer = new AirportReferer();
    		   carRefer.readRefFile(args[1]);
    		   airRefer.readRefFile(args[2]);
    	       System.out.println("Read From Reference Success");
    	       newFile.readTextFile(args[3], args[4], carRefer.gettableRef(), airRefer.gettableRef());
    	       System.out.println("Finish");	 
    	 }
    	 else if (args[0].equals("-libsvm"))
    	 {
    	       ConvertArff newFile = new ConvertArff();
    	       LinkRefer newReferFile = new LinkRefer();
    	       GetListFile newListFile = new GetListFile();
    	       newReferFile.readRefFile(args[3]);
    	       System.out.println("Read From Reference Success for libsvm");
    	       newFile.readTextFileLibsvm(newListFile.getListFile(args[1]), args[2], newReferFile.gettableRef());
    	       System.out.println("Finish");	 
    	 }
    	 else if (args[0]== "-caff")
    	 {
    	       ConvertArff newFile = new ConvertArff();
    	       LinkRefer newReferFile = new LinkRefer();
    	       GetListFile newListFile = new GetListFile();
    	       newReferFile.readRefFile(args[2]);
    	       System.out.println("Read From Reference Success");
    	       newFile.readTextFile(newListFile.getListFile(args[0]), args[1], newReferFile.gettableRef());
    	       System.out.println("Finish");	 
    	 }
    	 else if (args[0]== "-old"){
	        ConvertArff newFile = new ConvertArff();
	       LinkRefer newReferFile = new LinkRefer();
	       GetListFile newListFile = new GetListFile();
	       newReferFile.readRefFile(args[2]);
	       System.out.println("Read From Reference Success");
	       newFile.readTextFile(newListFile.getListFile(args[0]), args[1], newReferFile.gettableRef());
	       System.out.println("Finish");
    	 }
    	 

     }
   }
 }