package writer;

import java.io.File;
import java.io.FileWriter;
import static comparator.Main.environment;
import com.opencsv.CSVWriter;

public class CsvWriter {
	private static String outputFolder = environment.get("OUTPUT_FOLDER");
	private static File outputFile = null;
	
	public CsvWriter() {
		File outputDir = new File(outputFolder);
		if(outputDir.isDirectory()) {
			outputFile = new File(outputFolder,"result.csv");
			if(outputFile.exists() && outputFile.isFile()) outputFile.delete();
			
			outputFile = new File(outputFolder,"result.csv");
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
