package main.mergeFile;

import java.io.IOException;
import java.text.ParseException;

import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiMerge;
import usualTool.AtFileWriter;
import usualTool.TimeTranslate;

public class MergeAsciiFile {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		TimeTranslate tt = new TimeTranslate();
		String startTime = "20160926 09";
		
		String saveFolder = Global.Global.temptSaveFolder;
		String targetFolder = "S:\\HomeWork\\HighDensityDem\\Simulation\\220m(20m_5+1_1m)\\2016092609_1H_50H\\Buffer100m+20m(20m)_original\\";
		
		for(int index = 0 ; index <= 50 ; index++) {
			String temptTime = tt.milliToDate(tt.StringToLong(startTime , "yyyyMMdd HH")+index * 3600000 , "yyyyMMddHH");
			String fileFilter = String.format("%04d", index);
			String[][] outMerge = new AsciiBasicControl(targetFolder + "dm1d" + fileFilter + ".asc").getAsciiFile();
//			for(int zone = 2 ; zone<=9 ; zone++) {
//				outMerge = new AsciiMerge(outMerge, targetFolder + "dm" + zone + "d" + fileFilter + ".asc").getMergedAscii();
//			}
			new AtFileWriter(outMerge , saveFolder + temptTime + ".asc").textWriter("    ");
		}
	}

}
