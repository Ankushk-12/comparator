package compare;

import java.io.File;
import java.util.List;
import reader.CsvReader;
import writer.CsvWriter;
import util.Common;

public class Compare {
	final CsvWriter writer = new CsvWriter();
	public Boolean isIdentical(File stagingFile, File prodFile) {
		List<String[]> stagingfileData = null;
		List<String[]> prodfileData = null;
		stagingfileData = CsvReader.readFile(stagingFile);
		prodfileData = CsvReader.readFile(prodFile);
		Common.nullCheck(stagingfileData,prodfileData);
		boolean result = true;
		
		if(stagingfileData.size() != prodfileData.size())return false;
		
		for (int i = 0; i < stagingfileData.size(); i++) {
			String[] stagingFileRow = stagingfileData.get(i);
			String[] prodFileRow = prodfileData.get(i);
			if (stagingFileRow.length != prodFileRow.length) {
				System.out.println("not same size");
				writer.writeAllLines(stagingFile.getAbsolutePath(), stagingFileRow);
				writer.writeAllLines(prodFile.getAbsolutePath(), prodFileRow);
				continue;
			}
			for (int j = 0; j < stagingFileRow.length; j++) {
				if (isNull(stagingFileRow[j]) && isNull(prodFileRow[j]))
					continue;
				result = stagingFileRow[j].equals(prodFileRow[j]);
				if(!result) break;
			}
			if (!result) {
				System.out.println("in the result 1");
				writer.writeAllLines(stagingFile.getAbsolutePath(), stagingFileRow);
				System.out.println("in the result  1 staging");
				writer.writeAllLines(prodFile.getAbsolutePath(), prodFileRow);
				System.out.println("in the result  1 prod");
			}
		}
		return true;
	}

	public Boolean isNull(String value) {
		if (value == null) {
			return true;
		}
		return false;
	}

	public void printArray(String[] array) {
		for (String value : array) {
			System.out.println(value + " ");
		}
	}
}