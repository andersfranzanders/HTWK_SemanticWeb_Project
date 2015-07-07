package classModel;

public class City extends ResourceClass {

	String einwohnerZahl;
	String Bundesland;

	public String getEinwohnerZahl() {
		return einwohnerZahl;
	}

	public void setEinwohnerZahl(String einwohnerZahl) {
		this.einwohnerZahl = einwohnerZahl;
	}

	public String getState() {
		return Bundesland;
	}

	public void setBundesland(String bundesland) {
		Bundesland = bundesland;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", einwohnerZahl=" + einwohnerZahl + ", Bundesland=" + Bundesland + "]";
	}

}
