package jlpt.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "multiple_choice_question")
public class MultipleChoiceQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column
	private String question;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "choice_question_answer", joinColumns = {@JoinColumn(name="question_id")}, inverseJoinColumns = {@JoinColumn(name="answer_id")})
	@Column(name = "answers_id")
	private Set<ChoiceAnswer> answers;
	
	private ChoiceAnswerKey selectedAnswer;

	@Enumerated(EnumType.STRING)
	@Column(name = "correct_answer")
	private ChoiceAnswerKey correctAnswer;
	public boolean isCorrect() {
		return selectedAnswer != null && selectedAnswer == correctAnswer;
	}
}
