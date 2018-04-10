package Analysis.Water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtCommonMath;

public class Analysis_RMSE {

	private TreeMap<String, String> resultTree = new TreeMap<String, String>();

	public Analysis_RMSE(TreeMap<String, ArrayList<String>> observation,
			TreeMap<String, ArrayList<String>> simulation) {

		for (String stationName : observation.keySet()) {
			try {
				ArrayList<String> observationList = observation.get(stationName);
				ArrayList<String> simulationList = simulation.get(stationName);

				double temptValue = 0;
				for (int index = 0; index < observationList.size(); index++) {
					temptValue = temptValue + Math.pow(Double.parseDouble(observationList.get(index))
							- Double.parseDouble(simulationList.get(index)), 2);
				}

				this.resultTree.put(stationName, Math.pow(temptValue / observationList.size(), 0.5) + "");

			} catch (Exception e) {

			}
		}

	}
	
	public TreeMap<String, String> getResult() {
		return this.resultTree;
	}

}
