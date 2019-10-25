package jlpt.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ChoiceAnswer {
	A, B, D, E;

	private static final List<ChoiceAnswer> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();
	
	private static final Random RAMDOM = new Random();
	
	
	public static ChoiceAnswer ramdomAnswer() {
		return VALUES.get(RAMDOM.nextInt(SIZE));
	}
}
