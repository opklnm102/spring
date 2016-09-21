package dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Event;
import dao.EventDao;

public class EventDaoImpl implements EventDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Event> getEventList(Date start, Date end) {
		
		//������������ �̺�Ʈ�� �˻�
		return em.createQuery(" FROM Event AS event WHERE event.date >= :startDate AND event.date <= :endDate ",
				Event.class).setParameter("startDate", start).setParameter("endDate", end).getResultList();	}

}
