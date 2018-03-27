package Model.GetPoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import Global.Global;

public class GetFloodedPoint {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String iotList[][] = getIotStation();

		String floodedFile = "C:\\HomeWork\\山峰可可\\mergedAscii\\";

		for (String iot[] : iotList) {
			String stationName = iot[0];
			int column = Integer.parseInt(iot[1]);
			int row = Integer.parseInt(iot[2]);
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);

			for (int eventFile = 0; eventFile <= Global.eventTime; eventFile++) {
				// read the event ascii grid
				String[][] ascii = new AsciiBasicControl(floodedFile + eventFile + ".asc").getAsciiGrid();
				outArray.add(ascii[row][column]);
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
	}

	private static String[][] getIotStation() {
		return new String[][] { { "安中五站", "372", "356" }, { "海佃四站", "536", "341" }, { "海佃三段站", "571", "427" },
				{ "朝皇宮站", "573", "467" }, { "龍金站", "749", "331" }, { "安中站", "620", "447" }, { "頂安站", "676", "453" },
				{ "安和站", "742", "444" }, { "溪頂寮站", "729", "519" }, { "裕農路裕義路口", "903", "742" } };
	}

}
