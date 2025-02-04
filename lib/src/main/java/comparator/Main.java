package comparator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import util.Common;
import reader.CsvReader;
import compare.Compare;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
	public static final Logger logger = Logger.getLogger(Main.class.getName());
	public static final Dotenv environment = Dotenv.load();
	public static Common common = new Common();
	public static CsvReader csvReader = new CsvReader();
	public static Compare compator = new Compare();
	public static CsvReader reader = new CsvReader();

	public static void main(String[] args) {
		logger.info("process started");
		String stagingFolderPath = environment.get("STAGING_FOLDER", null);
		String prodFolderPath = environment.get("PROD_FOLDER", null);
		if (stagingFolderPath == null || prodFolderPath == null)
			System.exit(1);

		File stagingDir = new File(stagingFolderPath);
		File prodDir = new File(prodFolderPath);
		if (common.isDirectory(stagingDir) && common.isDirectory(prodDir)) {
			File[] stagingFilesList = stagingDir.listFiles();
			File[] prodFilesList = prodDir.listFiles();
			Set<String> stagingFilesSet = new HashSet<String>();
			Set<String> prodFilesSet = new HashSet<String>();
			Set<String> stagingfilesNotFound = new HashSet<String>();
			Set<String> prodfilesNotFound = new HashSet<String>();
			Set<String> listFiles = Common.listFiles(stagingFilesList);
			if (listFiles == null) {
				logger.warning("files not exists in "+stagingDir.getName()+ " the folder ");
				System.exit(1);
			}
			stagingFilesSet.addAll(Common.listFiles(stagingFilesList));
			
			listFiles = Common.listFiles(prodFilesList);
			if (listFiles == null) {
				logger.warning("files not exists in "+prodDir.getName()+ " the folder ");
				System.exit(1);
			}
			prodFilesSet.addAll(Common.listFiles(prodFilesList));

			for (String fileName : stagingFilesSet) {
				if (prodFilesSet.contains(fileName)) {
					File stagingFile = new File(stagingDir, fileName);
					File prodFile = new File(prodDir, fileName);
					Boolean fileExists = Common.fileExists(prodFile);
					if (!fileExists) {
						prodfilesNotFound.add(prodFile.getName());
						logger.warning("File not found in prod folder" + prodFile);
						continue;
					}

					fileExists = Common.fileExists(stagingFile);
					if (!fileExists) {
						stagingfilesNotFound.add(stagingFile.getName());
						logger.warning("File not found in staging folder" + stagingFile);
						continue;
					}

					if (stagingFile.exists() && prodFile.exists()) {
						Boolean result = compator.isIdentical(stagingFile, prodFile);
						if (result) {
							logger.info("Both the files are identical");
						} else {
							logger.info("There are some differnces in both the files");
						}
					}
				} else {
					logger.warning("file not found : " + fileName);
				}
			}

		} else {
			Boolean result = compator.isIdentical(stagingDir, prodDir);
			if (result) {
				logger.info("Both the files are identical");
			} else {
				logger.info("There are some differnces in both the files");
			}
		}
		logger.info("Process ended");
	}
}
