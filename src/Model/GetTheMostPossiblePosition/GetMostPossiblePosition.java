package Model.GetTheMostPossiblePosition;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import Global.Global;
import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import usualTool.AtFileReader;

public class GetMostPossiblePosition {

	private String floodedFile;
	private String eventObservation;

	private TreeMap<String, ArrayList<Double>> observationTree;
	private TreeMap<String, TreeMap<String, ArrayList<Double>>> outTree;

	public GetMostPossiblePosition(int detectedGrid, String eventObservation) throws IOException {
		// TODO Auto-generated method stub
		this.eventObservation = eventObservation;
		this.outTree = new TreeMap<String, TreeMap<String, ArrayList<Double>>>();

		int getGrid = detectedGrid;
		String iotList[][] = getIotStation();
		this.floodedFile = Global.temptSaveFolder;

		this.observationTree = this.getEventObservation();
		String[] eventFileList = new File(this.floodedFile).list();
		for (int eventFile = 0; eventFile < eventFileList.length; eventFile++) {
			// read the event ascii grid
			AsciiBasicControl asciiControl = new AsciiBasicControl(floodedFile + eventFile + ".asc");
			String[][] ascii = asciiControl.getAsciiGrid();

			for (String iot[] : iotList) {
				// get the iot sensor property
				// ===============================
				String stationName = iot[0];
				double x = Double.parseDouble(iot[1]);
				double y = Double.parseDouble(iot[2]);

				ArrayList<String> outArray = new ArrayList<String>();
				outArray.add(stationName);

				int[] position = asciiControl.getPosition(x, y);
				int column = position[0];
				int row = position[1];

				double observationValue = observationTree.get(stationName).get(eventFile);
				// get the selected grid

				// create new treeMap of position
				TreeMap<String, ArrayList<Double>> temptTree = this.outTree.get(stationName);
				if (temptTree == null) {
					temptTree = new TreeMap<String, ArrayList<Double>>();
				}

				// detect selected grid
				for (int countRow = 1 * (getGrid / 2); countRow >= -1 * (getGrid / 2); countRow--) {
					for (int countColumn = -1 * (getGrid / 2); countColumn <= (getGrid / 2); countColumn++) {
						String positionKey = countRow + "_" + countColumn;

						// create new array
						ArrayList<Double> temptArray;
						try {
							temptArray = this.outTree.get(stationName).get(positionKey);
						} catch (Exception e) {
							temptArray = new ArrayList<Double>();
						}

						// put the arraylist value to position tree

						temptArray.add(Double.parseDouble(ascii[row + countRow][column + countColumn]));
						temptTree.put(positionKey, temptArray);

						temptArray.clear();

					}
				}

				// put the out tree back
				this.outTree.put(stationName, temptTree);
				temptTree.clear();
			}
		}
	}

	public TreeMap<String, TreeMap<String, ArrayList<Double>>> getTreePositionTree() {
		return this.outTree;
	}

	private static String[][] getIotStation() {
		return Global.getZoneOneIotPosition();
	}

	public TreeMap<String, String> getCorrelation() {
		TreeMap<String, String> correlation = new TreeMap<String, String>();
		for (String station : this.outTree.keySet()) {

			double maxDis = -999;
			String key = "0_0";

			for (String positionKey : this.outTree.get(station).keySet()) {
				try {
					double cor = new AtCommonMath(this.observationTree.get(station)).getCorrelartion(this.outTree
							.get(station).get(positionKey).stream().mapToDouble(Double::doubleValue).toArray());

					if (cor > maxDis) {
						key = positionKey;
						maxDis = cor;
					}
				} catch (Exception e) {
				}
			}
			correlation.put(station, key);
		}
		return correlation;
	}

	private TreeMap<String, ArrayList<Double>> getEventObservation() throws IOException {
		TreeMap<String, ArrayList<Double>> temptTree = new TreeMap<String, ArrayList<Double>>();
		String[][] temptContent = new AtFileReader(this.eventObservation).getContent("\t");

		for (int column = 0; column < temptContent[0].length; column++) {
			ArrayList<Double> temptArray = new ArrayList<Double>();
			for (int row = 1; row < temptContent.length; row++) {
				temptArray.add(Double.parseDouble(temptContent[row][column]));
			}
			temptTree.put(temptContent[0][column], temptArray);
		}
		return temptTree;
	}

}