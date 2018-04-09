package Analysis.Water;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtFileReader;

public class AnalysisControl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		

	}
	
	
	private TreeMap<String, ArrayList<String>> getEventObservation(String observationFile) throws IOException {
		TreeMap<String, ArrayList<String>> temptTree = new TreeMap<String, ArrayList<String>>();
		String[][] temptContent = new AtFileReader(observationFile).getContent("\t");

		for (int column = 0; column < temptContent[0].length; column++) {
			ArrayList<String> temptArray = new ArrayList<String>();

			for (int row = 1; row < temptContent.length; row++) {
				temptArray.add(temptContent[row][column]);
			}
			temptTree.put(temptContent[0][column], temptArray);
		}
		return temptTree;
	}
	
	private TreeMap<String, ArrayList<String>> getSimulation(String simulationFile) throws IOException {
		TreeMap<String, ArrayList<String>> temptTree = new TreeMap<String, ArrayList<String>>();
		String[][] temptContent = new AtFileReader(simulationFile).getContent("\t");

		for (int row = 0; row < temptContent.length; row++) {
			ArrayList<String> temptArray = new ArrayList<String>();

			for (int column = 1; row < temptContent[0].length; row++) {
				temptArray.add(temptContent[row][column]);
			}
			temptTree.put(temptContent[row][0], temptArray);
		}
		return temptTree;
	}

}
