package jlpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import profile.HeadNavigator;
import profile.HeaderService;

@Controller
public class IndividualProfileController {
	@GetMapping("/")
	public String myProfile(Model model) {
		return myContact(model);
	}
	
	@GetMapping("/contact")
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.CONTACT);
		return "contact";
	}
}
