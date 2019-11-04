package jlpt.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MultipleChoiceQuestion {
	private String question;
	private ChoiceAnswer answers;
	@NotNull
	private ChoiceAnswerKey selectedAnswer;
	private ChoiceAnswerKey correctAnswer;
	public boolean isCorrect() {
		return selectedAnswer != null && selectedAnswer == correctAnswer;
	}
}
