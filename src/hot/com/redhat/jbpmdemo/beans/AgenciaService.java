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

import com.redhat.jbpmdemo.model.Contratada;


@Name("agenciaService")
public class AgenciaService {
	
	@In(required = false, scope = ScopeType.BUSINESS_PROCESS)
	@Out(required = false, scope = ScopeType.BUSINESS_PROCESS)
	private String initiator;

	@In
	private EntityManager entityManager;

	@In
	private Identity identity;

	@In(create = true)
	@Out(required = false)
	private Contratada contratada;

	@Out(scope = ScopeType.BUSINESS_PROCESS)
	@In(required = false)
	private Long idContratada;
	
	@CreateProcess(definition = "SolicitacaoFinanciamento")
	@End
	public String preCadastraProposta() {
		
		
		entityManager.persist(contratada);
		idContratada = contratada.getId();
		initiator = identity.getCredentials().getUsername();
		
		return "precadadastroOk";
		
	}
	
     @StartTask() @EndTask(transition = "agencia-reguladora.envia.complementadados")
	public void enviarParaContratada() {
		contratada = entityManager.find(Contratada.class,idContratada);
	}
     
 	public String getDebug(){
		contratada = entityManager.find(Contratada.class, idContratada);
		return "complementaCadastro";
	}

}
