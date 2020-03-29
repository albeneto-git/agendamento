package br.com.alura.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class BusinessException extends RuntimeException{

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
