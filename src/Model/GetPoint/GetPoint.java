package Model.GetPoint;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asciiFunction.AsciiBasicControl;

public class GetPoint {
	private Map<String, String[]> detectedPoint = new HashMap<String, String[]>();
	private Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
	private String analysisFolder = Global.Global.sobekAnalysisFolder;

	public Map<String, List<String>> getResultMap() throws IOException {
		int fileNum = new File(this.analysisFolder).list().length;

		// read ascii file
		for (int index = 0; index < fileNum; index++) {
			AsciiBasicControl ascii = new AsciiBasicControl(this.analysisFolder + index + ".asc");

			// read station
			for (String station : this.detectedPoint.keySet()) {
				// initialization staion result
				List<String> stationResultList = this.resultMap.get(station);
				if (stationResultList == null) {
					stationResultList = new ArrayList<String>();
				}

				stationResultList.add(ascii.getValue(Double.parseDouble(this.detectedPoint.get(station)[0]),
						Double.parseDouble(this.detectedPoint.get(station)[1])));
				this.resultMap.put(station, stationResultList);
			}
		}
		return this.resultMap;
	}

	public GetPoint setPoint(String station, String x, String y) {
		this.detectedPoint.put(station, new String[] { x, y });
		return this;
	}

	public GetPoint setAllPoint() {
		this.detectedPoint = Global.Global.getAllIotPosition_En();
		return this;
	}

}
