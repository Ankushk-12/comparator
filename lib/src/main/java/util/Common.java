package util;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public static Set<String> listFiles(File [] files){
		if(files.length ==0) {
			logger.warning("No files exists in the folder");
			return null;
		}
		Set<String> filesList = new HashSet<String>();
		for(File file : files) {
			filesList.add(file.getName());
		}
		return filesList;
	}
}

