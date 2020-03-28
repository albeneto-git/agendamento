package br.com.alura.exception;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

	private static final long serialVersionUID = 1L;

	private List<String> mensagens;
	
	public BusinessException() {
		super();
	}

	public BusinessException(String mensagem) {
		super(mensagem);
		
		mensagens = new ArrayList<>();
		mensagens.add(mensagem);
		
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void addMensagem(String mensagem) {
		this.mensagens.add(mensagem);
	}	
}
