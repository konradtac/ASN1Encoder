
public class StringType extends EncodeObject<String> {
	
	private String encodeValue = "";
	
	public StringType(String type, int length, String value) {
		super(type, length, value);
		encodeValue = encode();

	}
	
	public String encode() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("0D ");
		String val = this.getValue();
		int lengthVal = val.length();
		if (lengthVal < 16) {
			stringBuilder.append("0");
		}
		stringBuilder.append(Integer.toHexString(lengthVal) + " ");
		byte[] stringByte = val.getBytes();
		for (int j = 0; j < stringByte.length; j++) {
			stringBuilder.append((String.format("%02X ", stringByte[j])));
		}
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
