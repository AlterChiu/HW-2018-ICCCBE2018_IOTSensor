package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class test {

	public static void main(String[] args) throws IOException, DocumentException {
		// TODO Auto-generated method stub
//		new DemUncertainty(Global.Global.originalDem).createNewDem(Global.Global.sobekRuntimeDem);
	String fileAdd = "C:\\FEWS\\FEWS_Taiwan_2017\\Taiwan\\Modules\\WRA\\Taiwan\\Southern\\Tainan\\Sobek\\Zone1\\Zone1Merge\\temptSaveFolder\\0\\Output.xml";
	
	Document document = new SAXReader().read(new File(fileAdd));
	List<Node> nodes = document.selectNodes("head");
	
	for(Node node : nodes) {
		//System.out.println(node.selectSingleNode("locationId").getText());
	}
	}
}
