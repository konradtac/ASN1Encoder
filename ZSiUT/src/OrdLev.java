import java.util.Iterator;

public class OrdLev extends EncodeObject<int[]> {

	private String encodeValue = "";

	public OrdLev(String type, int length, int[] value) {

		super(type, length, value);
		encodeValue = encode();
	}

	private String encode() {
		this.getValue();
		Int[] intArray = new Int[10];
		DataResources dataResources = new DataResources();
		StringBuilder stringBuilder = new StringBuilder();
		int counter = 0;
		int indexOfResources = dataResources.getIndexOfDataResources("SEQUENCE");
		stringBuilder.append("OrdLev (SEQUENCE): ");
		stringBuilder.append(indexOfResources + " ");
		int[] val = this.getValue();
		for (int i = 0; i < val.length; ++i) {
			intArray[i] = new Int(this.getType(), this.getLength(), val[i]);
			counter += intArray[i].getEncodeValue().length();
		}
		
		stringBuilder.append(Integer.toHexString(counter));
		for (int i = 0; i < val.length; ++i) {
			stringBuilder.append(System.getProperty("line.separator"));
			stringBuilder.append("\t");
			stringBuilder.append(intArray[i].encodeValue);

		}

		return stringBuilder.toString();
	}

	@Override
	public String getEncodeValue() {
		return encodeValue;

	}
}
