package jlpt.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JlptTestForm {
	private List<Question> questions;
	
	public JlptTestForm() {
		questions = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Question question = new Question();
			question.setQuestion("question " + i);
			questions.add(question);
		}
	}
}
