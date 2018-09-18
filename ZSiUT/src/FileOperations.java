import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperations {
	public static List<String> readFromFile(String fileName) {
		try (FileReader fileReader = new FileReader(fileName);
				BufferedReader reader = new BufferedReader(fileReader);) {
			String nextLine = null;
			int lines = 0;
			List<String> rowsFromFile = new ArrayList<>();
			while ((nextLine = reader.readLine()) != null) {
				rowsFromFile.add(nextLine.trim());
				lines++;
			}
			return rowsFromFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
