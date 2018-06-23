package Model.GetCloestValue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.MergeZoneAscii;
import usualTool.FileFunction;

public class ControlMain {

	private static String eventSimulation = "";
	private static String observation = "S:\\HomeWork\\HighDensityDem\\Observation\\201609260900_1H_50H.txt";
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		
		
//		// clear the tempt save folder
//		String saveAdd = Global.Global.temptSaveFolder;
//		ff.delAllFile(saveAdd);
//		
//		// copy the file to the tempt save folder
//		String originalName = "\\dm1d";
//		for(int index = 0  ; index <=24 ; index++) {
//			ff.copyFile(eventSimulation + originalName + String.format("%04d", index) + ".asc" , saveAdd + (index) + ".asc");
//		}
//		
		
		// start analysis
		System.out.println("value detect");
		GetClosetValue getClosetValue = new GetClosetValue(20,observation);
		getClosetValue.setAllIotStation();
		Map<String,List<String>> reuslt = getClosetValue.getResultTree();
		
		// output the analysis result
		for(String station : reuslt.keySet()) {
			System.out.print(station);
			reuslt.get(station).forEach(result -> System.out.print("\t" + result ));
		}
	}

}
