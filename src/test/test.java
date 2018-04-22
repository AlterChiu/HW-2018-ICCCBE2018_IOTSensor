package test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import usualTool.AtFileWriter;

public class test {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub

		String fileAdd ="C:\\Sobek213\\TANZ1U01.lit\\DEM\\AnChung\\";
		
		String[][] temptAscii = new AsciiBasicControl(fileAdd + "0.asc").getAsciiFile();
		String[][] temptAsciiKn = new AsciiBasicControl(fileAdd + "0(kn).asc").getAsciiFile();
		
		for(int index = 1 ; index<=8 ; index++) {
			temptAscii = new AsciiMerge(temptAscii , fileAdd + index + ".asc").getMergedAscii();
			temptAsciiKn = new AsciiMerge(temptAsciiKn  ,  fileAdd + index + "(kn).asc").getMergedAscii(); 
		}
		
		new AtFileWriter(temptAscii , fileAdd + "merge.asc").textWriter("    ");
		new AtFileWriter(temptAsciiKn , fileAdd + "merge(kn).asc").textWriter("    ");

	}
}
