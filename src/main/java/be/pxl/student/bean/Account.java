package be.pxl.student.bean;

public class Account {

	int id;
	String number;
	String IBAN;
	String name;

	public Account(String number, String IBAN, String name) {
		this.number = number;
		this.IBAN = IBAN;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
