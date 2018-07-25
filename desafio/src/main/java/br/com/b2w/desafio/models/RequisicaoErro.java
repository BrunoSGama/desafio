package br.com.b2w.desafio.models;

import java.io.Serializable;

public class RequisicaoErro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -355521743667078077L;

	private Long timestamp;
	private int status;
	private String error;
	private String menssage;
	private String path;

	public RequisicaoErro(Long timestamp, int status, String error, String menssage, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.menssage = menssage;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMenssage() {
		return menssage;
	}

	public void setMenssage(String menssage) {
		this.menssage = menssage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
