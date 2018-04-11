package Analysis.Water.oneD;

import java.util.List;

import org.dom4j.Node;

import usualTool.AtXmlReader;

public class OneDimensionValue {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String fileAdd  = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160611(20~20+1)\\0410\\AhnSung.xml";
		
		List<Node> values = new AtXmlReader(fileAdd).getNodes("event");
		
		
		for(Node value : values) {
			System.out.println(value.valueOf("@value"));
		}
		
		

	}

}
