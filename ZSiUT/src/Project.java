
import java.util.ArrayList;

public class Project {
	private Real FN;
	private OrdLev SN;
	private ObjectIdentifier AD;
	private int AGE;
	private Details details;
	private ArrayList<Integer> OrdLevs;
	
	public Project(){
		
	}
	public Real getFN() {
		return FN;
	}

	public void setFN(Real fN) {
		FN = fN;
	}

	public OrdLev getSN() {
		return SN;
	}

	public void setSN(OrdLev sN) {
		SN = sN;
	}

	public ObjectIdentifier getAD() {
		return AD;
	}

	public void setAD(ObjectIdentifier aD) {
		AD = aD;
	}

	public int getAGE() {
		return AGE;
	}

	public void setAGE(int aGE) {
		AGE = aGE;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public ArrayList<Integer> getOrdLevs() {
		return OrdLevs;
	}

	public void setOrdLevs(ArrayList<Integer> ordLevs) {
		OrdLevs = ordLevs;
	}
}