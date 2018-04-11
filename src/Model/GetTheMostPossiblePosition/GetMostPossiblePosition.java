package Model.GetTheMostPossiblePosition;

import java.io.File;
import java.io.IOException;
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

	private TreeMap<String, ArrayList<String>> temptTree;

	public GetMostPossiblePosition(int detectedGrid, String eventObservation,
			TreeMap<String, ArrayList<String>> temptTree) throws IOException {
		// TODO Auto-generated method stub
		this.temptTree = temptTree;
		this.eventObservation = eventObservation;

		int getGrid = detectedGrid;
		String iotList[][] = getIotStation();
		this.floodedFile = Global.saveFolder_MergeResult;

		TreeMap<String, ArrayList<String>> observationTree = this.getEventObservation();
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

				try {
					String key = "";
					double minDis = 999;

					double observationValue = Double.parseDouble(observationTree.get(stationName).get(eventFile));
					// get the selected grid
					if (observationValue > 0.05) {
						for (int countRow = 1 * (getGrid) / 2; countRow >= -1 * (getGrid) / 2; countRow--) {
							for (int countColumn = -1 * (getGrid) / 2; countColumn <= (getGrid) / 2; countColumn++) {

								double dis = Math.abs(Double.parseDouble(ascii[row + countRow][column + countColumn])
										- observationValue);
								if (dis < minDis) {
									key = countRow + "_" + countColumn;
									minDis = dis;
								}
							}
						}
						
						System.out.println(key);

						try {
							ArrayList<String> temptArray = this.temptTree.get(stationName);
							temptArray.add(key);
							this.temptTree.put(stationName, temptArray);
						} catch (Exception e) {
							ArrayList<String> temptArray = new ArrayList<String>();
							temptArray.add(key);
							this.temptTree.put(stationName, temptArray);
						}

					}
				} catch (Exception e) {

				}
			}
		}

	}

	public TreeMap<String, ArrayList<String>> getTreePositionTree() {
		return this.temptTree;
	}

	private static String[][] getIotStation() {
		return Global.getIotPosition();
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