package com.redhat.jbpmdemo.beans;

import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;
import org.jbpm.identity.Membership;
import org.jbpm.identity.User;

@Name("authenticator")
public class Authenticator {
	
	@Logger
	private Log log;

	@In
	private Identity identity;

	@In
	private EntityManager entityManager;

	@In(required=false)
	private Actor actor;

	public boolean authenticate() {
		Query query = entityManager.createQuery("from " + User.class.getName()
				+ "  u where u.name = :name and u.password = :password");
		query.setParameter("name", identity.getUsername());
		query.setParameter("password", identity.getPassword());
		User user = (User) query.getSingleResult();
		if (user == null) {
			return false;
		} else {
			actor.setId(user.getName());
			Iterator<Membership> memberships = user.getMemberships().iterator();
			while (memberships.hasNext()) {
				Membership m = memberships.next();
				actor.getGroupActorIds().add(m.getGroup().getName());
				identity.addRole(m.getGroup().getName());
			}
		}
		return true;
	}
}
