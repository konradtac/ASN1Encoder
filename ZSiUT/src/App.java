
import java.util.Arrays;
import java.util.List;

public class App {
	static String[] TYPES = { "sn", "fn", "ad", "age", "details", "name", "f2", "f3", "f4", "f5", "f6" };
	public static StructFactory structBuilder;

	public static void main(String[] args) {
		List<String> readedRows = FileOperations.readFromFile("testFile.txt");
		boolean flag = true;
		structBuilder = new StructFactory();

		for (int i = 0; i < readedRows.size(); i++) {
			String rowAsTable[] = readedRows.get(i).split(" ", 2);
			String type = rowAsTable[0];
			String value = (rowAsTable.length == 1) ? "" : rowAsTable[1];

			if (hasType(type)) {
				if (flag) {
					flag = createObjectByType(value, type, rowAsTable.length, "");
				} else {
					flag = createObjectByType(value, "details", rowAsTable.length, type);
				}
			}
		}

		Struct struct = structBuilder.struct;
		String s = struct.encode();
		System.out.println(s);

	}

	private static boolean createObjectByType(String value, String type, int length, String typeForDetails) {
		EncodeObject<?> encodeObject;
		value = value.trim();

		switch (type) {
		case "fn":
			encodeObject = new Real(type, length, Double.parseDouble(removeComma(value)));
			System.out.print("fn: ");
			System.out.println(encodeObject.getEncodeValue());
			break;
		case "sn":
			encodeObject = new OrdLev(type, length, prepereDataStringToIntArray(value));
			System.out.print("SN: ");
			System.out.println(encodeObject.getEncodeValue());
			break;
		case "ad":
			encodeObject = new ObjectIdentifier(type, length, removeComma(value));
			System.out.print("AD: ");
			System.out.println(encodeObject.getEncodeValue());
			break;
		case "age":
			encodeObject = new Int(type, length, Integer.parseInt(removeComma(value)));
			System.out.print("AGE: ");
			System.out.println(encodeObject.getEncodeValue());
			break;
		case "details":
			if (value.equals("{")) {
				encodeObject = structBuilder.createStruct();
				return false;
			}
			structBuilder.add(typeForDetails, length, value);

			return false;
		default:
			break;
		}
		return true;
	}

	private static String removeComma(String value) {
		return value.replaceAll(",", "");
	}

	public static int[] prepereDataStringToIntArray(String value) {
		String[] parts = value.replaceAll("^\\[|\\]$", "").replaceAll("\\{|\\}", "")
				.split(",(?=(([^\"]*\"){2})*[^\"]*$)");
		return Arrays.asList(parts).stream().mapToInt(Integer::parseInt).toArray();
	}

	private static boolean hasType(String type) {
		return Arrays.asList(TYPES).contains(type);
	}
}
