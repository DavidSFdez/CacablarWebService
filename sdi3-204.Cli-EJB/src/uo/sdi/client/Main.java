package uo.sdi.client;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;

import uo.sdi.business.ClientService;
import uo.sdi.business.RattingsService;
import uo.sdi.business.ServicesFactory;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.business.impl.ejb.EJBUsersService;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Main {

    
    ClientService cs = new RemoteEJBServiceLocator().getClientService();
    
    public static void main(String[] args) throws Exception {
	new Main().run();

    }

    private void run() throws Exception {
//	metodocutre();

	System.out.println("###listarUsuariosSistema();");
	listarUsuariosSistema();

//	System.out.println("###deshabilitarUsuario();");
//	deshabilitarUsuario();
//
//	System.out.println("###listarComentariosYPuntuaciones();");
//	listarComentariosYPuntuaciones();
//	
//	System.out.println("###eliminarRatting();");
//	eliminarRatting();
    }


    private void listarUsuariosSistema() throws Exception {
	
	Scanner in = new Scanner(System.in);
	System.out.println("Introduce Id de usuario");
	Long userId = in.nextLong();
	
	User user = cs.findUserById(userId);
	
	List<Trip> promotedTrips = cs.ListAllTripsPromotedByUser(userId);
	List<Trip> participatedTrips = cs.ListAllTripsWhereUserParticipated(userId);
	
	imprimirDatosUsuarioViajes(user, promotedTrips.size(), participatedTrips.size());
	
    }

    private void imprimirDatosUsuarioViajes(User user,
	    int numberPromoted, int numberParticipated) {
	
	System.out.println("\nId: " + user.getId() + "\nUsuario: "
		+ user.getLogin());

	System.out.println("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);

	
    }

    private void deshabilitarUsuario() {
	Scanner in = new Scanner(System.in);
	System.out.println("Introduce Id de usuario");
	Long userId = in.nextLong();
	
	cs.cancelUsuario(userId);
	
	
    }

    private void listarComentariosYPuntuaciones() {
	
	
	

    }

    private static Date getNewDateMonth(Date actual, int months) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(actual);
	    cal.add(Calendar.MONTH, months); 
	    return cal.getTime();
    }

    private static void imprimeRattings(List<Rating> rattings) {
	// TODO Auto-generated method stub
	
    }
    
    private static void eliminarRatting() {
	
    }

}
