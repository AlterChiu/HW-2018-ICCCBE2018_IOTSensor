package Model.GetPoint.Selected;

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
			double x = Double.parseDouble(iot[1]);
			double y = Double.parseDouble(iot[2]);
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);

			for (int eventFile = 0; eventFile < new File(floodedFile).list().length; eventFile++) {
				// read the event ascii grid
				outArray.add(new AsciiBasicControl(floodedFile + eventFile + ".asc").getValue(x, y));
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
	}

	private static String[][] getIotStation() {
		return new String[][] {{"海佃四站","165682.855498722","2551525.05751402"},
			{"朝皇宮站","166373.082894916","2549005.28362074"},
			{"龍金站","169900.008159805","2551726.18196554"},
			{"安中站","167329.839003952","2549370.51162728"},
			{"頂安站","168441.01265487","2549278.957237"},
			{"溪頂寮站","169500.714143787","2547971.88268649"} };

		// return Global.getIotPosition();
	}

}
