package br.com.b2w.desafio.exception;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4361365652280770459L;

	public NotFoundException(String msg) {
		super(msg);
	}
}
