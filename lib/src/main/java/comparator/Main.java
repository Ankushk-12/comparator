package comparator;

import java.io.File;
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
			logger.info("Under the if condition");
		} else {
			Boolean result = compator.isIdentical(stagingDir, prodDir);
			logger.info("result is : " + result);
		}
		logger.info("Process ended");
	}
}
