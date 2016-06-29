using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using sdi3_204.Cli_NET.ClientService;

namespace sdi3_204.Cli_NET
{
    class Program
    {
       

        static void Main(string[] args)
        {
           
            
	    while (true) {
	        Console.WriteLine("introduce opción 1-4 (0 para salir)");
	        Console.WriteLine("1-Listar usuarios del sistema"
		        + "\n2-Deshabilitar usuario"
		        + "\n3-Listar comentarios y puntuaciones de los viajes "
		        + "realizados en el último mes"
		        + "\n4-Eliminar comentarios y puntuaciones" + "\n0-Salir");
	        String opcion = Console.ReadLine();

	        switch (opcion) {
	            case "0":
		            return;
	            case "1":
		            listarUsuariosSistema();
		            break;
	            case "2":
		            deshabilitarUsuario();
		            break;
	            case "3":
		            listarComentariosYPuntuaciones();
		            break;
	            case "4":
		            eliminarRatting();
		            break;
	            default:
		            break;
	        }

	    }
        }
    

     private static void listarUsuariosSistema(){

	    Console.WriteLine("###listarUsuariosSistema");
        ClientServiceClient cs = new ClientServiceClient();
	    userInfo[] usersInfo = cs.listUsersInfo();

	    foreach(userInfo u in usersInfo)
	        imprimirDatosUsuarioViajes(u);

    }

    private static void imprimirDatosUsuarioViajes(userInfo user) {

	    Console.WriteLine("----------User id: " + user.id
		    + " Info-----------------");

	    Console.WriteLine("\nUsuario: " + user.name + "\t apellidos: "
		    + user.surname + "\t e-mail: " + user.email
		    + "\t status: " + user.status);

	    Console.WriteLine("\nPromocionados: " + user.numPromoted
		    + "\nParticipa: " + user.numParticipated);

	    Console.WriteLine("------------------------------------------------");

    }

    private static void deshabilitarUsuario()
    {
	    Console.WriteLine("###deshabilitarUsuario");
        ClientServiceClient cs = new ClientServiceClient();
	    Console.WriteLine("Introduce Id del Usuario que desea deshabilitar");
        String userId = Console.ReadLine();
	    cs.disableUser(long.Parse(userId));

    }

    private static void listarComentariosYPuntuaciones()
    {
	    Console.WriteLine("###listarComentariosYPuntuaciones");
        ClientServiceClient cs = new ClientServiceClient();
        ratingInfo[] ratings = cs.listRatings();
        if(ratings!=null && ratings.Length>0)
	    imprimirRatings(ratings);

    }

    private static void imprimirRatings(ratingInfo[] ri)
    {
	    Console.WriteLine("----------ratings-----------------");
     
	    foreach (ratingInfo r in ri) {
	        Console.WriteLine("----------------------------------");
	        Console.WriteLine("Destino: " + r.destino
		        + "\nComentario realizado por: " + r.fromUserId
		        + "\nSobre el usuario: " + r.aboutUserId
		        + "\nValoración: " + r.value + "\nComentario: "
		        + r.comment);
	        Console.WriteLine("----------------------------------");
	    }
    }

    private static void eliminarRatting()
    {
	    Console.WriteLine("###eliminarRatting");
        ClientServiceClient cs = new ClientServiceClient();
	    Console.WriteLine("Introduce Id del rating que desea eliminar");
        String ratingId = Console.ReadLine();

	    cs.removeRating(long.Parse(ratingId));

    }
}
}