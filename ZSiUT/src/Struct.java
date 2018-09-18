import java.awt.datatransfer.StringSelection;

public class Struct extends EncodeObject<StructFactory>{
	
	public Struct(String type, int length, StructFactory value) {
		super(type, length, value);
		
	}
	public String encode() { 
		return getEncodeValue();	
	}
	@Override
	public String getEncodeValue() {
		DataResources dataResources = new DataResources();
		StringBuilder stringBuilder = new StringBuilder();
		int indexOfResources = dataResources.getIndexOfDataResources("SEQUENCE");
		stringBuilder.append("SEQUENCE: ");
		stringBuilder.append(indexOfResources + " ");
		createString(stringBuilder);
		return stringBuilder.toString();
	}
	private void createString(StringBuilder stringBuilder) {
		StructFactory structBuilder = this.getValue();

		stringBuilder.append(Integer.toHexString(structBuilder.numberOfOctets));
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("\t");
		stringBuilder.append(structBuilder.name.getEncodeValue());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("\t");
		stringBuilder.append(structBuilder.f2.getEncodeValue());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("\t");
		stringBuilder.append(structBuilder.f3.getEncodeValue());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("\t");
		stringBuilder.append(structBuilder.f5.getEncodeValue());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("\t");
		stringBuilder.append(structBuilder.f6.getEncodeValue());
	}
	
}
