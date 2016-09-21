package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.RankDao;

public class RankDaoImpl implements RankDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> getRank(Integer eventId) {

		return em
				.createQuery(
						" SELECT rank.rankId, rank.name, rank.price, COUNT(*) "
								+ " FROM Rank AS rank INNER JOIN rank.tickets AS ticket "
								+ " LEFT OUTER JOIN ticket.reservation reservation "
								+ " WHERE rank.event.eventId = :eventId AND reservation IS NULL GROUP BY rank.rankId ")
				.setParameter("eventId", eventId).getResultList();
	}
}
