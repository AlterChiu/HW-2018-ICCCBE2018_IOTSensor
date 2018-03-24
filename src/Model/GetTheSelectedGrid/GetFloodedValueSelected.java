package Model.GetTheSelectedGrid;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import asciiFunction.AsciiBasicControl;
import usualTool.AtCommonMath;

import Global.Global;

public class GetFloodedValueSelected {

	public GetFloodedValueSelected(int selectGrid) throws IOException {
		// TODO Auto-generated method stub]
		
		int getGrid = selectGrid;
		
		TreeMap<String,String[][]> selectedGrid = getSelectedGrid();
		String iotList[][] = getIotStation();
		
		String floodedFile = Global.saveFolder_MergeResult;
		
		for(String iot[] : iotList) {
			String[][] iotSelected = selectedGrid.get(iot[0]);
			String stationName = iot[0];
			int column = Integer.parseInt(iot[1]);
			int  row      = Integer.parseInt(iot[2]);
			ArrayList<String> outArray = new ArrayList<String>();
			outArray.add(stationName);
			
			
			
			for(int eventFile = 0 ; eventFile< new File(floodedFile).list().length;eventFile++) {
				// read the event ascii grid
				String[][] ascii = new AsciiBasicControl(floodedFile + eventFile + ".asc").getAsciiGrid();
				ArrayList<Double> temptValue = new ArrayList<Double>();
				
				// get the selected grid
				for(int countRow = -1*(getGrid-1)/2 ; countRow<=(getGrid-1)/2 ; countRow++) {
					for(int countColumn = -1*(getGrid-1)/2 ; countColumn<=(getGrid-1)/2 ; countColumn++) {
						
						// only for 5*5 grid array
						if(iotSelected[countRow+2][countColumn+2].equals("1")) {
							try {
								temptValue.add(Double.parseDouble(ascii[row + countRow][column + countColumn]));
							}catch(Exception e) {
							}
						}
					}
				}
				outArray.add(new AtCommonMath(temptValue.stream().mapToDouble(Double::doubleValue).toArray()).getMax() + "");
			}
			outArray.forEach(text -> System.out.print(text + "\t"));
			System.out.println();
		}
		
		
	}
	
	
	
	
	
	private static TreeMap<String,String[][]> getSelectedGrid(){
		TreeMap<String,String[][]> selectedGrid = new TreeMap<String,String[][]>();
		selectedGrid.put("安中五站", new String[][] {
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
			{"1",	"1",	"1",	"0",	"0"},
			{"1",	"1",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"}
		});
		
		selectedGrid.put("海佃四站", new String[][]{
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"1",	"1",	"0",	"0"},
			{"0",	"0",	"1",	"1",	"0"},
			{"0",	"0",	"1",	"1",	"0"}
		});
		
		selectedGrid.put("海佃三段站", new String[][] {
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"1",	"0",	"0",	"0"},
			{"0",	"1",	"1",	"0",	"0"},
			{"1",	"1",	"1",	"0",	"1"},
			{"1",	"1",	"1",	"1",	"1"}			
		});
		
		selectedGrid.put("朝皇宮站", new String[][] {
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"1",	"1",	"0",	"0"},
			{"0",	"1",	"1",	"1",	"0"},
			{"0",	"1",	"1",	"1",	"0"}
		});
		
		selectedGrid.put("龍金站", new String[][] {
			{"0",	"0",	"1",	"0",	"0"},
			{"0",	"0",	"1",	"0",	"0"},
			{"0",	"0",	"1",	"1",	"1"},
			{"0",	"0",	"1",	"1",	"1"},
			{"1",	"1",	"1",	"1",	"0"},
		});
		
		selectedGrid.put("安中站", new String[][] {
			{"1",	"1",	"1",	"0",	"0"},
			{"0",	"1",	"1",	"1",	"1"},
			{"1",	"1",	"1",	"0",	"1"},
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
		});
		
		selectedGrid.put("頂安站", new String[][] {
			{"1",	"1",	"1",	"0",	"1"},
			{"1",	"1",	"0",	"1",	"1"},
			{"1",	"0",	"1",	"1",	"1"},
			{"1",	"1",	"1",	"1",	"1"},
			{"1",	"1",	"1",	"1",	"1"}
		});
		
		selectedGrid.put("安和站", new String[][] {
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
			{"1",	"1",	"1",	"0",	"0"},
			{"0",	"1",	"1",	"0",	"0"},
			{"0",	"1",	"1",	"1",	"0"}
		});
		
		selectedGrid.put("溪頂寮站", new String[][] {
			{"1",	"1",	"0",	"0",	"0"},
			{"0",	"1",	"1",	"1",	"0"},
			{"1",	"1",	"1",	"0",	"0"},
			{"1",	"1",	"0",	"0",	"0"},
			{"1",	"1",	"1",	"0",	"0"}
		});
		
		selectedGrid.put("裕農路裕義路口", new String[][] {
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"0",	"0",	"0"},
			{"0",	"0",	"1",	"1",	"1"},
			{"0",	"0",	"1",	"1",	"1"},
			{"0",	"0",	"1",	"1",	"1"}
		});
		return selectedGrid;
	}
	
	private static String[][] getIotStation(){
		return  new String[][] {
			{"安中五站","372","356"},
			{"海佃四站","536","341"},
			{"海佃三段站","571","427"},
			{"朝皇宮站","573","467"},
			{"龍金站","749","331"},
			{"安中站","620","447"},
			{"頂安站","676","453"},
			{"安和站","742","444"},
			{"溪頂寮站","729","519"},
			{"裕農路裕義路口","903","742"}
		};
	}
}
