package Model.OneMeterDem;

import java.io.IOException;
import java.util.Map;

import asciiFunction.AsciiBasicControl;
import usualTool.AtFileWriter;

public class TwentyMeterDem {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> iotPosition = Global.Global.getAllIotPosition_En();
		String saveAdd = "S:\\\\HomeWork\\\\ICCCBE2018\\\\LevelDEM\\\\IOT_1m\\\\";
		String asciiAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\Hy_Dem(20m).asc";
		String asciiKnAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\ZoneU1_20m(kn).asc";

		for (String position : iotPosition.keySet()) {
			AsciiBasicControl totalAscii = new AsciiBasicControl(asciiAdd);
			AsciiBasicControl totalAsciiKn = new AsciiBasicControl(asciiKnAdd);

			double iotX = Double.parseDouble(iotPosition.get(position)[0]);
			double iotY = Double.parseDouble(iotPosition.get(position)[1]);

			int[] center = totalAscii.getPosition(iotX, iotY);


			String[][] outAscii = totalAscii.getClipAsciiFile(center[0]-6, center[1]-6,
					center[0]+6, center[1]+6);
			
			String[][] outAsciiKn = totalAsciiKn.getClipAsciiFile(center[0]-6, center[1]-6,
					center[0]+6, center[1]+6);
			
			new AtFileWriter(outAscii , saveAdd + position + "\\buffer100m+20m(20m)_Hy.asc").textWriter("    ");
			new AtFileWriter(outAsciiKn , saveAdd + position + "\\buffer100m+20m(20m)_Hy(kn).asc").textWriter("    ");
			
		}

	}

}
