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

	private TreeMap<String, ArrayList<Integer>> temptTree;

	public GetMostPossiblePosition(int detectedGrid, String eventObservation,
			TreeMap<String, ArrayList<Integer>> temptTree) throws IOException {
		// TODO Auto-generated method stub
		this.temptTree = temptTree;
		this.eventObservation = eventObservation;

		int getGrid = detectedGrid;
		String iotList[][] = getIotStation();
		this.floodedFile = Global.saveFolder_MergeResult;

		TreeMap<String, ArrayList<String>> observationTree = this.getEventObservation();

		for (String iot[] : iotList) {
			// get the iot sensor property
			// ===============================
			String stationName = iot[0];
			int column = Integer.parseInt(iot[1]);
			int row = Integer.parseInt(iot[2]);

			
			
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);

			String[] eventFileList = new File(this.floodedFile).list();
			for (int eventFile = 0; eventFile < eventFileList.length; eventFile++) {
				// read the event ascii grid
				String[][] ascii = new AsciiBasicControl(floodedFile + eventFile + ".asc").getAsciiGrid();
				ArrayList<Double> temptValue = new ArrayList<Double>();
				try {

					double observationValue = Double.parseDouble(observationTree.get(stationName).get(eventFile));
					// get the selected grid
					if (observationValue > 0.05) {
						for (int countRow = 1 * (getGrid - 1) / 2; countRow >= -1 * (getGrid - 1) / 2; countRow--) {
							for (int countColumn = -1 * (getGrid - 1) / 2; countColumn <= (getGrid - 1)
									/ 2; countColumn++) {

								// only for 5*5 grid array
								temptValue.add(Double.parseDouble(ascii[row + countRow][column + countColumn]));

							}
						}


						int index = new AtCommonMath(temptValue.stream().mapToDouble(Double::doubleValue).toArray())
								.getClosestIndex(observationValue);
						

						try {
							ArrayList<Integer> temptArray = this.temptTree.get(stationName);
							temptArray.add(index);
							this.temptTree.put(stationName, temptArray);
						} catch (Exception e) {
							ArrayList<Integer> temptArray = new ArrayList<Integer>();
							temptArray.add(index);
							this.temptTree.put(stationName, temptArray);
						}

					}
				} catch (Exception e) {

				}
			}
		}

	}

	public TreeMap<String, ArrayList<Integer>> getTreePositionTree() {
		return this.temptTree;
	}

	private static String[][] getIotStation() {
		return new String[][] { { "安中五站", "372", "356" }, { "海佃四站", "536", "341" }, { "海佃三段站", "571", "427" },
			{ "朝皇宮站", "573", "467" }, { "龍金站", "749", "331" }, { "安中站", "620", "447" }, { "頂安站", "676", "453" },
			{ "安和站", "742", "444" }, { "溪頂寮站", "729", "519" }, { "裕農路裕義路口", "903", "742" } };
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