package profile;

import java.util.Arrays;
import java.util.List;

public class HeaderService {
	private final static List<HeadNavigator> navigatorItems;
	public static final String HEADER_ATTR = "headerAttribute";

	static {
		navigatorItems = Arrays.asList(HeadNavigator.values());
	}

	public static List<HeadNavigator> getNavigatoritems() {
		return navigatorItems;
	}
}
