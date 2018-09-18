import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectIdentifier extends EncodeObject<String> {
	
	String encodeValue = "";
	
	public ObjectIdentifier(String type, int length, String value) {
		super(type, length, value);
		encodeValue = encode(value);
	}

	private String encode(String value) {
		int[] nodeNumbers = Arrays.asList(value.split("\\.")).stream().mapToInt(Integer::parseInt).toArray();

		StringBuilder stringBuilder = new StringBuilder();
		int calcFirstTwoNodes = (nodeNumbers[0] * 40 + nodeNumbers[1]);
		String calcFristTwoNodesBinary = Integer.toString(calcFirstTwoNodes, 2);
		fillToOctet(calcFristTwoNodesBinary, stringBuilder);
		stringBuilder.append(calcFristTwoNodesBinary);
		for (int i = 2; i < nodeNumbers.length; i++) {
			String nodeNumber = Integer.toString(nodeNumbers[i], 2);
			if (nodeNumber.length() > 8) {
				calculateForMultipleOctets(stringBuilder, nodeNumber);
			} else {
				stringBuilder.append(nodeNumber);
			}
		}
		return stringToHex(stringBuilder.toString());
	}

	private void calculateForMultipleOctets(StringBuilder stringBuilder, String nodeNumber) {
		ArrayList<String> tempList = new ArrayList(Arrays.asList(nodeNumber.split("")));
		for (int j = 0; j < fillToOctet(nodeNumber, stringBuilder); j++) {
			tempList.add(0, "0");
		}
		tempList.set(0, "1");
		tempList.set(tempList.size() - 8, "0");
		String leftmostOctet = tempList.stream().map(Object::toString).collect(Collectors.joining("")).substring(0, 8);
		String rightmostOctets = tempList.stream().map(Object::toString).collect(Collectors.joining("")).substring(8);
		String firstByteOffFirstOctet = leftmostOctet.substring(0, 1);
		String restBytesOfFirstOctet = leftmostOctet.substring(1);
		ArrayList<String> restBytesOffFirstOctetArray = new ArrayList(Arrays.asList(restBytesOfFirstOctet.split("")));
		restBytesOfFirstOctet = shiftLeftByOneByte(restBytesOffFirstOctetArray);
		stringBuilder.append(firstByteOffFirstOctet + restBytesOfFirstOctet);
		stringBuilder.append(rightmostOctets);
	}

	private String shiftLeftByOneByte(ArrayList<String> restBytesOffFirstOctetArray) {
		int sizeOfRestBytesOffFirstOctetArray = restBytesOffFirstOctetArray.size();
		for (int j = 0; j < sizeOfRestBytesOffFirstOctetArray - 1; j++) {
			restBytesOffFirstOctetArray.set(j, restBytesOffFirstOctetArray.get(j + 1));
		}
		restBytesOffFirstOctetArray.set(restBytesOffFirstOctetArray.size() - 1, "0");
		return String.join("", restBytesOffFirstOctetArray);
	}

	private int fillToOctet(String nodeNumber, StringBuilder sb) {
		int numberOfOctets = (int) Math.ceil(nodeNumber.length() / 8.0);
		int counter = 0;
		for (int i = 0; i < 8 * numberOfOctets - nodeNumber.length(); i++) {
			if (numberOfOctets <= 1)
				sb.append("0");
			counter++;
		}
		return counter;

	}
	
	@Override
	public String getEncodeValue() {
		return encodeValue;
	}

}
