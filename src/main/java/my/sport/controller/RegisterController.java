package my.sport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
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
import my.sport.service.PlayerService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private PlayerService playerService;
	@Autowired
    protected AuthenticationManager authenticationManager;
	
	@GetMapping()
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";
	}
	
	@PostMapping
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult resutl, 
			Errors errors, HttpServletRequest request) {
		if (resutl.hasErrors()) {
			return new ModelAndView("register", "user", userDto);
		}
		playerService.registerNewPlayerAccount(userDto);
		authenticateUserAndSetSession(userDto, request);
		return new ModelAndView("successRegister", "user", userDto);
	}
	
	private void authenticateUserAndSetSession(UserDto user, HttpServletRequest request) {
        String username = user.getEmail();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

        // generate session if one doesn't exist
        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
