package nihongo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import profile.HeadNavigator;
import profile.HeaderService;

@Controller
@RequestMapping("sakura-gakko")
public class SakuraGakkoController {

	@GetMapping
	public String sakuraGakkoPage(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.SAKURA_GAKKO);
		return "sakuraGakko";
	}
}
