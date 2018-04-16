package Model.RunSobekInUncertaintyDem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import DEM.ChangeLevel.DemUncertainty;
import SOBEK.Runtimes;
import nl.wldelft.util.TimeUnit;
import usualTool.AtArrayFunction;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;
import usualTool.FileFunction;

public class ControlMain {

	private static String observationEvent = Global.Global.observation0611;
	private static String temptResultFolder = "S:\\HomeWork\\ICCCBE2018\\DEMUncertainty\\result\\";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		for (int time = 0; time < 100; time++) {
			// change the Dem
//			new DemUncertainty(Global.Global.originalDem).createNewDem(Global.Global.sobekRuntimeDem);

//			// start sobek runtimes
//			new Runtimes();
//			  try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			// return the sobek result
			moveResult();
//		}

		// output the analysis result
//		SaveResult(analysisSetting().getAnalysisTree());
	}

	static public void moveResult() {
		// create the new folder
		String saveFolderName = String.valueOf(new File(Global.Global.analysisFolder).list().length);
		new FileFunction().newFolder(Global.Global.analysisFolder + saveFolderName);

		// move result to the folder
		new FileFunction().moveFolder(Global.Global.sobekResult, Global.Global.analysisFolder + saveFolderName + "\\");
	}

	static public TreeMap<String, ArrayList<Double>> getEventObservation() throws IOException {
		TreeMap<String, ArrayList<Double>> temptTree = new TreeMap<String, ArrayList<Double>>();
		String[][] temptContent = new AtFileReader(observationEvent).getContent("\t");

		for (int column = 0; column < temptContent[0].length; column++) {
			ArrayList<Double> temptArray = new ArrayList<Double>();

			for (int row = 1; row < temptContent.length; row++) {
				temptArray.add(Double.parseDouble(temptContent[row][column]));
			}
			temptTree.put(temptContent[0][column], temptArray);
		}
		return temptTree;
	}

	static public void SaveResult(TreeMap<String, TreeMap<Integer, ArrayList<Double>>> result) throws IOException {
		for (String stationName : result.keySet().parallelStream().toArray(String[]::new)) {
			for (int detectedGrid : result.get(stationName).keySet().stream().mapToInt(Integer::intValue).toArray()) {
				ArrayList<String> temptList = new ArrayList<String>();
				temptList.add(stationName);
				temptList.add(String.valueOf(detectedGrid));
				result.get(stationName).get(detectedGrid).forEach(element -> temptList.add(String.valueOf(element)));

				new AtFileWriter(temptList.parallelStream().toArray(String[]::new),
						temptResultFolder + stationName + "_" + detectedGrid + ".txt").textWriter(" ");
			}
		}
	}

	static public AnalysisResult analysisSetting() {
		// start analysis
		AnalysisResult analysis = new AnalysisResult(Global.Global.analysisFolder);

		// set the detected grid
		analysis.setDetectGrid(2);
		analysis.setDetectGrid(1);
		analysis.setDetectGrid(0);

		// set the detected station
		for (String stationName : Global.Global.getUncertaintyPosition().keySet().parallelStream()
				.toArray(String[]::new)) {
			analysis.setStastion(stationName);
		}
		return analysis;
	}

}
