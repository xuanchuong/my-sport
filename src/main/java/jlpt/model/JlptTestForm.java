package jlpt.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JlptTestForm {
	private List<Question> vocabularyQuestions;
	private List<Question> grammaQuestions;
	private List<Question> listeningQuestion;
	
	public JlptTestForm() {
		vocabularyQuestions = initRamdomQuestion();
		grammaQuestions = initRamdomQuestion();
		listeningQuestion = initRamdomQuestion();
	}

	private List<Question> initRamdomQuestion() {
		List<Question> randomQuestions = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			MultipleChoiceQuestion question = new MultipleChoiceQuestion();
			question.setCorrectAnswer(ChoiceAnswer.ramdomAnswer());
			question.setQuestion("question ");
			randomQuestions.add(question);
		}
		return randomQuestions;
	}
}
