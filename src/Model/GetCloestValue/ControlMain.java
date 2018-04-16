package Model.GetCloestValue;

import java.io.IOException;

import main.MergeZoneAscii;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
//		String eventSimulation = Global.Global.simulation0927;
		String eventSimulation = Global.Global.simulation0927;
		
		String eventObservation = Global.Global.observation0927;
		
		System.out.println("start ascii merge");
		new MergeZoneAscii(eventSimulation);
		System.out.println("value detect");
		new GetClosetValue(3,eventObservation);

	}

}
