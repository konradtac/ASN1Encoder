import javax.naming.BinaryRefAddr;

public abstract class EncodeObject<T> {

	private String type;
	private int length;
	private T value;
	

	public EncodeObject(String type, int length, T value) {
		setType(type);
		setLength(length);
		setValue(value);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String stringToHex(String stringData) {
		String[] stringDatas = stringData.split("(?!^)");

		StringBuilder hexBuilder = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		for (int i = 0; i < stringDatas.length; i++) {
			sb.append(stringDatas[i]);
			if (counter % 4 == 0) {
				int decimal = Integer.parseInt(sb.toString(), 2);
				hexBuilder.append(Integer.toString(decimal, 16).toUpperCase());
				
				sb = new StringBuilder();
			}
			if(counter % 8 == 0) {
				hexBuilder.append(" ");
			}
			counter++;
		}
	
		return hexBuilder.toString();
	}

	protected String countTag(int indexOfResources) {
		StringBuilder sb = new StringBuilder();
		String binaryIndexOfResources = Integer.toString(indexOfResources, 2);
		for (int i = 0; i < 8 - binaryIndexOfResources.length(); ++i) {
			sb.append("0");
		}
		sb.append(binaryIndexOfResources);
		return sb.toString();
	}
	
	public abstract String getEncodeValue();


}
