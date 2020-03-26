package br.com.alura.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDao;
import br.com.alura.model.AgendamentoEmail;

@Stateless
public class AgendamentoEmailBusiness {

	@Inject
	private AgendamentoEmailDao dao;
	
	public List<AgendamentoEmail> listarAgendamentosEmail(){
		return dao.listarAgendamentosEmail();
	}

	public void salvarAgendamentoEmail(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setEnviado(false);
		dao.salvarAgendamentoEmail(agendamentoEmail);
	}
}
