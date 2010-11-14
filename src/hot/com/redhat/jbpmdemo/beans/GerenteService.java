package com.redhat.jbpmdemo.beans;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;

import com.redhat.jbpmdemo.model.SolicitacaoHoraExtra;

@Name("gerenteService")
@Scope(ScopeType.CONVERSATION)
public class GerenteService {
	
	@In
	private EntityManager entityManager;
	
	@In(scope=ScopeType.BUSINESS_PROCESS)
	private Long idSolicitacao;
	
	@In(required=false)
	@Out(required=false)
	private SolicitacaoHoraExtra solicitacao;
	
	@StartTask()
	public void avaliarSolicitacao(){
		solicitacao = entityManager.find(SolicitacaoHoraExtra.class, idSolicitacao);
	}
	
	@EndTask(transition="Reprovar")
	@End
	public void reprovar(){
	}
	
	@EndTask(transition="Aprovar")
	@End
	public void aprovar(){
		entityManager.merge(solicitacao);
	}
	
}
