package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import model.Reservation;
import dao.ReservationDao;

public class ReservationDaoImpl implements ReservationDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void cancelReservation(Reservation reservation) {
		reservation = em.merge(reservation);
		
		em.remove(reservation);
	}

	@Override
	public Reservation getReservation(Integer reservationId) {
	
		//id로 예약을 취득
		return em.find(Reservation.class, reservationId);
	}

	@Override
	public void addReservation(Reservation reservation) {
		em.persist(reservation);
		
	}
}
