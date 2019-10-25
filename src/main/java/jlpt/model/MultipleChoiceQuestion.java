package jlpt.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MultipleChoiceQuestion {
	private String question;
	public static Answer[] options = Answer.values();
	@NotNull
	private Answer answer;
	private Answer correctAnswer;
}
