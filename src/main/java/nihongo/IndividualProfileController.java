package nihongo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import profile.HeadNavigator;
import profile.HeaderService;

@Controller
public class IndividualProfileController {
	@GetMapping("/")
	public String myProfile(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.CONTACT);
		return "sakuraGakko";
	}
	
	@GetMapping("/contact")
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.CONTACT);
		return "contact";
	}
}
