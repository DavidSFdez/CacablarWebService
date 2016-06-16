package uo.sdi.client;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import uo.sdi.business.ClientService;
import uo.sdi.business.RattingsService;
import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.exception.EntityNotFoundException;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Main {

    public static void main(String[] args) throws Exception {
	run();

    }

    private static void run() throws Exception {
//	metodocutre();

	System.out.println("###listarUsuariosSistema();");
	listarUsuariosSistema();

	System.out.println("###deshabilitarUsuario();");
	deshabilitarUsuario();

	System.out.println("###listarComentariosYPuntuaciones();");
	listarComentariosYPuntuaciones();
	
	System.out.println("###eliminarRatting();");
	eliminarRatting();
    }


    private static void listarUsuariosSistema() throws Exception {
	ClientService cs = 
    }

    private static void imprimirDatosUsuarioViajes(User user,
	    int numberPromoted, int numberParticipated) {
	
	System.out.println("\nId: " + user.getId() + "\nUsuario: "
		+ user.getLogin());

	System.out.println("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);

	
    }

    private static void deshabilitarUsuario() throws Exception {

	
    }

    private static void listarComentariosYPuntuaciones() {
	
	

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
