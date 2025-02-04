package util;

import java.io.File;
import java.util.List;
import static comparator.Main.logger;

public class Common {

	public Boolean isDirectory(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return false;
	}
	
	public static Boolean nullCheck(List<String[]> file1, List<String[]> file2) {
		if(file1 == null && file2 == null) return true;
		return false;
	}
	
	public static void cleanDirectory(File directory) {
		if(!directory.isDirectory()) return;
		File [] files = directory.listFiles();
		for(File file : files) {
			file.delete();
		}
	}
	
	public static Boolean fileExists(File file) {
		if(file.exists()) return true;
		logger.warning("File not found : "+file.getAbsolutePath());
		return false;
	}
}
