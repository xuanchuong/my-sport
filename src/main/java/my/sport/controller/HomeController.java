package my.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import my.sport.rest.controller.PlayerRestController;
import profile.HeadNavigator;
import profile.HeaderService;

@Controller
@RequestMapping(value = {"/home", "/"})
public class HomeController {
	@Autowired
	private PlayerRestController playerRestController;

	@GetMapping(value = {"/contact"})
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.CONTACT);
		return "contact";
	}
	
	@GetMapping
	public String homePage(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.HOME);
		return "home";
	}
	
	@GetMapping(value = "/search")
	public String search(@RequestParam(value = "search", required = false) String q, Model model) {
		model.addAttribute("searchResult", playerRestController.getAllPlayers());
		return "home";
	}
}
