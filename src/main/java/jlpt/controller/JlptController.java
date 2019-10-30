package jlpt.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import jlpt.model.JlptTestForm;
import jlpt.model.Question;
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
	public String processResult(@Valid JlptTestForm result, BindingResult bindingResult, Model model, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			return "jlpt";
		}
		long vocabularyPoints = result.getVocabularyQuestions().stream().filter(Question::isCorrect).count();
		long grammaPoints = result.getGrammaQuestions().stream().filter(Question::isCorrect).count();
		long listeningPoints = result.getListeningQuestion().stream().filter(Question::isCorrect).count();
		model.addAttribute("vocabulary", vocabularyPoints);
		model.addAttribute("gramma", grammaPoints);
		model.addAttribute("listening", listeningPoints);
		model.addAttribute(HeaderService.HEADER_ATTR, HeaderService.getNavigatoritems());
		model.addAttribute(HeaderService.SELECTED_ITEM_ATTR, HeadNavigator.JLPT);
		model.addAttribute("jlptResult", result);
		sessionStatus.setComplete();
		return "jlptResult";
	}
}
