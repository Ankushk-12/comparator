package writer;

import java.io.File;
import java.io.FileWriter;
import static comparator.Main.environment;
import com.opencsv.CSVWriter;
import util.Common;

public class CsvWriter {
	private static String outputFolder = environment.get("OUTPUT_FOLDER");
	private static String outputFileName = environment.get("OUTPUT_FILE_NAME","output.csv");
	private static File outputFile = null;
	
	public CsvWriter() {
		File outputDir = new File(outputFolder);
		if(outputDir.isDirectory()) {
			Common.cleanDirectory(outputDir);
			outputFile = new File(outputFolder,outputFileName);
		}
	}
	
	public void writeAllLines(String fileName,String[] lines) {
	    try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile.getAbsolutePath(),true))) {
	    	writer.writeNext(new String[] {fileName});
	        writer.writeNext(lines);
	        writer.writeNext(null);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
