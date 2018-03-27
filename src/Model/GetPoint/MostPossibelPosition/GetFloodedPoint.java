package Model.GetPoint.MostPossibelPosition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import Global.Global;

public class GetFloodedPoint {

	public GetFloodedPoint() throws IOException {
		// TODO Auto-generated method stub
		String iotList[][] = getIotStation();

		String floodedFile = Global.saveFolder_MergeResult;

		for (String iot[] : iotList) {
			String stationName = iot[0];
			int column = Integer.parseInt(iot[1]);
			int row = Integer.parseInt(iot[2]);
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);

			for (int eventFile = 0; eventFile < new File(floodedFile).list().length; eventFile++) {
				// read the event ascii grid
				outArray.add(new AsciiBasicControl(floodedFile + eventFile + ".asc").getAsciiGrid()[row][column]);
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
	}

	private static String[][] getIotStation() {
		return new String[][] {{"安中站","618","447"},
			{"安和站","741","445"},
			{"朝皇宮站","573","468"},
			{"海佃三段站","570","427"},
			{"海佃四站","534","339"},
			{"裕農路裕義路口","905","743"},
			{"頂安站","677","454"}};
	}

}
