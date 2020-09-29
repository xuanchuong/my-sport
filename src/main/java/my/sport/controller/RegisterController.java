package my.sport.controller;

import lombok.AllArgsConstructor;
import my.sport.application.service.PlayerService;
import my.sport.rest.dto.CreateUserCommandDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final PlayerService playerService;

    @GetMapping()
    public String showRegistrationForm(Model model) {
        CreateUserCommandDTO commandDTO = new CreateUserCommandDTO();
        model.addAttribute("user", commandDTO);
        return "register";
    }

    @PostMapping
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid CreateUserCommandDTO userDto, BindingResult resutl) {
        if (resutl.hasErrors()) {
            return new ModelAndView("register", "user", userDto);
        }
        playerService.add(userDto);
        return new ModelAndView("redirect:/login");
    }
}
