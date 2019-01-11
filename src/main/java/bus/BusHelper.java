package bus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BusHelper {

	private static List<String> busNameList;

	/**
	 * 公交名List
	 * 
	 * @return
	 */
	public static List<String> getBusNameList(String folderPath) {
		if (busNameList == null || busNameList.size() == 0) {
			busNameList = new ArrayList<>();
			String[] filenameList = new File(folderPath).list();
			for (String filename : filenameList) {
				busNameList.add(filename.substring(0, filename.lastIndexOf(".")));
			}
		}
		return busNameList;
	}

}
