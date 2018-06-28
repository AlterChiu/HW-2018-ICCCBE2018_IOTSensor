package SOBEK;

import java.io.File;
import java.io.IOException;

public class Runtimes {

	public static void main(String[] args) throws IOException {

		// run the sobek modle
		// and the model.exe should be under this index

		String command[] = new String[] { "cmd.exe", "/c", " start  /wait /B Sobek_Forecast.bat " };
		ProcessBuilder builder = new ProcessBuilder();
		builder.directory(new File("C:\\Sobek213\\"));
		builder.command(command);
		builder.start();
	}

}
