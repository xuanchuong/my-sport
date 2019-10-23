package profile;

public enum HeadNavigator {
	SAKURA_GAKKO("sakura-gakko"), JLPT("jlpt"), CONTACT("contact");
	private String value;
	HeadNavigator(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
