package jlpt.model;

import lombok.Data;

@Data
public abstract class Question {
	public abstract boolean isCorrect();
}
