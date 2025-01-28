package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import static comparator.Main.logger;
import com.opencsv.CSVReader;

public class CsvReader {
	
	public static List<String []> readFile(File path) {
		try(FileReader fileReader = new FileReader(path);CSVReader reader = new CSVReader(fileReader);){
			List<String []> fileData = reader.readAll();
			return fileData;
		}catch(FileNotFoundException e) {
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e) {
			logger.warning(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
