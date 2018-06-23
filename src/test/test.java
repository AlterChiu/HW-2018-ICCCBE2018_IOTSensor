package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import SOBEK.Runtimes;
import asciiFunction.AsciiBasicControl;
import asciiFunction.AsciiMerge;
import usualTool.AtCommonMath;
import usualTool.AtFileReader;
import usualTool.AtFileWriter;

public class test {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub

		String fileList[] = Global.Global.getAllIotPosition_En().keySet().parallelStream().toArray(String[]::new);
		String fileAdd = "S:\\HomeWork\\ICCCBE2018\\LevelDEM\\IOT_1m\\";

		String targetList[] = new String[] { "buffer30m+1m(1m)"};

		for (String ascii : targetList) {
			String[][] mergeAscii = new AsciiBasicControl(fileAdd + fileList[0] + "\\" + ascii + ".asc").getAsciiFile();
			String[][] mergeAsciiKn = new AsciiBasicControl(fileAdd + fileList[0]+"\\" + ascii + "(kn).asc").getAsciiFile();
			for (int index = 1; index < fileList.length; index++) {
				mergeAscii = new AsciiMerge(mergeAscii , fileAdd + fileList[index] + "\\" + ascii + ".asc").getMergedAscii();
				mergeAsciiKn = new AsciiMerge(mergeAsciiKn , fileAdd + fileList[index] + "\\" + ascii + "(kn).asc").getMergedAscii();
			}
			new AtFileWriter(mergeAscii , fileAdd + ascii + ".asc").textWriter("    ");
			new AtFileWriter(mergeAsciiKn , fileAdd + ascii + "(kn).asc").textWriter("    ");
			
		}

	}
}
