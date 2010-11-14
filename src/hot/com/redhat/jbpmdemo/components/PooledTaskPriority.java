package com.redhat.jbpmdemo.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.Unwrap;
import org.jboss.seam.bpm.Actor;
import org.jbpm.JbpmContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

@Name("pooledTaskPriorityList")
@Install(dependencies = "org.jboss.seam.bpm.jbpm", precedence = Install.BUILT_IN)
public class PooledTaskPriority implements Serializable {

	private static final long serialVersionUID = 1L;

	@In
	private Actor actor;

	@In
	private JbpmContext jbpmContext;

	@Unwrap
	@Transactional
	public List<TaskInstance> getTaskPriority() {
		List<TaskInstance> tasks = jbpmContext.getTaskMgmtSession()
				.findPooledTaskInstances(
						new ArrayList<String>(actor.getGroupActorIds()));
		Collections.sort(tasks, new Comparator<TaskInstance>() {
			public int compare(TaskInstance o1, TaskInstance o2) {
				return (o1.getPriority() > o2.getPriority()) ? 1 : (o1
						.getPriority() < o2.getPriority() ? -1 : 0);
			}
		});
		return tasks;
	}
}
