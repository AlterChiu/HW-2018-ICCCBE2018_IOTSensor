package Model.GetTheMostPossiblePosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import main.MergeZoneAscii;
import usualTool.AtArrayFunction;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String eventSimulation[] = new String[] { Global.Global.simulation0731, Global.Global.simulation0927,
				Global.Global.simulation0611 };
		String eventObservation[] = new String[] { Global.Global.observation0731, Global.Global.observation0927,
				Global.Global.observation0611 };
		TreeMap<String, ArrayList<String>> analysisTree = new TreeMap<String, ArrayList<String>>();
		TreeMap<String, Double[]> iotTree = getIotStation();

		for (int index = 0; index < eventSimulation.length; index++) {
			System.out.println("start ascii merge");
			new MergeZoneAscii(eventSimulation[index]);
			System.out.println("value detect");
			// return the array, first for
			analysisTree = new GetMostPossiblePosition(5, eventObservation[index], analysisTree).getTreePositionTree();

		}
		for (int stationList = 0 ; stationList<analysisTree.size() ; stationList++) {
			String iotStation = Global.Global.getIotPosition()[stationList][0];
			
			ArrayList<String> displacementList = analysisTree.get(iotStation);
			System.out.print(iotStation + "\t");
			
			try {
				String displace = new AtArrayFunction<String>().getMostReapetTimesValue(displacementList);
			
				
				int row = Integer.parseInt(displace.split("_")[0]);
				int column = Integer.parseInt(displace.split("_")[1]);

				
				System.out.print(iotTree.get(iotStation)[0] + column*20 + "\t");
				System.out.print(iotTree.get(iotStation)[1] + row*20);
				System.out.println();
			} catch (Exception e) {
			}

		}
	}

	private static TreeMap<String, Double[]> getIotStation() {
		TreeMap<String, Double[]> temptTree = new TreeMap<String, Double[]>();
		String iotStations[][] = Global.Global.getIotPosition();
		for (String[] iotStation : iotStations) {
			temptTree.put(iotStation[0],
					new Double[] { Double.parseDouble(iotStation[1]), Double.parseDouble(iotStation[2]) });
		}
		return temptTree;
	}

}
