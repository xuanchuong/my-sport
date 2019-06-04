package nihongo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndividualProfileController {
	@GetMapping("/")
	public String myProfile() {
		return "myProfile";
	}
	
	@GetMapping("/contact")
	public String myContact() {
		return "contact";
	}
}
