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
		
		try {
			
		}catch(Exception e) {
			
		}
	}
	
	public void writeAllLines(String fileName, int index ,String[] lines) {
	    try (CSVWriter writer = new CSVWriter(new FileWriter(outputFile.getAbsolutePath()))) {
	    	System.out.println("method is called");
	    	writer.writeNext(new String[] {fileName,Integer.toString(index)});
	        writer.writeNext(lines);
	        writer.writeNext(new String[] {fileName,Integer.toString(index)});
	        writer.writeNext(lines);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
