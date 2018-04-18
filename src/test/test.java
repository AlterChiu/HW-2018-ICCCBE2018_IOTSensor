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

public class test {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub

		TreeMap<String,ArrayList< String>> tr = new TreeMap<String, ArrayList<String>>();
		
	
		System.out.println(tr.get("23").get(0));

	}
}
