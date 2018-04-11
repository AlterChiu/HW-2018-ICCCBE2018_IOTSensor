package SOBEK;

import java.io.IOException;

public class Runtimes {
	
	public Runtimes() throws IOException {
		
		// run the sobek modle
		// and the model.exe should be under this index
		
		String command = "cmd.exe /c start /wait C:\\code\\javaWorkspace\\Fews\\ICCBE_IOTSensor\\Mapreduce_Run.bat";
		
		Process p = Runtime.getRuntime().exec(command);
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
