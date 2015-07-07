package classModel;


public class State extends ResourceClass{

	String einwohnerZahl;
	double durchschnittsEinkommen;
	String durchschnittsBmi;
	String durchschnittsBmiW;

	public double getDurchschnittsEinkommen() {
		return durchschnittsEinkommen;
	}

	public void setDurchschnittsEinkommen(double durchschnittsEinkommen) {
		this.durchschnittsEinkommen = durchschnittsEinkommen;
	}

	public String getDurchschnittsBmiW() {
		return durchschnittsBmiW;
	}

	public void setDurchschnittsBmiW(String durchschnittsBmiW) {
		this.durchschnittsBmiW = durchschnittsBmiW;
	}

	public String getEinwohnerZahl() {
		return einwohnerZahl;
	}

	public void setEinwohnerZahl(String einwohnerZahl) {
		this.einwohnerZahl = einwohnerZahl;
	}

	public double getIncome() {
		return durchschnittsEinkommen;
	}

	public void setIncome(double durchschnittsEinkommen) {
		this.durchschnittsEinkommen = durchschnittsEinkommen;
	}

	public String getDurchschnittsBmiM() {
		return durchschnittsBmi;
	}

	public void setDurchschnittsBmi(String durchschnittsBmi) {
		this.durchschnittsBmi = durchschnittsBmi;
	}

	@Override
	public String toString() {
		return "State [name=" + name + ", einwohnerZahl=" + einwohnerZahl + ", durchschnittsEinkommen="
				+ durchschnittsEinkommen + ", durchschnittsBmi=" + durchschnittsBmi + "]";
	}

}
