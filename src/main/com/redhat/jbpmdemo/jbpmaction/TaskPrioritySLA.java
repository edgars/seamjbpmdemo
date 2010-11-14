package com.redhat.jbpmdemo.jbpmaction;

import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class TaskPrioritySLA implements ActionHandler {

	private static final long serialVersionUID = 1L;

	public void execute(ExecutionContext executionContext) throws Exception {
		try {
			int currentPriority = executionContext.getTaskInstance()
					.getPriority();
			// Maximum priority of a given task is 1
			if (currentPriority > 1) {
				currentPriority--;
				TaskInstance t = executionContext.getJbpmContext()
						.getTaskInstanceForUpdate(
								executionContext.getTaskInstance().getId());
				t.setPriority(currentPriority);
			} else {
				// we could now redirect the user to another node in the process
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
