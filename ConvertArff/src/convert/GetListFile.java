package convert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GetListFile {
	FileReader files ;
    FileWriter fw ;
    ArrayList<String> thisFileList = new ArrayList<String>();
    
	public ArrayList<String> getListFile(String fileReadName)
	{
		String line = "";
		
		try
	    {
			//System.out.println(fileReadName);
			files = new FileReader(fileReadName);
			
			BufferedReader reader = new BufferedReader(files);
			while ((line = reader.readLine()) != null)
	        {
				thisFileList.add(line);
	        }
	    }
		catch (IOException localIOException) {}
		return thisFileList;
	}

}
