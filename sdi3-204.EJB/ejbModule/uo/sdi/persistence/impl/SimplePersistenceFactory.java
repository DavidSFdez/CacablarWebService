package uo.sdi.persistence.impl;


import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.Transaction;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author alb
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

    @Override
    public UserDao createUserDao() {
	return new UserDaoJdbcImpl();
    }

    @Override
    public TripDao createTripDao() {
	return new TripDaoJdbcImpl();
    }

    @Override
    public SeatDao createSeatDao() {
	return new SeatDaoJdbcImpl();
    }

    @Override
    public ApplicationDao createApplicationDao() {
	return new ApplicationDaoJdbcImpl();
    }

    @Override
    public Transaction createTransaction() {
	return new TransactionJdbcImpl();
    }



}
