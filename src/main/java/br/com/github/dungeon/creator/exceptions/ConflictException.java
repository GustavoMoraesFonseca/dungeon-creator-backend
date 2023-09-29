package br.com.github.dungeon.creator.exceptions;

public class ConflictException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConflictException(String msg) {
		super(msg);
	}
}