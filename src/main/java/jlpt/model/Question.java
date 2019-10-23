package jlpt.model;

import lombok.Data;

@Data
public class Question {
	private String question;
	public static Answer[] options = Answer.values();
	private Answer answer;
}
