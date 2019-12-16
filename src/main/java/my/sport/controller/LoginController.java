package my.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping
	public String login() {
		return "login";
	}

	@PostMapping
	public ModelAndView login(String username, String passwork,
			ModelMap model) {
		return new ModelAndView("redirect:/dashboard", model);
	}
}
