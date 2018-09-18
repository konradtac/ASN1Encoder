import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Int extends EncodeObject<Integer> {
	String encodeValue = "";
	
	public Int(String type, int length, int value) {
		super(type, length, value);
		encodeValue = encode();
	}

	public String encode() {
		DataResources dataResources = new DataResources();
		return calculateValue(dataResources);
	}

	private String calculateValue(DataResources dataResources) {

		StringBuilder tb = new StringBuilder();
		int indexOfResources = dataResources.getIndexOfDataResources(this.getClass().getName().toUpperCase());
		tb.append(countTag(indexOfResources));
		int val = this.getValue();
		int lengthOfUsefullData = countLength(tb, val);
		tb.append(countValue(val, lengthOfUsefullData));
		return stringToHex(tb.toString());
		
	}

	private int countLength(StringBuilder tb, int val) {
		String binaryRepresentation = Integer.toBinaryString(Math.abs(val));
		int lengthOfUsefullData = binaryRepresentation.length();
		int olderBit = val >> 7;
		int lengthOfOctets = countLength(val, olderBit);

		appendLength(lengthOfOctets, tb);
		complementMissingBitForLength(val, binaryRepresentation, lengthOfOctets, tb);
		return lengthOfUsefullData;
	}

	private void appendLength(int lengthOfOctets, StringBuilder tb) {
		for (int i = 0; i < 8 - Integer.toBinaryString(lengthOfOctets).length(); ++i) {
			tb.append("0");
		}
		tb.append(Integer.toBinaryString(lengthOfOctets));
	}

	private String countValue(int val, int lengthOfUsefullData) {
		String finalValue = (val > 0) ? Integer.toBinaryString(val)
				: Integer.toBinaryString(val).substring((Integer.toBinaryString(val).length() - lengthOfUsefullData));
		return finalValue;
	}

	private void complementMissingBitForLength(int val, String binaryRepresentation, int lengthOfOctets,
			StringBuilder tb) {
		for (int i = 0; i < (8 * lengthOfOctets) - binaryRepresentation.length(); ++i) {
			if (val < 0) {
				tb.append("1");
			} else if (val > 0) {
				tb.append("0");
			}
		}
	}

	private int countLength(double val, int olderBit) {
		if (val > 0 && olderBit == 0) {
			return (int) Math.ceil((Math.floor(log2(val)) + 1) / 8);
		} else if (val > 0) {
			return (int) Math.ceil((Math.floor(log2(val)) + 1) / 8) + 1;
		}
		return (int) Math.ceil((Math.floor(log2(-val)) + 1) / 8) + 1;

	}

	private double log2(double val) {
		return Math.log(val) / Math.log(2);
	}
	
	@Override
	public String getEncodeValue() {
		return encodeValue;
	}

}
