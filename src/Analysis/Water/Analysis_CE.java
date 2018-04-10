package Analysis.Water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

import usualTool.AtCommonMath;

public class Analysis_CE {
	private TreeMap<String, String> resultTree = new TreeMap<String, String>();

	public Analysis_CE(TreeMap<String, ArrayList<String>> observation, TreeMap<String, ArrayList<String>> simulation) {

		for (String station : observation.keySet()) {
			double sigmaUp = 0;
			double sigmaDown = 0;

			try {
				ArrayList<String> observationList = observation.get(station);
				ArrayList<String> simulationList = simulation.get(station);

				double observationMean = new AtCommonMath(observationList.parallelStream().toArray(String[]::new)).getMean();

				
				for (int index = 0; index < observationList.size(); index++) {
					sigmaUp = sigmaUp + Math.pow((Double.parseDouble(observationList.get(index))
							- Double.parseDouble(simulationList.get(index))), 2);
				}

				
				for(String temptValue : simulationList) {
					sigmaDown = sigmaDown + Math.pow((Double.parseDouble(temptValue) - observationMean) , 2);
				}
				
				this.resultTree.put(station, new BigDecimal((1-(sigmaUp/sigmaDown))).setScale(3, BigDecimal.ROUND_HALF_UP).toString());
				
				
			} catch (Exception e) {
			}

		}

	}

	public TreeMap<String, String> getResult() {
		return this.resultTree;
	}

}
