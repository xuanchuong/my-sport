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
		vocabularyQuestions = initRamdomQuestions();
		grammaQuestions = initRamdomQuestions();
		listeningQuestion = initRamdomQuestions();
	}

	private List<MultipleChoiceQuestion> initRamdomQuestions() {
		List<MultipleChoiceQuestion> randomQuestions = new ArrayList<>();
		
		ChoiceAnswer answer = ChoiceAnswer.initChoiceAnser("たいきょう", "だいひょ", "だいひょう", "たいひょ");
		MultipleChoiceQuestion question = initRamdomQuestion("山田さんはクラスの<u>代表</u>に選ばれた。", ChoiceAnswerKey.C, answer);
		randomQuestions.add(question);
		
		ChoiceAnswer answer2 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question2 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer2);
		randomQuestions.add(question2);
		
		ChoiceAnswer answer3 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question3 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer3);
		randomQuestions.add(question3);
		
		ChoiceAnswer answer4 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question4 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer4);
		randomQuestions.add(question4);
		
		ChoiceAnswer answer5 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question5 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer5);
		randomQuestions.add(question5);
		
		ChoiceAnswer answer6 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question6 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer6);
		randomQuestions.add(question6);
		
		ChoiceAnswer answer7 = ChoiceAnswer.initChoiceAnser("助けて", "守けて", "支けて", "協けて");
		MultipleChoiceQuestion question7 = initRamdomQuestion("困っているときに、先生にたすけていただきました。", ChoiceAnswerKey.A, answer7);
		randomQuestions.add(question7);
		
		return randomQuestions;
	}
	

	private MultipleChoiceQuestion initRamdomQuestion(String questionTitle, ChoiceAnswerKey correctAnswerKey, ChoiceAnswer answers) {
		MultipleChoiceQuestion question = new MultipleChoiceQuestion();
		question.setCorrectAnswer(correctAnswerKey);
		question.setQuestion(questionTitle);
		question.setAnswers(answers);
		return question;
	}
}
