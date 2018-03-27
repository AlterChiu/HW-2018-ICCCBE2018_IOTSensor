package main;

import java.io.IOException;

public class SobekRuntimes {
	
	public SobekRuntimes() throws IOException {
		
		// run the sobek modle
		// and the model.exe should be under this index
		
		String command = "cmd.exe /c start /wait C:\\code\\javaWorkspace\\Fews\\SOBEK_Ascii\\Mapreduce_Run.bat";
		
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
