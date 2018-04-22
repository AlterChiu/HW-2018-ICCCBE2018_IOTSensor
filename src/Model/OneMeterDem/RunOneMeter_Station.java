package Model.OneMeterDem;

import java.io.File;
import java.io.IOException;

import usualTool.AtFileReader;
import usualTool.FileFunction;

public class RunOneMeter_Station {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String station = args[0] + "\\";
//		String indexDem = args[1];
		
		String station = args[0] + "\\";
		String indexDem = args[1] ;
		String fileAdd = Global.Global.demFolder;
	
		FileFunction ff = new FileFunction();
		
		ff.copyFile(fileAdd + station + indexDem + ".asc", fileAdd + "firstDem.asc");
		ff.copyFile(fileAdd + station + indexDem + "(kn).asc", fileAdd + "firstDem(kn).asc");
		
		
		
	}

}
