package utils;

public class State {
	//index of q
	private int id;
	//for example q+id
	private String value;

	public State() {
		super();
	}

	public State(int id, String value) {
		super();
		this.id = id;
		this.value = value + this.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value + this.id;
	}
}