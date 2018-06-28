package Model.GetCloestValue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import main.MergeZoneAscii;
import usualTool.AtFileReader;
import usualTool.FileFunction;

public class ControlMain {

	private static String eventSimulation = "";
	private static String observation = "S:\\HomeWork\\HighDensityDem\\Observation\\201609260900_1H_50H.txt";

	public static void main(String[] args) throws IOException, NumberFormatException, ParseException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		String stationList[] = new String[] { "AnChung5", "HaiTeing4", "HaiTeing3", "ChauHuangTemple", "LongKin",
				"AnChung", "DinAhn", "AnHo", "SeeDingLaio" };

		// // clear the tempt save folder
		// String saveAdd = Global.Global.temptSaveFolder;
		// ff.delAllFile(saveAdd);
		//
		// // copy the file to the tempt save folder
		// String originalName = "\\dm1d";
		// for(int index = 0 ; index <=24 ; index++) {
		// ff.copyFile(eventSimulation + originalName + String.format("%04d", index) +
		// ".asc" , saveAdd + (index) + ".asc");
		// }
		//

		// start analysis
		System.out.println("value detect");
		GetClosetValue getClosetValue = new GetClosetValue(5);
		getClosetValue.setAllIotStation();
		getClosetValue.setStartTime("2016092716");
		Map<String, List<String>> reuslt = getClosetValue.getResultTree();

		// output the analysis result
		for(int index = 0 ; index<stationList.length;index++) {
			System.out.print(stationList[index]);
			reuslt.get(stationList[index]).forEach(result -> System.out.print("\t" + result));
			System.out.println();
		}
	}

	public static TreeMap<String, ArrayList<String>> getEventObservation() throws IOException {
		TreeMap<String, ArrayList<String>> temptTree = new TreeMap<String, ArrayList<String>>();
		String[][] temptContent = new AtFileReader(observation).getContent("\t");

		for (int column = 0; column < temptContent[0].length; column++) {
			ArrayList<String> temptArray = new ArrayList<String>();

			for (int row = 1; row < temptContent.length; row++) {
				temptArray.add(Double.parseDouble(temptContent[row][column]) + "");
			}
			temptTree.put(temptContent[0][column], temptArray);
		}
		return temptTree;
	}

}
