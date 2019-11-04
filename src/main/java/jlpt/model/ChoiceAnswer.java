package jlpt.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "choiceanswer", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")
})
public class ChoiceAnswer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4495813904117257923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ElementCollection(targetClass=ChoiceAnswerKey.class)
	@Column(name = "options")
	private Map<ChoiceAnswerKey, String> options;
	
	public ChoiceAnswer() {
		id = Long.valueOf(123);
		options = new HashMap<>();
	}
	
	public static ChoiceAnswer initChoiceAnser(String answer1, String answer2, String answer3, String answer4) {
		ChoiceAnswer answers = new ChoiceAnswer();
		answers.getOptions().put(ChoiceAnswerKey.A, answer1);
		answers.getOptions().put(ChoiceAnswerKey.B, answer2);
		answers.getOptions().put(ChoiceAnswerKey.C, answer3);
		answers.getOptions().put(ChoiceAnswerKey.D, answer4);
		return answers;
	}
}
