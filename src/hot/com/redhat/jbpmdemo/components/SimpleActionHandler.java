package com.redhat.jbpmdemo.components;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;

public class SimpleActionHandler implements ActionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8069610821186549625L;

	public void execute(ExecutionContext context) throws Exception {
		
		
		 System.out.println("Passando pelo JBPM da IDContrada: " +
		 
		 context.getProcessInstance().getContextInstance().getVariable("idContratada")
		 );
		 

	}

}
