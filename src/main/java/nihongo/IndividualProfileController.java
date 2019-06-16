package nihongo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndividualProfileController {
	@GetMapping("/")
	public String myProfile() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String myContact() {
		return "contact";
	}
	@GetMapping("/work")
	public String myWork() {
		return "work";
	}
	@GetMapping("/about")
	public String aboutMe() {
		return "about";
	}
}
