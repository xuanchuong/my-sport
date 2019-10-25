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
		vocabularyQuestions = initRamdomQuestion();
		grammaQuestions = initRamdomQuestion();
		listeningQuestion = initRamdomQuestion();
	}

	private List<MultipleChoiceQuestion> initRamdomQuestion() {
		List<MultipleChoiceQuestion> randomQuestions = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			MultipleChoiceQuestion question = new MultipleChoiceQuestion();
			question.setCorrectAnswer(Answer.ramdomAnswer());
			question.setQuestion("question ");
			randomQuestions.add(question);
		}
		return randomQuestions;
	}
}
