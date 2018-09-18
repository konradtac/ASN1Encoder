
public class DataObject {
	
	private String typeName;
	private String valueEncoding;
	
	public DataObject(String typeName, String valueEncoding) {
		this.typeName = typeName;
		this.valueEncoding = valueEncoding;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getValueEncoding() {
		return valueEncoding;
	}
	public void setValueEncoding(String valueEncoding) {
		this.valueEncoding = valueEncoding;
	}
}
