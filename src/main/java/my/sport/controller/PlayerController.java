package my.sport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/player")
public class PlayerController {

	@GetMapping("/detail")
	public String detailPlayer(@RequestParam(value = "id", required = true) String id) {
		System.out.println(id);
		return "playerInfo";
	}
}
