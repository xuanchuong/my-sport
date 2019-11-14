package profile;

public enum HeadNavigator {
	HOME("dash board", "/dashboard"), CONTACT("contact", "contact");
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
