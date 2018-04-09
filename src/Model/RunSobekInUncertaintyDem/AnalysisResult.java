package Model.RunSobekInUncertaintyDem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;

public class AnalysisResult {
	private String indexFolder;
	private ArrayList<Integer> detectGrid = new ArrayList<Integer>();
	private TreeMap<String, Double[]> analysisStation = new TreeMap<String, Double[]>();
	private TreeMap<String, TreeMap<Integer, ArrayList<Double>>> analysisTree = new TreeMap<String, TreeMap<Integer, ArrayList<Double>>>();

	public AnalysisResult(String analysisFolder) {
		this.indexFolder = analysisFolder;
	}

	public TreeMap<String, TreeMap<Integer, ArrayList<Double>>> getAnalysisTree() throws IOException {
		String[] indexFolderList = new File(this.indexFolder).list();
		String asciiName = "dm1d";
		
		// get into the result folder
		for (String folderName : indexFolderList) {
			
			// get into the time step folder => the number the ascii result is the period
			// hours +2
			for (int eventTimeStep = 0; eventTimeStep < new File(indexFolder + folderName).list().length
					- 2; eventTimeStep++) {
				
				// get the ascii information
				AsciiBasicControl ascii = new AsciiBasicControl(
						indexFolder + folderName + "\\" + asciiName + String.format("%04d", eventTimeStep) + ".asc");
				String[][] asciiGrid = ascii.getAsciiGrid();

				// get selected the iotStation
				for (String station : Global.Global.getUncertaintyPosition().keySet().parallelStream()
						.toArray(String[]::new)) {
					double twdX = Double.parseDouble(Global.Global.getUncertaintyPosition().get(station)[0]);
					double twdY = Double.parseDouble(Global.Global.getUncertaintyPosition().get(station)[1]);
					int[] columnRow = ascii.getPosition(twdX, twdY);

					// save tree
					TreeMap<Integer, ArrayList<Double>> saveTree;
					try {
						saveTree = analysisTree.get(station);
					} catch (Exception e) {
						saveTree = new TreeMap<Integer, ArrayList<Double>>();
					}

					// detect by the given grid
					for (int detectGrid : this.detectGrid) {
						// saveList
						ArrayList<Double> closestList;
						try {
							closestList = saveTree.get(detectGrid);
						} catch (Exception e) {
							closestList = new ArrayList<Double>();
						}
						
						for (int row = -1 * detectGrid; row <= detectGrid; row++) {
							for (int column = -1 * detectGrid; column <= detectGrid; column++) {
								closestList
										.add(Double.parseDouble(asciiGrid[columnRow[1] + row][columnRow[0] + column]));
							}
						}

						// get the closest value to the observation
						closestList.add(new AtCommonMath(closestList)
								.getClosestValue(ControlMain.getEventObservation().get(station).get(eventTimeStep)));
					}
				}
			}

		}
		return this.analysisTree;
	}

	// before runtimes
	// ========================================================================
	public AnalysisResult setStastion(String sationName) {
		TreeMap<String, String[]> temptTree = Global.Global.getUncertaintyPosition();

		try {
			String[] temptPosition = temptTree.get(sationName);
			this.analysisStation.put(sationName,
					new Double[] { Double.parseDouble(temptPosition[0]), Double.parseDouble(temptPosition[1]) });
		} catch (Exception e) {
		}
		return this;
	}

	public AnalysisResult setDetectGrid(int grid) {
		this.detectGrid.add(grid);
		return this;
	}

}
