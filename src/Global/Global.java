package Global;

import java.util.TreeMap;

public class Global {

	static public int eventTime = 24;

	static public String observation0611 = "S:\\HomeWork\\ICCCBE2018\\observation\\0611.txt";
	static public String observation0927 = "S:\\HomeWork\\ICCCBE2018\\observation\\0927.txt";
	static public String observation0731 = "S:\\HomeWork\\ICCCBE2018\\observation\\0731.txt";

	static public String simulation0611 = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160611(00~00)\\許_QPESUMS_20160611\\";
	static public String simulation0927 = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20160927(00~24)\\20170108_20160927_許_QPESUMS\\";
	static public String simulation0731 = "S:\\HomeWork\\ICCCBE2018\\多次成果\\FloodAscii\\20170731(00~12)\\Final_許_QPESUMS\\";

	static public String observation0611_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0611.txt";
	static public String observation0927_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0927.txt";
	static public String observation0731_10min = "S:\\HomeWork\\ICCCBE2018\\observation\\10min\\0731.txt";

	static public String saveFolder_MergeResult = "S:\\HomeWork\\ICCCBE2018\\mergedAscii\\";

	static public String originalDem = "C:\\Sobek213\\TANZ1U01.lit\\DEM\\ZoneU1_20m.asc";
	static public String sobekRuntimeDem = "C:\\Sobek213\\TANZ1U01.lit\\DEM\\firstDem.asc";
	static public String sobekResult = "C:\\Sobek213\\Output\\";
	static public String analysisFolder = "C:\\Sobek213\\Analysis\\";

	static public TreeMap<String, String[]> getUncertaintyPosition() {
		TreeMap<String, String[]> temptTree = new TreeMap<String, String[]>();
		String position[][] = { { "朝皇宮站", "166393.082894916000000", "2548985.283620740000000" },
				{ "安中站", "167329.839003952000000", "2549390.511627280000000" } };

		for (String[] station : position) {
			temptTree.put(station[0], new String[] { station[1], station[2] });
		}
		return temptTree;
	}

	static public TreeMap<String, String[]> getAllIotPosition() {
		TreeMap<String, String[]> temptTree = new TreeMap<String, String[]>();
		String position[][] = { { "安中五站", "162367.190368440000000", "2551199.148974450000000" },
				{ "海佃四站", "165662.855498722000000", "2551505.057514020000000" },
				{ "海佃三段站", "166352.509038435000000", "2549777.889128630000000" },
				{ "朝皇宮站", "166393.082894916000000", "2548985.283620740000000" },
				{ "龍金站", "169920.008159805000000", "2551706.181965540000000" },
				{ "安中站", "167329.839003952000000", "2549390.511627280000000" },
				{ "頂安站", "168461.012654870000000", "2549258.957237000000000" },
				{ "安和站", "169782.704140448000000", "2549447.958468440000000" },
				{ "溪頂寮站", "169520.714143787000000", "2547951.882686490000000" },
				{ "裕農路裕義路口", "172977.183568405000000", "2543479.676934840000000" } };

		for (String[] station : position) {
			temptTree.put(station[0], new String[] { station[1], station[2] });
		}
		return temptTree;
	}

	static public String[][] getIotPosition() {
		return new String[][] { { "安中五站", "162367.190368440000000", "2551199.148974450000000" },
				{ "海佃四站", "165662.855498722000000", "2551505.057514020000000" },
				{ "海佃三段站", "166352.509038435000000", "2549777.889128630000000" },
				{ "朝皇宮站", "166393.082894916000000", "2548985.283620740000000" },
				{ "龍金站", "169920.008159805000000", "2551706.181965540000000" },
				{ "安中站", "167329.839003952000000", "2549390.511627280000000" },
				{ "頂安站", "168461.012654870000000", "2549258.957237000000000" },
				{ "安和站", "169782.704140448000000", "2549447.958468440000000" },
				{ "溪頂寮站", "169520.714143787000000", "2547951.882686490000000" },
				{ "裕農路裕義路口", "172977.183568405000000", "2543479.676934840000000" } };
	}

	static public String[][] getZoneOneIotPosition() {
		return new String[][] { { "安中五站", "162367.190368440000000", "2551199.148974450000000" },
				{ "海佃四站", "165662.855498722000000", "2551505.057514020000000" },
				{ "海佃三段站", "166352.509038435000000", "2549777.889128630000000" },
				{ "朝皇宮站", "166393.082894916000000", "2548985.283620740000000" },
				{ "龍金站", "169920.008159805000000", "2551706.181965540000000" },
				{ "安中站", "167329.839003952000000", "2549390.511627280000000" },
				{ "頂安站", "168461.012654870000000", "2549258.957237000000000" },
				{ "安和站", "169782.704140448000000", "2549447.958468440000000" },
				{ "溪頂寮站", "169520.714143787000000", "2547951.882686490000000" } };
	}

	static public String[][] getMG4CIotPosition() {
		return new String[][] { { "安中五站", "162367.1904", "2551199.149" }, { "溪頂寮站", "169520.7141", "2547951.883" },
				{ "媚儷站", "168311.6623", "2548539.489" }, { "海佃豪宅站", "167337.7476", "2549430.779" },
				{ "公學站", "168346.9327", "2553038.181" }, { "安中站", "167329.839", "2549390.512" },
				{ "海佃四站", "165662.8555", "2551505.058" }, { "安中六站", "161221.6842", "2551268.019" },
				{ "金格站", "164699.3266", "2550162.628" }, { "總頭里站", "168165.893", "2550601.679" },
				{ "朝皇宮站", "166393.0829", "2548985.284" }, { "龍金站", "169918.528", "2551697.663" },
				{ "海佃三段站", "166352.509", "2549777.889" }, { "頂安站", "168461.0127", "2549258.957" },
				{ "安和站", "169782.7041", "2549447.958" }, { "長和二站", "170246.4641", "2552337.344" } };
	}
}
