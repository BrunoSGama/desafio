package br.com.b2w.desafio.exception;

public class ServicoIndisponivelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3636806309900660657L;

	public ServicoIndisponivelException(String msg) {
		super(msg);
	}
}
