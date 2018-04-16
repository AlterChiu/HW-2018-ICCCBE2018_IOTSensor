package Model.GetCloestValue;

import java.io.IOException;

import main.MergeZoneAscii;
import usualTool.FileFunction;

public class ControlMain_10Min {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileFunction ff = new FileFunction();
		
		String saveAdd = Global.Global.saveFolder_MergeResult;
		ff.delAllFile(saveAdd);
		
//		String eventSimulation = "S:\\HomeWork\\ICCCBE2018\\多次成果\\20160927(09~09+1)\\10min_許\\";
		
		String eventSimulation = "S:\\Users\\alter\\Downloads\\0\\";
		String originalName = "\\dm1d";
		
		for(int index = 0  ; index <=24 ; index++) {
			ff.copyFile(eventSimulation + originalName + String.format("%04d", index) + ".asc" , saveAdd + (index) + ".asc");
		}
		System.out.println("value detect");
		new GetClosetValue(5,"S:\\HomeWork\\ICCCBE2018\\observation\\0927.txt");
	}

}
