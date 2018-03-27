package test;
import java.io.IOException;
import java.util.ArrayList;

import asciiFunction.AsciiMerge;
import usualTool.AtArrayFunction;
import usualTool.AtCommonMath;
import usualTool.AtFileWriter;

public class test {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		ArrayList<Double> tempt = new ArrayList<Double>();
		
		tempt.add(0.);
		tempt.add(5.);
		
		tempt.add(0.);
		tempt.add(5.);
		
		
		tempt.add(0.);
		tempt.add(5.);
		
		
		tempt.add(0.);
		tempt.add(4.);
		
		
		tempt.add(1.);
		tempt.add(2.);
		
		
		System.out.println(new AtCommonMath(tempt).getClosestIndex(0.6));
		
	}
}
