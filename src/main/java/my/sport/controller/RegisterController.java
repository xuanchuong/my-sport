package my.sport.controller;

import lombok.AllArgsConstructor;
import my.sport.application.service.PlayerService;
import my.sport.controller.mapper.PlayerControllerMapper;
import my.sport.rest.dto.CreateUserCommandDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final PlayerService playerService;
    private final PlayerControllerMapper playerMapper;

    @GetMapping()
    public String showRegistrationForm(Model model) {
        CreateUserCommandDTO commandDTO = new CreateUserCommandDTO();
        model.addAttribute("user", commandDTO);
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user") CreateUserCommandDTO userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        playerService.add(playerMapper.map(userDto));
        return "redirect:/login";
    }
}
