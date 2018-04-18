package Model.OneMeterDem;

import java.io.IOException;
import java.util.TreeMap;

import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiBuffer;
import usualTool.AtFileWriter;
import usualTool.FileFunction;

public class DemMaker {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		TreeMap<String, String[]> iotPosition = Global.Global.getAllIotPosition();
		String saveAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\";

		AsciiBasicControl ascii = new AsciiBasicControl("S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\merge.asc");
		System.out.println("ascii finish");
		double cellSize = Double.parseDouble(ascii.getProperty().get("cellSize"));

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
					String[][] selectedAscii = bufferAscii.setPoint(temptX, temptY).getSelecBufferAscii(20);
					new AtFileWriter(selectedAscii , saveAdd +key + "\\" + index + ".asc").textWriter("    ");
				}

			}

		}
	}
}
