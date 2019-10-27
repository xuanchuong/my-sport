package jlpt.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChoiceAnswer extends Answer{
	private Map<ChoiceAnswerKey, String> options;
	
	public ChoiceAnswer() {
		options = new HashMap<>();
	}
}
