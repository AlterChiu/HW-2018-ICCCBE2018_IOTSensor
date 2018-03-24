package Model.GetTheMostPossiblePosition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import Model.GetCloestValue.GetClosetValue;
import main.MergeZoneAscii;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String eventSimulation = Global.Global.simulation0731;
		String eventObservation = Global.Global.observation0731;
		TreeMap<String,ArrayList<Integer[]>> analysisTree = new TreeMap<String,ArrayList<Integer[]>>();
		
		
		System.out.println("start ascii merge");
		new MergeZoneAscii(eventSimulation);
		System.out.println("value detect");
		new GetMostPossiblePosition(5,eventObservation,analysisTree);
		
		
		for(String iotStation : analysisTree.keySet().parallelStream().toArray(String[]::new)) {
			
		}

	}

}
