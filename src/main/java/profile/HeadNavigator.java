package profile;

public enum HeadNavigator {
	WORK("work"), ABOUT("about"), CONTACT("contact");
	private String value;
	HeadNavigator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
