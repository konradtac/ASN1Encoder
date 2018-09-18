import java.util.BitSet;
import java.util.Set;

public class Real extends EncodeObject<Double>{
	
	private String encodeValue = "";
	
	public Real(String type, int length, Double value) {
		super(type, length, value);
		encodeValue = encode();
	}
	
	

	public String encode() {
		DataResources dataResources = new DataResources();
		dataResources.buildDataResources();
		
		dataResources.getBuildedMap();
		
		StringBuilder stringBuilder = new StringBuilder();
		int indexOfResources = dataResources.getIndexOfDataResources(this.getClass().getName().toUpperCase());
		int primitiveOrConstrucetd = 0;
		int tagClass = 0;
		stringBuilder.append(Integer.toString(tagClass, 2));
		stringBuilder.append("00");
		stringBuilder.append(Integer.toString(primitiveOrConstrucetd, 2));
		stringBuilder.append(Integer.toString(indexOfResources, 2));
		this.getValue();
		long doubleBits = Double.doubleToLongBits(this.getValue());
		String doubleBitsStr = Long.toBinaryString(doubleBits);
		int numberOfComplementBits = 64 - doubleBitsStr.length();
		for(int i=0 ; i < numberOfComplementBits; ++i) {
			stringBuilder.append("0");
		}
		stringBuilder.append("00100000");
		stringBuilder.append(doubleBitsStr);
		
		return stringToHex(stringBuilder.toString());
	}
	
	@Override
	public String getEncodeValue() {
		return encodeValue;
	}
	
	
}
