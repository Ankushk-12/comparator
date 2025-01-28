package compare;

import java.io.File;
import java.util.List;
import reader.CsvReader;
import writer.CsvWriter;

public class Compare {
	final CsvWriter writer = new CsvWriter();
	public Boolean isIdentical(File stagingFile, File prodFile) {
		List<String[]> stagingfileData = null;
		List<String[]> prodfileData = null;
		stagingfileData = CsvReader.readFile(stagingFile);
		prodfileData = CsvReader.readFile(prodFile);
		if (stagingfileData == null || prodfileData.isEmpty())
			return false;

		if (stagingfileData.size() != prodfileData.size())
			return false;
		int result = 0;
		int index = 0;
		for (int i = 0; i < stagingfileData.size(); i++) {
			String[] stagingFileRow = stagingfileData.get(i);
			String[] prodFileRow = prodfileData.get(i);
			if (stagingFileRow.length != prodFileRow.length)
				return false;
			for (int j = 0; j < stagingFileRow.length; j++) {
				if (isNull(stagingFileRow[j]) && isNull(prodFileRow[j]))
					continue;
				result = stagingFileRow[j].compareTo(prodFileRow[j]);
				if (result != 0)
					index = j;
			}
			if (result == 1) {
				System.out.println("in the result 1");
				writer.writeAllLines(stagingFile.getAbsolutePath(), index, stagingFileRow);
				System.out.println("in the result  1 staging");
				writer.writeAllLines(prodFile.getAbsolutePath(), index, prodFileRow);
				System.out.println("in the result  1 prod");
			} else if (result == -1) {
				System.out.println("in the result -1");
				writer.writeAllLines(prodFile.getAbsolutePath(), index, prodFileRow);
				System.out.println("in the result  1 prod");
				writer.writeAllLines(stagingFile.getAbsolutePath(), index, stagingFileRow);
				System.out.println("in the result  1 staging");
			}
		}
		if(result != 0) return false;
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