package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Meeting;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	private Object session;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Meeting getId(Long id) {
		return (Meeting) connector.getSession().get(Meeting.class,id);
	}

	public Meeting add(Meeting meeting) {
		Transaction transaction = ((Session) this.session).beginTransaction();
		((Session) session).save(meeting);
		transaction.commit();
		return meeting;
	}

}
