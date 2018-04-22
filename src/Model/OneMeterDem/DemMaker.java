package Model.OneMeterDem;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import Global.Global;
import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiBuffer;
import usualTool.AtFileWriter;
import usualTool.FileFunction;

public class DemMaker {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Map<String, String[]> iotPosition = Global.getAllIotPosition_En();
		String saveAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\";
		AsciiBasicControl knAscii = new AsciiBasicControl( "C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m(kn).asc");

		AsciiBasicControl ascii = new AsciiBasicControl("S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\merge.asc");
		System.out.println("ascii finish");
		double cellSize = Double.parseDouble(knAscii.getProperty().get("cellSize"));

		for (String key : iotPosition.keySet()) {
			System.out.println(key);
			new FileFunction().newFolder(saveAdd + key);
			double x = Double.parseDouble(iotPosition.get(key)[0]);
			double y = Double.parseDouble(iotPosition.get(key)[1]);

			for (int row = 1; row >= -1; row--) {
				for (int column = -1; column <= 1; column++) {
					int index = -(row-1)*3 + (column+1);
					double temptX = x + column * cellSize;
					double temptY = y + row * cellSize;

					AsciiBuffer bufferAscii = new AsciiBuffer(ascii.getAsciiFile());
					String[][] selectedAscii = bufferAscii.setPoint(temptX, temptY).getSelecBufferAscii(10);

					AsciiBasicControl outKnAscii =new AsciiBasicControl(selectedAscii);
					outKnAscii.setValue(temptX, temptY, knAscii.getValue(temptX, temptY));
					
					new AtFileWriter(selectedAscii , saveAdd +key + "\\" + index + ".asc").textWriter("    ");
					new AtFileWriter(outKnAscii.getAsciiFile() , saveAdd + key + "\\" + index + "(kn).asc").textWriter("    ");
				}

			}

		}
	}
}
