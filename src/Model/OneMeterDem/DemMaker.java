package Model.OneMeterDem;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import Global.Global;
import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiBuffer;
import asciiFunction.AsciiFillIn;
import usualTool.AtFileWriter;
import usualTool.FileFunction;

public class DemMaker {
	// for originalAscii
	private static int detectedGrid = 5;
	// original cell size
	private static int originalCellSize = 20;
	// buffer cell size
	private static int bufferCellSize = 20;
	// equal to the detectedGrid * original asciiCell size
	private static int bufferRadium = originalCellSize * (2* (detectedGrid )+ 1) / bufferCellSize / 2 +1;
	// save name
	private static String saveName = "buffer100m+20m(20m)";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map<String, String[]> iotPosition = Global.getAllIotPosition_En();
		String saveAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\";

		AsciiBasicControl ascii = new AsciiBasicControl("S:\\HomeWork\\ICCCBE2018\\LevelDEM\\Zone1.asc");
		System.out.println("ascii finish");

		for (String station : iotPosition.keySet()) {
			AsciiBasicControl knAscii = new AsciiBasicControl("C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m(kn).asc");
			AsciiBasicControl oriAscii = new AsciiBasicControl("C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m.asc");
			String nullValue = oriAscii.getProperty().get("noData");
			String nullKnValue = knAscii.getProperty().get("noData");

			System.out.println(station);
			new FileFunction().newFolder(saveAdd + station);
			double x = Double.parseDouble(iotPosition.get(station)[0]);
			double y = Double.parseDouble(iotPosition.get(station)[1]);

			// originalAscii (20m) => get the corrdinate of grid center
			double cellCordinate[] = knAscii.getClosestCoordinate(x, y);
			double cellCordinateX = cellCordinate[0];
			double cellCordinateY = cellCordinate[1];
			
			
			
			//                       zone1 20m clip
//			int gridPosition[] = knAscii.getPosition(x, y);
//			int gridX = gridPosition[0];
//			int girdY = gridPosition[1];
//			for(int row = 1*detectedGrid ; row>=-1*detectedGrid ; row--) {
//				for(int column = -1*detectedGrid ; column<=1*detectedGrid ; column++) {
//					oriAscii.setValue(gridX + column, girdY + row, nullValue);
//					knAscii.setValue(gridX + column, girdY + row, nullKnValue);
//				}
//			}
//			new AtFileWriter(oriAscii.getAsciiFile() , saveAdd + station + "\\ZoneU1_20m(clip).asc").textWriter("    ");
//			new AtFileWriter(knAscii.getAsciiFile() , saveAdd + station + "\\ZoneU1_20m(clip)(kn).asc").textWriter("    ");
//			oriAscii = new AsciiBasicControl("C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m.asc");
			

			// set one meter buffer area
			double bufferMinX = cellCordinateX - bufferRadium * bufferCellSize ;
			double bufferMinY = cellCordinateY - bufferRadium * bufferCellSize;
			double bufferMaxX = cellCordinateX + bufferRadium * bufferCellSize;
			double bufferMaxY = cellCordinateY + bufferRadium * bufferCellSize;
			AsciiFillIn asciiFill = new AsciiFillIn(ascii);
			asciiFill.setBondary(bufferMaxX, bufferMaxY, bufferMinX, bufferMinY);
			String[][] selectedAscii = asciiFill.getFillAscii();
			new AtFileWriter(selectedAscii , saveAdd + station + "\\" + saveName + ".asc").textWriter("    ");
			
			// set kn file
			AsciiBasicControl outKnAscii = new AsciiBasicControl(selectedAscii);
			String[][] outKnAsciiGrid = outKnAscii.getAsciiGrid();
			for (int row = 0; row < outKnAsciiGrid.length; row++) {
				for (int column = 0; column < outKnAsciiGrid[0].length; column++) {
					double[] cordinate = outKnAscii.getCoordinate(column, row);
					outKnAscii.setValue(column, row, knAscii.getValue(cordinate[0], cordinate[1]));
				}
			}
			new AtFileWriter(outKnAscii.getAsciiFile(), saveAdd + station + "\\" + saveName + "(kn).asc").textWriter("    ");

		}
	}
}
