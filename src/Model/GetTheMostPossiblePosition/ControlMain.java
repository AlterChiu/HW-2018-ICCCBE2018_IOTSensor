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
		TreeMap<String, ArrayList<Integer>> analysisTree = new TreeMap<String, ArrayList<Integer>>();
		TreeMap<String, Integer[]> iotTree = getIotStation();

		for (int index = 0; index < eventSimulation.length; index++) {
			System.out.println("start ascii merge");
			new MergeZoneAscii(eventSimulation[index]);
			System.out.println("value detect");
			// return the array, first for
			analysisTree = new GetMostPossiblePosition(5, eventObservation[index], analysisTree).getTreePositionTree();

		}
		for (String iotStation : iotTree.keySet().parallelStream().toArray(String[]::new)) {
			ArrayList<Integer> displacementList = analysisTree.get(iotStation);

			try {
				int displace = new AtArrayFunction<Integer>().getMostReapetTimesValue(displacementList);
				System.out.print(iotStation + "\t");
				
				int row = displace/5;
				int column = displace - row*5;
				
				row = 2 - row;
				column = column-2;
				
//				System.out.print(displace +  "\t");
//				System.out.print(row +  "\t");
//				System.out.print(column);
				
				System.out.print(iotTree.get(iotStation)[0] + column + "\t");
				System.out.print(iotTree.get(iotStation)[1] + row);
				System.out.println();
			} catch (Exception e) {
			}

		}
	}

	private static TreeMap<String, Integer[]> getIotStation() {
		TreeMap<String, Integer[]> temptTree = new TreeMap<String, Integer[]>();
		String iotStations[][] = new String[][] {{ "安中五站", "372", "356" }, { "海佃四站", "536", "341" }, { "海佃三段站", "571", "427" },
			{ "朝皇宮站", "573", "467" }, { "龍金站", "749", "331" }, { "安中站", "620", "447" }, { "頂安站", "676", "453" },
			{ "安和站", "742", "444" }, { "溪頂寮站", "729", "519" }, { "裕農路裕義路口", "903", "742" }  };
		for (String[] iotStation : iotStations) {
			temptTree.put(iotStation[0],
					new Integer[] { Integer.parseInt(iotStation[1]), Integer.parseInt(iotStation[2]) });
		}
		return temptTree;
	}

}
