package com.redhat.jbpmdemo.beans;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.security.Identity;

import com.redhat.jbpmdemo.model.SolicitacaoHoraExtra;

@Name("funcionarioService")
public class FuncionarioService {

	@In(required = false, scope = ScopeType.BUSINESS_PROCESS)
	@Out(required = false, scope = ScopeType.BUSINESS_PROCESS)
	private String initiator;

	@In
	private EntityManager entityManager;

	@In
	private Identity identity;

	@In(create = true)
	@Out(required = false)
	private SolicitacaoHoraExtra solicitacao;

	@Out(scope = ScopeType.BUSINESS_PROCESS)
	@In(required = false)
	private Long idSolicitacao;

	@CreateProcess(definition = "AprovacaoHoras")
	@End
	public void solicitarHoraExtra() {
		solicitacao.setFuncionario(identity.getUsername());
		entityManager.persist(solicitacao);
		idSolicitacao = solicitacao.getId();
		initiator = identity.getUsername();
	}

	@StartTask()
	public void avaliarRetorno() {
		solicitacao = entityManager.find(SolicitacaoHoraExtra.class,
				idSolicitacao);
	}

	@EndTask(transition = "Aceitar")
	public void concordar() {
	}

	@EndTask(transition = "Solicitar Reavaliacao")
	public void resubmeter() {
		entityManager.merge(solicitacao);
	}

}
