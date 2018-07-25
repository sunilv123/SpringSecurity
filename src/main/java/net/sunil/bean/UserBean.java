package net.sunil.bean;

public class UserBean {

	private String name;
	
	private String dummy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDummy() {
		return dummy;
	}

	public void setDummy(String dummy) {
		this.dummy = dummy;
	}

	public UserBean(String name, String dummy) {
		super();
		this.name = name;
		this.dummy = dummy;
	}
	
	
	
}
