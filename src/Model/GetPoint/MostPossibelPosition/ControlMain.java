package Model.GetPoint.MostPossibelPosition;

import java.io.IOException;

import Model.GetCloestValue.GetClosetValue;
import main.MergeZoneAscii;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String eventSimulation = Global.Global.simulation0731;
		
		System.out.println("start ascii merge");
		new MergeZoneAscii(eventSimulation);
		System.out.println("value detect");
		new GetFloodedPoint();

	}

}
