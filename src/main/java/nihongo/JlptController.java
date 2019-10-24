package nihongo;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jlpt.model.JlptTestForm;
import profile.HeadNavigator;
import profile.HeaderService;

@Controller
@RequestMapping("jlpt")
public class JlptController {

	@GetMapping
	public String jlptTesting(Model model) {
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.JLPT);
		model.addAttribute("jlptForm", new JlptTestForm());
		return "jlpt";
	}
	
	@PostMapping
	public String processResult(@Valid JlptTestForm result, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "jlpt";
		}
		System.out.println("################ result: " + result);
		return "redirect:/";
	}
}
