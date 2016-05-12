using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using sdi3_204.Cli_NET.EJBUsersService;
using sdi3_204.Cli_NET.UsersService;
using sdi3_204.Cli_NET.TripsService;
using sdi3_204.Cli_NET.RattingsService;

namespace sdi3_204.Cli_NET
{
    class Program
    {
        static void Main(string[] args)
        {
        }

        private static void run() {
	metodocutre();

	 Console.WriteLine("###listarUsuariosSistema();");
	 listarUsuariosSistema();
	
	 Console.WriteLine("###deshabilitarUsuario();");
	 deshabilitarUsuario();
	
	 Console.WriteLine("###listarComentariosYPuntuaciones();");
	 listarComentariosYPuntuaciones();
	
	 Console.WriteLine("###eliminarRatting();");
	 eliminarRatting();
    }

    private static void metodocutre() {

	UsersServiceClient us = new UsersServiceClient();

	Console.WriteLine("Usuarios del sistema");
	user[] users = us.findAllUsers();
	foreach (user u in users)
	    Console.WriteLine(u);

	Console.WriteLine("Viajes del sistema");
	trip[] trips = new TripsServiceClient().findAllTrips();
		
	foreach (trip t in trips)
	    Console.WriteLine(t);
    }

    private static void listarUsuariosSistema(){
	UsersServiceClient us = new UsersServiceClient();
	TripsServiceClient ts = new TripsServiceClient();
	user[] users = us.findAllUsers();
	Console.WriteLine("-----------------------------");
	foreach (user u in users) {
	    trip[] promotedTrips = ts.findAllPromoted(u.id);
	    trip[] participateTrips = ts.findAllParticipated(u.id);
	    imprimirDatosUsuarioViajes(u, promotedTrips.Length,
		    participateTrips.Length);
	    Console.WriteLine("-------");
	}
    }

private static void imprimirDatosUsuarioViajes(user u,int numberPromoted,int numberParticipated)
{
 	  

	Console.WriteLine("\nId: " + u.id + "\nUsuario: "
		+ u.login);

	Console.WriteLine("\nPromocionados: " + numberPromoted
		+ "\nParticipa: " + numberParticipated);

}

  

    private static void deshabilitarUsuario() {

	user user;

	UsersServiceClient us = new UsersServiceClient();
	user = us.findUserById(101L);

	if (user != null)
	    us.cancelUser(user.id);
    }

    private static void listarComentariosYPuntuaciones() {

	RattingsServiceClient rs= new RattingsServiceClient();
		
	TripsServiceClient ts = new TripsServiceClient();




	    trip[] trips = ts.findAllTrips();

	    IList<trip> tripsLastMonth = new List<trip>();
	    DateTime actual = new DateTime();
	    DateTime unMesAntes = getNewDateMonth(actual, -1);

	    foreach (trip t in trips) {
		//if (t.arrivalDate.toGregorianCalendar().getTime()
			//.after(unMesAntes)
			//&& t.getArrivalDate().toGregorianCalendar().getTime()
				//.before(actual)) {
		    tripsLastMonth.Add(t);
		}
	    

	    IList<rating> rattings = new List<rating>();
	    foreach (trip t in tripsLastMonth) {
		rattings.Add(rs.listByTrip(t.id));
	    }

	    imprimeRattings(rattings);

	}

private static void imprimeRattings(IList<rating> rattings)
{
 	throw new NotImplementedException();
}

private static DateTime getNewDateMonth(DateTime actual,int p)
{
 	throw new NotImplementedException();
} 

   

private static void eliminarRatting() {
	RattingsServiceClient rs= new RattingsServiceClient();

	rating r = new rating();

	rs.delete(r.id);

    }
    }
}
