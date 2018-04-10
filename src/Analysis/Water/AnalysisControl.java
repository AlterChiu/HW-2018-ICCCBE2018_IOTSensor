package Analysis.Water;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtFileReader;

public class AnalysisControl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String simulationFile ="S:\\HomeWork\\ICCCBE2018\\多次成果\\Water\\tempt.txt";
		String observationFile = Global.Global.observation0927_10min;
		
		TreeMap<String,ArrayList<String>> observation = getEventObservation(observationFile);
		TreeMap<String,ArrayList<String>> simulation = getSimulation(simulationFile);
		
		TreeMap<String,String> CE = new Analysis_CE(observation , simulation).getResult();	
		TreeMap<String,String> RMSE = new Analysis_RMSE(observation , simulation).getResult();	
		Analysis_EH EH = new Analysis_EH(observation , simulation);	
		
		TreeMap<String,String> EHp = EH.getEHP();
		TreeMap<String,String> ETp = EH.getETP();
		
		
		for(String station : observation.keySet()) {
			System.out.print(station + "\t");
			System.out.print(CE.get(station) + "\t");
			System.out.print(RMSE.get(station) + "\t");
			System.out.print(EHp.get(station) + "\t");
			System.out.println(ETp.get(station) +"\t");
		}
		
		
		

	}
	
	
	private static TreeMap<String, ArrayList<String>> getEventObservation(String observationFile) throws IOException {
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
	
	private  static TreeMap<String, ArrayList<String>> getSimulation(String simulationFile) throws IOException {
		TreeMap<String, ArrayList<String>> temptTree = new TreeMap<String, ArrayList<String>>();
		String[][] temptContent = new AtFileReader(simulationFile).getContent("\t");

		for (int row = 0; row < temptContent.length; row++) {
			ArrayList<String> temptArray = new ArrayList<String>();

			for (int column = 1; column < temptContent[0].length; column++) {
				temptArray.add(temptContent[row][column]);
			}
			temptTree.put(temptContent[row][0], temptArray);
		}
		return temptTree;
	}

}
