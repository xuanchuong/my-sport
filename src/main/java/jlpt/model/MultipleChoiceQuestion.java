package jlpt.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceQuestion extends Question{
	private String question;
	private ChoiceAnswer answers;
	@NotNull
	private ChoiceAnswerKey selectedAnswer;
	private ChoiceAnswerKey correctAnswer;
	@Override
	public boolean isCorrect() {
		return selectedAnswer != null && selectedAnswer == correctAnswer;
	}
}
