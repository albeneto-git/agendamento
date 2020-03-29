package br.com.alura.business;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.exception.BusinessException;
import br.com.alura.interception.Logger;
import br.com.alura.model.AgendamentoEmail;

@Stateless
@Logger
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AgendamentoEmailBusiness {

	@Inject
	private AgendamentoEmailDao dao;
	
	@Resource(lookup = "java:jboss/mail/AgendamentoMailSession")
	private Session sessaoEmail;
	
	private static String EMAIL_FROM = "smtp.mailtrap.io";
	private static String EMAIL_USER = "c6e6351826a33a";
	private static String EMAIL_PASSWORD = "6ac6b86d58187d";	
	
	public List<AgendamentoEmail> listarAgendamentosEmail(){
		return dao.listarAgendamentosEmail();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvarAgendamentoEmail(@Valid AgendamentoEmail agendamentoEmail) throws BusinessException {
		
		if(!dao.listarAgendamentoEmailPorEmail(agendamentoEmail.getEmail()).isEmpty()) {
			throw new BusinessException("Email já esta agendado");
		}
		
		agendamentoEmail.setEnviado(false);
		dao.salvarAgendamentoEmail(agendamentoEmail);
		throw new BusinessException();
	}
	
	public List<AgendamentoEmail> listarAgendamentoEmailNaoEnviados(){
		return dao.listarAgendamentoEmailNaoEnviados();
	}
	
	public void enviarEmail(AgendamentoEmail agendamentoEmail) {
		try {
		    MimeMessage mensagem = new MimeMessage(sessaoEmail);
		    mensagem.setFrom(EMAIL_FROM);
		    mensagem.setRecipients(Message.RecipientType.TO, agendamentoEmail.getEmail());
		    mensagem.setSubject(agendamentoEmail.getAssunto());
		    mensagem.setText(Optional.ofNullable(agendamentoEmail.getMensagem()).orElse(""));
		    Transport.send(mensagem,
		    EMAIL_USER,
		    EMAIL_PASSWORD);
		} catch (MessagingException e) {
		    throw new RuntimeException(e);
		}		
	}
	
	public void marcarEnviadas(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setEnviado(true);
		dao.atualizarAgendamentoEmail(agendamentoEmail);
	}
	
}
