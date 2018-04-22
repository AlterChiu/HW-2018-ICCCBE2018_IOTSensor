package Model.Vm.Function;

import java.io.File;

import Global.Global;
import usualTool.FileFunction;

public class MoveFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String originalFileAdd = Global.sobekResult;
		String saveFileAdd = Global.temptSaveFolder;
		int fileNum = new File(saveFileAdd).list().length;
		
//		String originalFileAdd = "S:\\Users\\alter\\Desktop\\Test\\firstFolder\\";
//		String saveFileAdd = "S:\\Users\\alter\\Desktop\\Test\\thirdFolder\\";
		
		new FileFunction().copyFolder(originalFileAdd, saveFileAdd + fileNum);
	}

}
