package util;

import java.io.File;
import java.util.List;

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
}
