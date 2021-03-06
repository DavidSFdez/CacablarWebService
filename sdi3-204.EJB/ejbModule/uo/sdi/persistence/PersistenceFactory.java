package uo.sdi.persistence;

/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada
 * de persistencia para cada Entidad del Modelo (en este caso solo hay una:
 * Alumno; pero en futuras versiones habr�� m��s)
 * 
 * @author alb
 * 
 */
public interface PersistenceFactory {

    UserDao createUserDao();

    TripDao createTripDao();

    SeatDao createSeatDao();

    ApplicationDao createApplicationDao();

    Transaction createTransaction();

    RatingDao createRattingDao();

}
