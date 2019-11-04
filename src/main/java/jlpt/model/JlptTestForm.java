package jlpt.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JlptTestForm {
	private List<MultipleChoiceQuestion> vocabularyQuestions;
	private List<MultipleChoiceQuestion> grammaQuestions;
	private List<MultipleChoiceQuestion> listeningQuestion;
	
	public JlptTestForm() {
		vocabularyQuestions = new ArrayList<>();
		grammaQuestions = new ArrayList<>();
		listeningQuestion = new ArrayList<>();
	}
}
