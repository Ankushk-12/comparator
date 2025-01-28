package util;

import java.io.File;

public class Common {
	
	public Boolean isDirectory(File file) {
		if (file.isDirectory()) {
			return true;
		}
		return false;
	}
}
