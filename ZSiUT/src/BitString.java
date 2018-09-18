
public class BitString extends EncodeObject<String> {
	private String encodeValue;
	
	public BitString(String type, int length, String value) {
		super(type, length, value);
		encodeValue = encode();
	}

	public String encode() {
		DataResources dataResources = new DataResources();
		StringBuilder stringBuilder = new StringBuilder();
		int indexOfResources = dataResources.getIndexOfDataResources(this.getClass().getName().toUpperCase());
		stringBuilder.append(stringToHex(countTag(indexOfResources)) + " ");
		String val = this.getValue();
		val = getDigits(val);
		int numberOfBytes = (int) (Math.ceil(val.length() / 8.0) + 1);
		String format = numberOfBytes < 15 ? "0%s" : "%s";
		stringBuilder.append(String.format(format, Integer.toHexString(numberOfBytes)) + " ");
		int unusedBits = 8 * (numberOfBytes - 1) - val.length();
		stringBuilder.append(stringToHex(countTag(unusedBits)) + " ");
		stringBuilder.append(stringToHex(countTag(Integer.parseInt(val, 2))) + " ");
		return stringBuilder.toString();
	}

	private String getDigits(String val) {
		val = val.replaceAll("\\D+", "");
		return val;
	}
	
	@Override
	public String getEncodeValue() {
		return encodeValue;
	}
	
}
