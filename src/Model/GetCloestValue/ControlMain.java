package Model.GetCloestValue;

import java.io.IOException;

import main.MergeZoneAscii;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String eventSimulation = Global.Global.simulation0611;
		String eventObservation = Global.Global.observation0611;
		
		System.out.println("start ascii merge");
		new MergeZoneAscii(eventSimulation);
		System.out.println("value detect");
		new GetClosetValue(5,eventObservation);

	}

}
