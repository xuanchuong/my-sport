package my.sport.header;

public enum HeadNavigator {
	HOME("home", "/dashboard"), MATCH("create a match", "match"), CONTACT("contact", "contact");
	private String value;
	private String url;
	HeadNavigator(String value, String url) {
		this.value = value;
		this.url = url;
	}

	public String getValue() {
		return value;
	}

	public String getUrl() {
		return url;
	}
}
