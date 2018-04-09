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
				outArray.add(new AsciiBasicControl(floodedFile + eventFile + ".asc").getValue(x,y));
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
	}

	private static String[][] getIotStation() {
		return new String[][] {{"安中五站","162367.190368440000000","2551199.148974450000000"},
			{"海佃四站","165662.855498722000000","2551505.057514020000000"},
			{"海佃三段站","166352.509038435000000","2549777.889128630000000"},
			{"朝皇宮站","166393.082894916000000","2548985.283620740000000"},
			{"龍金站","169920.008159805000000","2551706.181965540000000"},
			{"安中站","167329.839003952000000","2549390.511627280000000"},
			{"頂安站","168461.012654870000000","2549258.957237000000000"},
			{"安和站","169782.704140448000000","2549447.958468440000000"},
			{"溪頂寮站","169520.714143787000000","2547951.882686490000000"},
			{"裕農路裕義路口","172977.183568405000000","2543479.676934840000000"}};
	}

}
