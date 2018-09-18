
public class OctetString extends EncodeObject<String> {
	
	private String encodeValue = "";
	
	public OctetString(String type, int length, String value) {
		super(type, length, value);
		encodeValue = encode();
	}

	public String encode() {

		DataResources dataResources = new DataResources();
		StringBuilder stringBuilder = new StringBuilder();
		int indexOfResources = dataResources.getIndexOfDataResources(this.getClass().getName().toUpperCase());
		shouldAddZero(stringBuilder, indexOfResources);
		stringBuilder.append(indexOfResources + " ");
		String val = substractValue();
		int numberOfBytest = val.length();
		shouldAddZero(stringBuilder, numberOfBytest);
		stringBuilder.append(Integer.toHexString(numberOfBytest) + " ");
		countValue(stringBuilder, val);
		return stringBuilder.toString();
	}

	private String substractValue() {
		return this.getValue().substring(0, this.getValue().length() - 1);
	}

	private void countValue(StringBuilder stringBuilder, String val) {
		byte[] stringByte = val.getBytes();
		for (int j = 0; j < stringByte.length; j++) {
			stringBuilder.append((String.format("%02X ", stringByte[j])));
		}
	}

	private void shouldAddZero(StringBuilder stringBuilder, int val) {
		if (val < 16) {
			stringBuilder.append("0");
		}
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
