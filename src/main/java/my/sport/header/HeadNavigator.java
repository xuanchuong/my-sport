package my.sport.header;

public enum HeadNavigator {
	HOME("trang chủ", "/dashboard"), MATCH("tạo trận đấu", "match"), CONTACT("thông tin cá nhân", "contact");
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
