package my.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import profile.HeadNavigator;
import profile.HeaderService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping("/contact")
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.CONTACT);
		return "contact";
	}
}
