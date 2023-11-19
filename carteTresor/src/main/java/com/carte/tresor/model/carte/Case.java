package main.java.com.carte.tresor.model.carte;

public class Case {
	
    private TypeCase type;
    private int tresors; // Valide uniquement pour TypeCase.TRESOR
	
    public int getTresors() {
		return tresors;
	}
	public void setTresors(int tresors) {
		this.tresors = tresors;
	}
	public TypeCase getType() {
		return type;
	}
	public void setType(TypeCase type) {
		this.type = type;
	}

}
