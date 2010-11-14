package com.redhat.jbpmdemo.beans;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jboss.seam.international.StatusMessages;

import com.redhat.jbpmdemo.model.Contratada;

@Name("bancos")
public class Bancos
{
    @Logger private Log log;
    
	@In
	private Identity identity;

    @In StatusMessages statusMessages;
    
    @In
	private EntityManager entityManager;
	
	@In(scope=ScopeType.BUSINESS_PROCESS)
	private Long idContratada;
	
	@In(create = true)
	@Out(required = false)
	private Contratada contratada;

	@StartTask()

    public String forneceFinanciamento()
    {

        log.info("bancos.forneceFinanciamento() action called");
       
        contratada = entityManager.find(Contratada.class, idContratada);
        contratada.setBanco(identity.getCredentials().getUsername());
        entityManager.merge(contratada);
        
        statusMessages.add("Tafefa de Aprovação para " + contratada.getRazao() + " foi iniciada");
        
        return "finalizarBanco";
        
        
    }
	
	@EndTask(transition="fim")
	@End
    public String aprova()
    {
		
		statusMessages.add("Crédito Concedido e Processo Finalizado");
        return "fim";
        
        
    }
	
	@EndTask(transition="reprovado")
	@End
    public String reprova()
    {
		
		statusMessages.add("Crédito Não Concedido e Processo Finalizado");
        return "fim";
        
        
    }

    

}
