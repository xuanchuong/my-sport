package my.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/contact"})
public class ContactController {
	@GetMapping
	public String myContact(Model model) {
		return "contact";
	}
}
