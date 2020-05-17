package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;
	private Object session;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		return connector.getSession().createCriteria(Participant.class).list();
	}
	
	public Participant findByLogin(String login) {
		return (Participant) connector.getSession().get(Participant.class,login);
	}

	public Participant add(Participant participant) {
		Transaction transaction = ((Session) this.session).beginTransaction();
		((Session) session).save(participant);
		transaction.commit();
		return participant;
		
	}

	public void delete(Participant participant) {
		Transaction transaction = ((Session) this.session).beginTransaction();
		((Session) session).delete(participant);
		transaction.commit();
		//return participant;


		
	}

}
