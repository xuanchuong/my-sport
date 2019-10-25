package profile;

public enum HeadNavigator {
	JLPT("jlpt"), CONTACT("contact");
	private String value;
	HeadNavigator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
