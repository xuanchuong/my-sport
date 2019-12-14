package my.sport.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import my.sport.dto.UserDto;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@GetMapping()
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";
	}
	
	@PostMapping
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult resutl, WebRequest request, Errors errors) {
		if (resutl.hasErrors()) {
			return new ModelAndView("register", "user", userDto);
		}
		return new ModelAndView("redirect:/dashboard");
	}
}
