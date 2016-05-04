package uo.sdi.client;

import java.util.List;

import uo.sdi.business.TripsService;
import uo.sdi.business.UsersService;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.Trip;
import uo.sdi.model.User;

public class Main {

    public static void main(String[] args) {
	// TODO Auto-generated method stub

    }

    private void run() throws Exception {
	User user;
	
	UsersService us = new RemoteEJBServiceLocator().createUsersService();
	user = us.findById(101L);
	
	TripsService ts = new RemoteEJBServiceLocator().createTripsService();
	
	List<Trip> trips = ts.listRelated(user.getId());
	
	imprimirDatos(user,trips);
	
	
	
	
	/*AlumnosService service = new RemoteEjbServicesLocator().getAlumnosService();
	List<Alumno> alumnos = service.getAlumnos();
	printHeader();
	for (Alumno a : alumnos) {
	printLine(a);
	}*/
	}

    private void imprimirDatos(User user, List<Trip> trips) {
	System.out.println("\nUsuario: "+user.getLogin()+"\nId: "+user.getId());
	int promocionados=0;
	int participa=0;
	for(Trip t : trips){
	    if(t.getPromoterId().equals(user.getId()))
		promocionados++;
	    else
		participa++;
	}
	    System.out.println("\nPromocionados: "+promocionados+"\nParticipa: "+participa);
	
    }
}
