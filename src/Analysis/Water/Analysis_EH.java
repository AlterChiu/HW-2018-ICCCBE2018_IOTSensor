package Analysis.Water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtArrayFunction;
import usualTool.AtCommonMath;

public class Analysis_EH {

	private TreeMap<String, String> EHPTree = new TreeMap<String, String>();
	private TreeMap<String, String> ETPTree = new TreeMap<String, String>();
	private TreeMap<String,String>  EHTree = new TreeMap<String,String>();

	public Analysis_EH(TreeMap<String,ArrayList<String>> observation , TreeMap<String,ArrayList<String>> simulation) {
		
		for(String stationName : observation.keySet()) {
			try {
				ArrayList<String> observationList = observation.get(stationName);
				ArrayList<String> simulationList = simulation.get(stationName);
				
				AtCommonMath simMath = new AtCommonMath(simulationList.parallelStream().toArray(String[]::new));
				AtCommonMath obsMath = new AtCommonMath(observationList.parallelStream().toArray(String[]::new));
				
				double simMax = simMath.getMax();
				int simMaxIndex = simMath.getClosestIndex(simMax);
				
				double obsMax = obsMath.getMax();
				int obsMaxIndex = obsMath.getClosestIndex(obsMax);
				
				
						
				try {
					this.EHPTree.put(stationName, new BigDecimal((simMax-obsMax)/obsMax *100).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}catch(Exception e) {
					this.EHPTree.put(stationName, "null");
				}
				
				try {
					this.ETPTree.put(stationName, simMaxIndex-obsMaxIndex + "");
				}catch(Exception e) {
					this.ETPTree.put(stationName, "null");
				}
				
				try {
					this.EHTree.put(stationName, new BigDecimal((simMax-obsMax)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}catch(Exception e) {
					this.EHTree.put(stationName, "null");
				}
			}catch(Exception e) {
				
			}
		}
		
		
	}

	public TreeMap<String, String> getEHP() {
		return this.EHPTree;
	}
	public TreeMap<String,String> getETP(){
		return this.ETPTree;
	}
	public TreeMap<String,String> getEH(){
		return this.EHTree;
	}
}
