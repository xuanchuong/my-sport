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
		vocabularyQuestions = initRamdomQuestions();
		grammaQuestions = initRamdomQuestions();
		listeningQuestion = initRamdomQuestions();
	}

	private List<Question> initRamdomQuestions() {
		List<Question> randomQuestions = new ArrayList<>();
		
		ChoiceAnswer answer = initChoiceAnser("たいきょう", "だいひょ", "だいひょう", "たいひょ");
		MultipleChoiceQuestion question = initRamdomQuestion("山田さんはクラスの<u>代表</u>に選ばれた。", ChoiceAnswerKey.C, answer);
		randomQuestions.add(question);
		
		ChoiceAnswer answer2 = initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question2 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer2);
		randomQuestions.add(question2);
		
		return randomQuestions;
	}
	
	private ChoiceAnswer initChoiceAnser(String answer1, String answer2, String answer3, String answer4) {
		ChoiceAnswer answers = new ChoiceAnswer();
		answers.getOptions().put(ChoiceAnswerKey.A, answer1);
		answers.getOptions().put(ChoiceAnswerKey.B, answer2);
		answers.getOptions().put(ChoiceAnswerKey.C, answer3);
		answers.getOptions().put(ChoiceAnswerKey.D, answer4);
		return answers;
	}

	private MultipleChoiceQuestion initRamdomQuestion(String questionTitle, ChoiceAnswerKey correctAnswerKey, ChoiceAnswer answers) {
		MultipleChoiceQuestion question = new MultipleChoiceQuestion();
		question.setCorrectAnswer(correctAnswerKey);
		question.setQuestion(questionTitle);
		question.setAnswers(answers);
		return question;
	}
}
