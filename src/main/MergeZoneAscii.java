package main;

import java.io.File;
import java.io.IOException;

import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiMerge;
import usualTool.AtFileWriter;
import usualTool.FileFunction;
import Global.Global;

public class MergeZoneAscii {

	public MergeZoneAscii(String eventSimulationFileAdd) throws IOException{
		// TODO Auto-generated method stub

		// the location of index folder which contains each zone data
		String fileAdd = eventSimulationFileAdd;
		String saveAdd = Global.saveFolder_MergeResult;

		// clear the tmept save folder
		new FileFunction().delAllFile(saveAdd);
		
		// the name of folder that data want to merge
		String[] zoneNameList = new String[] { "U1", "U2", "Z4" };
		String path = "\\dm1d0000.asc";

		// get the time steps in simulation folder
		int dataSteps = new File(fileAdd + zoneNameList[0] + "\\").list().length;
		
		for (int hours = 0; hours < dataSteps; hours++) {
			// start from the first zone
			String[][] mergedAscii = new AsciiBasicControl(fileAdd + zoneNameList[0] + "\\" + hours + "\\" + path)
					.cutFirstColumn().getAsciiFile();

			for (int zoneList = 1; zoneList < zoneNameList.length; zoneList++) {
				mergedAscii = new AsciiMerge(mergedAscii, fileAdd + zoneNameList[zoneList] + "\\" + hours + "\\" + path)
						.getMergedAscii();
			}
			new AtFileWriter(mergedAscii , saveAdd + hours + ".asc").textWriter("    ");
		}

	}

}
