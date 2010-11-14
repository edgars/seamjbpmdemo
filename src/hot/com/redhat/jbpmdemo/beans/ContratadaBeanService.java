package com.redhat.jbpmdemo.beans;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.log.Log;
import org.jboss.seam.international.StatusMessages;

import com.redhat.jbpmdemo.model.Contratada;

@Name("contratadaBeanService")
public class ContratadaBeanService
{
    @Logger private Log log;

    @In StatusMessages statusMessages;
    
	@In
	private EntityManager entityManager;
	
	@In(scope=ScopeType.BUSINESS_PROCESS)
	private Long idContratada;
	
	@In(create = true)
	@Out(required = false)
	private Contratada contratada;
	
	@StartTask()
	public String complementaCadastro(){
		statusMessages.add("Tarefa Inicializada!");
		contratada = entityManager.find(Contratada.class, idContratada);
		return "complementaCadastro";
	}
	@Begin(join=true)
	@EndTask(transition="contratada.solicitafinanciamento")
	@End
	public void enviaParaBanco(){
		statusMessages.add("Tarefa Finalizada, o próximo passo será você esperar a Aprovação Bancária!");
		entityManager.merge(contratada);
		
	}
	
	
	
	@StartTask
	@EndTask(transition="bancos.apresentaproposta") // Grupo Bancos
	@End
    public void enviaProposta()
    {

        statusMessages.add("Proposta de Financiamento Enviada para o Banco!");
        
    }


    public void make()
    {
        // implement your business logic here
        log.info("contratadaBeanService.contratadaBeanService() action called");
        statusMessages.add("contratadaBeanService");
        
    }

    // add additional action methods

}
