package OriginalData.Ascii.Merge;

import java.io.IOException;

import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiMerge;
import usualTool.AtFileWriter;
import Global.Global;

public class MergeZoneAscii {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// the location of index folder which contains each zone data
		String fileAdd = "C:\\HomeWork\\山峰可可\\";
		String saveAdd = "C:\\HomeWork\\山峰可可\\mergedAscii\\";

		// the name of folder that data want to merge
		String[] zoneNameList = new String[] { "U1", "U2", "Z4" };
		String path = "\\dm1d0000.asc";

		
		
		for (int hours = 0; hours <= Global.eventTime; hours++) {
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
