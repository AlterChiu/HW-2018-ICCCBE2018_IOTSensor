package Global;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Global {

	static public String observation0611_1H = "S:\\HomeWork\\ICCCBE2018\\observation\\0611.txt";
	static public String observation0927_1H = "S:\\HomeWork\\ICCCBE2018\\observation\\0927.txt";
	static public String observation0731_1H = "S:\\HomeWork\\ICCCBE2018\\observation\\0731.txt";

	static public String simulation0611_FEWS = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160611(00~00)\\許_QPESUMS_20160611\\";
	static public String simulation0927_FEWS = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160927(00~24)\\20170108_20160927_許_QPESUMS\\";
	static public String simulation0731_FEWS = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20170731(00~12)\\Final_許_QPESUMS\\";

	static public String observation0611_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0611.txt";
	static public String observation0927_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0927.txt";
	static public String observation0731_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0731.txt";

	static public String sobekAnalysisFolder = "S:\\HomeWork\\ICCCBE2018\\mergedAscii\\";

	static public String originalDem = "C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m.asc";
	static public String sobekRuntimeDem = "C:\\Sobek213\\TANZ1U01.lit\\DEM\\firstDem.asc";
	static public String sobekResult = "C:\\Sobek213\\Output\\";
	static public String temptSaveFolder = "S:\\HomeWork\\HighDensityDem\\temptSaveFolder\\";
	static public String demFolder = "C:\\Sobek213\\TANZ1U01.lit\\DEM\\";

	static public TreeMap<String, String[]> getTestPosition() {
		TreeMap<String, String[]> temptTree = new TreeMap<String, String[]>();
		String position[][] = { {"AnChung", "167329.839003952000000", "2549390.511627280000000" }};

		for (String[] station : position) {
			temptTree.put(station[0], new String[] { station[1], station[2] });
		}
		return temptTree;
	}

	static public Map<String, String[]> getAllIotPosition() {
		Map<String, String[]> temptTree = new HashMap<String, String[]>();
		String position[][] = { {"安中站","167343.0354","2549408.7745"},
				{"安和站","169782.6996","2549442.6696"},
				{"安忠五站","162443.8311","2551162.0524"},
				{"海佃三段站","166349.1283","2549757.3205"},
				{"海佃四站","165640.3824","2551509.1404"},
				{"頂安站","168471.3890","2549254.7808"},
				{"朝皇宮站","166392.8801","2548995.3546"},
				{"溪頂寮站","169520.5048","2547951.9182"},
				{"龍金站","169922.9723","2551708.0487"}};

		for (String[] station : position) {
			temptTree.put(station[0], new String[] { station[1], station[2] });
		}
		return temptTree;
	}
	
	static public Map<String, String[]> getAllIotPosition_En() {
		Map<String, String[]> temptTree = new HashMap<String, String[]>();
		String position[][] = { {"AnChung","167343.0354","2549408.7745"},
				{"AnHo","169782.6996","2549442.6696"},
				{"AnChung5","162443.8311","2551162.0524"},
				{"HaiTeing3","166349.1283","2549757.3205"},
				{"HaiTeing4","165640.3824","2551509.1404"},
				{"DinAhn","168471.3890","2549254.7808"},
				{"ChauHuangTemple","166392.8801","2548995.3546"},
				{"SeeDingLaio","169520.5048","2547951.9182"},
				{"LongKin","169922.9723","2551708.0487"}};

		for (String[] station : position) {
			temptTree.put(station[0], new String[] { station[1], station[2] });
		}
		return temptTree;
	}
}
