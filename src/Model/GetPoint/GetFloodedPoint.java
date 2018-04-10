package Model.GetPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import Global.Global;

public class GetFloodedPoint {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String iotList[][] = getIotStation();

		String floodedFile = "S:\\HomeWork\\ICCCBE2018\\mergedAscii\\";

		for (String iot[] : iotList) {
			String stationName = iot[0];
			double x = Double.parseDouble(iot[1]);
			double y = Double.parseDouble(iot[2]);
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);

			for (int eventFile = 0; eventFile <= Global.eventTime; eventFile++) {
				// read the event ascii grid
				outArray.add(new AsciiBasicControl(floodedFile + eventFile + ".asc").getValue(x, y));
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
	}

	private static String[][] getIotStation() {
//		return Global.getIotPosition();
		
		return new String[][] {
			{"安中五站","162387.19036844","2551179.14897445"},
			{"海佃三段站","166372.509038435","2549757.88912863"},
			{"龍金站","169940.008159805","2551686.18196554"},
			{"安中站","167349.839003952","2549370.51162728"},
			{"頂安站","168481.01265487","2549238.957237"},
			{"安和站","169802.704140448","2549427.95846844"}
		};
	}

}
