package main.java.algo;

@SuppressWarnings("serial")
public class BadInputGraphException extends RuntimeException {

	public BadInputGraphException(String input) {
		super(input);
	}
}