package Model.GetCloestValue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import Global.Global;
import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import usualTool.AtFileReader;

public class GetClosetValue {

	private String floodedFile = Global.temptSaveFolder;
	private String eventObservation;
	private int detectedGrid;
	private Map<String, String[]> detectedPoint = new HashMap<String, String[]>();
	private Map<String, String> resultTree = new HashMap<String, String>();
	private Map<String, ArrayList<String>> observationTree = this.getEventObservation();

	public GetClosetValue(int detectedGrid, String eventObservation) throws IOException {
		// TODO Auto-generated method stub
		this.eventObservation = eventObservation;
		this.detectedGrid = detectedGrid;
	}

	
	
	public Map<String, String> getResultTree() throws IOException {
		// read the event ascii grid
		// ================================================
		String[] eventFileList = new File(this.floodedFile).list();
		for (int eventFile = 0; eventFile < eventFileList.length; eventFile++) {
			AsciiBasicControl asciiControl = new AsciiBasicControl(floodedFile + eventFile + ".asc");
			double cellSize = Double.parseDouble(asciiControl.getProperty().get("cellSize"));

			// get the iot sensor property
			// ============================================
			for (String station : detectedPoint.keySet()) {
				String stationName = station;
				double x = Double.parseDouble(detectedPoint.get(station)[0]);
				double y = Double.parseDouble(detectedPoint.get(station)[1]);

				List<String> temptValue = new ArrayList<String>();
				// get the selected grid
				// =============================================
				for (int countRow = -1 * (detectedGrid - 1) / 2; countRow <= (detectedGrid - 1) / 2; countRow++) {
					for (int countColumn = -1 * (detectedGrid - 1) / 2; countColumn <= (detectedGrid - 1)
							/ 2; countColumn++) {
						try {
							temptValue
									.add(asciiControl.getValue(x + countColumn * cellSize, y + countColumn * cellSize));
						} catch (Exception e) {
						}
					}
				}
				try {
					double observationValue = Double.parseDouble(observationTree.get(stationName).get(eventFile));
					resultTree.put(station,
							new AtCommonMath(
									temptValue.stream().map(e -> Double.parseDouble(e)).collect(Collectors.toList()))
											.getClosestValue(observationValue)
									+ "");
				} catch (Exception e) {
				}
			}
		}
		return this.resultTree;
	}

	public GetClosetValue setPoint(String station, String x, String y) {
		this.detectedPoint.put(station, new String[] { x, y });
		return this;
	}

	public GetClosetValue getAllIotStation() {
		this.detectedPoint = Global.getAllIotPosition_En();
		return this;
	}

	private TreeMap<String, ArrayList<String>> getEventObservation() throws IOException {
		TreeMap<String, ArrayList<String>> temptTree = new TreeMap<String, ArrayList<String>>();
		String[][] temptContent = new AtFileReader(this.eventObservation).getContent("\t");

		for (int column = 0; column < temptContent[0].length; column++) {
			ArrayList<String> temptArray = new ArrayList<String>();

			for (int row = 1; row < temptContent.length; row++) {
				temptArray.add(temptContent[row][column]);
			}
			temptTree.put(temptContent[0][column], temptArray);
		}
		return temptTree;
	}

}