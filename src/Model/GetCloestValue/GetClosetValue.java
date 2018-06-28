package Model.GetCloestValue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Global.Global;
import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;
import usualTool.AtFileReader;
import usualTool.TimeTranslate;

public class GetClosetValue {
	private TimeTranslate tt = new TimeTranslate();

	// global setting
	private String floodedFile = Global.temptSaveFolder;
	private int detectedGrid;

	// setting the start time
	private String startTime;

	// ascii
	AsciiBasicControl asciiControl;
	double cellSize = 1.;

	// the IoT position
	private Map<String, String[]> detectedPoint = Global.getAllIotPosition_En();
	// output Map
	private Map<String, List<String>> resultTree = new HashMap<String, List<String>>();

	public GetClosetValue(int detectedGrid) throws IOException {
		// TODO Auto-generated method stub
		this.detectedGrid = detectedGrid;
	}

	public Map<String, List<String>> getResultTree() throws IOException, NumberFormatException, ParseException {

		// read the event ascii grid
		// ================================================
		String[] eventFileList = new File(this.floodedFile).list();

		for (String eventFile : eventFileList) {
			System.out.println(eventFile);
			if (detectTime(eventFile)) {
				asciiControl = new AsciiBasicControl(floodedFile + eventFile);
				cellSize = Double.parseDouble(asciiControl.getProperty().get("cellSize"));
			

				// get the iot sensor property
				// ============================================
				for (String station : detectedPoint.keySet()) {
					String stationName = station;
					double x = Double.parseDouble(detectedPoint.get(station)[0]);
					double y = Double.parseDouble(detectedPoint.get(station)[1]);

					// get the value contain in the buffer grid
					// ==============================================
					List<Double> detectBufferGrid = getDetectBufferGrid(x, y);

					// initialization of result map
					// ==============================================
					List<String> stationResultList = getStationResultList(station);

					// skip if there is no data in observation
					// ===============================================
					try {
						int timeIndex = Arrays.asList(eventFileList).indexOf(eventFile);
						double observationValue = Double
								.parseDouble(ControlMain.getEventObservation().get(stationName).get(timeIndex));
						stationResultList
								.add(new AtCommonMath(detectBufferGrid).getClosestValue(observationValue) + "");
						this.resultTree.put(station, stationResultList);
					} catch (Exception e) {
					}
				}
			}
		}
		return this.resultTree;
	}

	private Boolean detectTime(String eventFile) throws ParseException {
		eventFile.substring(0, eventFile.length() - 4);
		return tt.StringToLong(eventFile, "yyyyMMddHH") >= tt.StringToLong(this.startTime, "yyyyMMddHH");
	}

	private List<Double> getDetectBufferGrid(double x, double y) {
		List<Double> temptValue = new ArrayList<Double>();
		// get the selected grid
		// =============================================
		for (int countRow = -1 * (detectedGrid - 1) / 2; countRow <= (detectedGrid - 1) / 2; countRow++) {
			for (int countColumn = -1 * (detectedGrid - 1) / 2; countColumn <= (detectedGrid - 1) / 2; countColumn++) {
				try {
					temptValue.add(Double.parseDouble(
							asciiControl.getValue(x + countColumn * cellSize, y + countColumn * cellSize)));
				} catch (Exception e) {
				}
			}
		}
		return temptValue;
	}

	private List<String> getStationResultList(String station) {
		List<String> stationResultList = this.resultTree.get(station);
		if (stationResultList == null) {
			stationResultList = new ArrayList<String>();
		}
		return stationResultList;
	}

	// set the calculate point
	// =================================================
	public GetClosetValue setPoint(String station, String x, String y) {
		this.detectedPoint.put(station, new String[] { x, y });
		return this;
	}

	public GetClosetValue setAllIotStation() {
		this.detectedPoint = Global.getAllIotPosition_En();
		return this;
	}

	// =====================================================
	public GetClosetValue setStartTime(String startTime) {
		this.startTime = startTime;
		return this;
	}
}