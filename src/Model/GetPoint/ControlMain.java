package Model.GetPoint;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import Global.Global;
import usualTool.FileFunction;

public class ControlMain {

	private static String analysisFolder = Global.sobekAnalysisFolder;
	private static String eventSimulation = "";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		
		// clear the tempt save folder
		String saveAdd = Global.temptSaveFolder;
		ff.delAllFile(saveAdd);

		// copy the file to the tempt save folder
		String originalName = "\\dm1d";
		for (int index = 0; index <= 24; index++) {
			ff.copyFile(eventSimulation + originalName + String.format("%04d", index) + ".asc",
					saveAdd + (index) + ".asc");
		}
		
		// get the result 
		GetPoint getPoint = new GetPoint();
		getPoint.setAllPoint();
		Map<String,List<String>> resultMap = getPoint.getResultMap();
		
		// out put the result
		for(String station : resultMap.keySet()) {
			System.out.print(station);
			resultMap.get(station).forEach(e -> System.out.print("\t" + e));
		}
		
	}

}
