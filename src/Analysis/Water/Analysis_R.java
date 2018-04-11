package Analysis.Water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import usualTool.AtCommonMath;

public class Analysis_R {

	private TreeMap<String, String> resultTree = new TreeMap<String, String>();

	public Analysis_R(TreeMap<String, ArrayList<String>> observation, TreeMap<String, ArrayList<String>> simulation) {

		for (String station : observation.keySet()) {

			try {
				ArrayList<String> observationList = observation.get(station);
				ArrayList<String> simulationList = simulation.get(station);
				ArrayList<Double> simulationListDouble = new ArrayList<Double>();
				simulationList.forEach(element -> simulationListDouble.add(Double.parseDouble(element)));

				double correlation = new AtCommonMath(observationList.parallelStream().toArray(String[]::new))
						.setPresision(10)
						.getCorrelartion(simulationListDouble.stream().mapToDouble(Double::doubleValue).toArray());


				this.resultTree.put(station,
						new BigDecimal(correlation).setScale(3, BigDecimal.ROUND_HALF_UP).toString());

			} catch (Exception e) {
			}

		}

	}

	public TreeMap<String, String> getResult() {
		return this.resultTree;
	}
}
