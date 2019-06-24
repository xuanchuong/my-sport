package nihongo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import profile.HeaderService;

@Controller
public class IndividualProfileController {
	@GetMapping("/")
	public String myProfile() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String myContact(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		return "contact";
	}
	@GetMapping("/work")
	public String myWork(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		return "work";
	}
	@GetMapping("/about")
	public String aboutMe(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		return "about";
	}
}
