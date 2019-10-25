package jlpt.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MultipleChoiceQuestion extends Question{
	private String question;
	public static ChoiceAnswer[] options = ChoiceAnswer.values();
	@NotNull
	private ChoiceAnswer answer;
	private ChoiceAnswer correctAnswer;
}
