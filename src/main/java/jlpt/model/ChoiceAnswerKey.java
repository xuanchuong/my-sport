package jlpt.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ChoiceAnswerKey {
	A, B, D, C;

	private static final List<ChoiceAnswerKey> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();
	
	private static final Random RAMDOM = new Random();
	
	
	public static ChoiceAnswerKey ramdomAnswer() {
		return VALUES.get(RAMDOM.nextInt(SIZE));
	}
}
