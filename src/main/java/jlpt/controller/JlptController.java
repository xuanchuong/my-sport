package jlpt.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import jlpt.model.ChoiceAnswer;
import jlpt.model.ChoiceAnswerKey;
import jlpt.model.JlptTestForm;
import jlpt.model.MultipleChoiceQuestion;
import jlpt.service.ChoiceAnswerService;
import profile.HeadNavigator;
import profile.HeaderService;

@Controller
@RestController
@RequestMapping("jlpt")
public class JlptController {
	
	@Autowired
	private ChoiceAnswerService choiceAnswerService;
	
	@GetMapping(value = "/choiceAnswers")
	public List<ChoiceAnswer> getAll() {
		return choiceAnswerService.getAllChoiceAnswers();
	}
	
	@PostMapping("/addChoiceAnswer")
	public HttpStatus insertChoiceAnswer() {
		ChoiceAnswer newValue = new ChoiceAnswer();
		newValue.setAnswerKey(ChoiceAnswerKey.A);
		newValue.setDescription("hello world");
		return choiceAnswerService.addNewChoiceAnswer(newValue) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

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
		long vocabularyPoints = result.getVocabularyQuestions().stream().filter(MultipleChoiceQuestion::isCorrect).count();
		long grammaPoints = result.getGrammaQuestions().stream().filter(MultipleChoiceQuestion::isCorrect).count();
		long listeningPoints = result.getListeningQuestion().stream().filter(MultipleChoiceQuestion::isCorrect).count();
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
