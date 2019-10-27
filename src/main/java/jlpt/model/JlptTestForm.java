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
			question.setCorrectAnswer(ChoiceAnswerKey.ramdomAnswer());
			question.setQuestion("question ");
			ChoiceAnswer answers = new ChoiceAnswer();
			answers.getOptions().put(ChoiceAnswerKey.A, "dap an 1");
			answers.getOptions().put(ChoiceAnswerKey.B, "dap an 2");
			answers.getOptions().put(ChoiceAnswerKey.E, "dap an 3");
			answers.getOptions().put(ChoiceAnswerKey.D, "dap an 4");
			
			question.setAnswers(answers);
			randomQuestions.add(question);
		}
		return randomQuestions;
	}
}
