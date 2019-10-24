package jlpt.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuestionValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (!(target instanceof Question)) {
			throw new IllegalArgumentException();
		}
		Question question = (Question) target;
		if (question.getCorrectAnswer() != question.getAnswer()) {
			errors.reject("wrong answer");
		}
	}

}
