package main;

import java.io.IOException;
import java.util.ArrayList;

import asciiFunction.AsciiBasicControl;

public class GetPositionDEM {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

String fileAdd  = "C:\\HomeWork\\山峰可可\\LevelDEM\\";
		
		AsciiBasicControl ascii = new AsciiBasicControl(fileAdd + "total.asc").cutFirstColumn();
		String[][] asciiContent = ascii.getAsciiGrid();
		String noData = ascii.getProperty().get("noData");
		
		
		String iotList[][] = new String[][] {
			{"安中五站","162367.190368440","2551199.148974450"},
			{"海佃四站","165662.855498722","2551505.057514020"},
			{"海佃三段站","166352.509038435","2549777.889128630"},
			{"朝皇宮站","166393.082894916","2548985.283620740"},
			{"龍金站","169920.008159805","2551706.181965540"},
			{"安中站","167329.839003952","2549390.511627280"},
			{"頂安站","168461.012654870","2549258.957237000"},
			{"安和站","169782.704140448","2549447.958468440"},
			{"溪頂寮站","169520.714143787","2547951.882686490"},
			{"裕農路裕義路口","172997.183568405","2543479.676934840"}
			};


		
		ArrayList<String> outArray = new ArrayList<String>();
		
		for(String iot[] : iotList) {
			int[] position = ascii.getPosition(Double.parseDouble(iot[1] ), Double.parseDouble(iot[2]));
			int column =position[0];
			int row  =position[1];
			
			String name = iot[0];
			outArray.add(name);
			for(int rowCount=-2;rowCount<=2;rowCount++) {
				String tempt ="";
				for(int columnCount=-2 ;columnCount<=2;columnCount++) {
					if(Double.parseDouble(asciiContent[row + rowCount][column + columnCount]) >Double.parseDouble(asciiContent[row][column])+0.000001 ||
							asciiContent[row + rowCount][column + columnCount].equals(noData)	) {
						tempt = tempt +  "0\t";
					}else {
						tempt = tempt +  "1\t";
					}
//					tempt = tempt + ("["+(column+columnCount)+","+(row + rowCount)+"]\t");
				}
				outArray.add(tempt);
			}
		}
		
		outArray.forEach(e -> System.out.println(e));
		
	}

}
