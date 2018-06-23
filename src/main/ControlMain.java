package main;

import java.io.IOException;

import Model.GetCloestValue.GetClosetValue;
import asciiFunction.AsciiBasicControl;

public class ControlMain {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String fileAdd = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160611(00~00)\\許_QPESUMS_20160611\\U2\\";
		double x = 167343.0354;
		double y = 2549408.7745;
		
		for(int index = 0 ; index <= 24 ; index++) {
			System.out.print(new AsciiBasicControl(fileAdd+index + "\\dm1d0000.asc").getValue(x, y) + "\t");
			System.out.print(new AsciiBasicControl(fileAdd+index + "\\dm1d0001.asc").getValue(x, y) + "\t");
			System.out.print(new AsciiBasicControl(fileAdd+index + "\\dm1d0002.asc").getValue(x, y) + "\t");
			System.out.println(new AsciiBasicControl(fileAdd+index + "\\dm1d0003.asc").getValue(x, y) + "\t");
			
		}
		
		

	}

}
