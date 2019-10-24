package jlpt.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Answer {
	A, B, D, E;

	private static final List<Answer> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));

	private static final int SIZE = VALUES.size();
	
	private static final Random RAMDOM = new Random();
	
	
	public static Answer ramdomAnswer() {
		return VALUES.get(RAMDOM.nextInt(SIZE));
	}
}
