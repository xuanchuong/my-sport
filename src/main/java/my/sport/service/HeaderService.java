package my.sport.service;

import java.util.Arrays;
import java.util.List;

import my.sport.header.HeadNavigator;

public class HeaderService {
	private final static List<HeadNavigator> navigatorItems;
	public static final String HEADER_ATTR = "headerAttribute";
	public static final String SELECTED_ITEM_ATTR = "selectedItemAttr";

	static {
		navigatorItems = Arrays.asList(HeadNavigator.values());
	}

	public static List<HeadNavigator> getNavigatoritems() {
		return navigatorItems;
	}
}
