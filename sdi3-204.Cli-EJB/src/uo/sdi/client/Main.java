package uo.sdi.client;

import java.util.List;
import java.util.Scanner;

import uo.sdi.business.ClientService;
import uo.sdi.business.impl.RemoteEJBServiceLocator;
import uo.sdi.model.User;
import uo.sdi.model.DTO.RatingInfo;
import uo.sdi.model.DTO.UserInfo;

public class Main {

    ClientService cs = new RemoteEJBServiceLocator().getClientService();
    Scanner in = null;

    public static void main(String[] args) throws Exception {
	new Main().run();

    }

    private void run() throws Exception {

	in = new Scanner(System.in);

	while (true) {
	    System.out.println("introduce opción 1-4 (0 para salir)");
	    System.out.println("1-Listar usuarios del sistema"
		    + "\n2-Deshabilitar usuario"
		    + "\n3-Listar comentarios y puntuaciones de los viajes "
		    + "realizados en el último mes"
		    + "\n4-Eliminar comentarios y puntuaciones" + "\n0-Salir");
	    String opcion = in.next();

	    switch (opcion) {
	    case "0":
		in.close();
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

    private void listarUsuariosSistema() throws Exception {

	System.out.println("###listarUsuariosSistema");

	List<UserInfo> usersInfo = cs.listUsersInfo();

	for (UserInfo u : usersInfo)
	    imprimirDatosUsuarioViajes(u);

    }

    private void imprimirDatosUsuarioViajes(UserInfo user) {

	System.out.println("----------User id: " + user.getUser().getId()
		+ " Info-----------------");

	System.out.println("\nUsuario: " + user.getUser().getLogin()
		+ "\t apellidos: " + user.getUser().getSurname()
		+ "\t e-mail: " + user.getUser().getEmail() + "\t status: "
		+ user.getUser().getStatus());

	System.out.println("\nPromocionados: " + user.getNumPromoted()
		+ "\nParticipa: " + user.getNumParticipated());

	System.out.println("------------------------------------------------");

    }

    private void deshabilitarUsuario() {
	System.out.println("###deshabilitarUsuario");

	System.out.println("Introduce Id del Usuario que desea deshabilitar");
	Long userId = in.nextLong();

	cs.disableUser(userId);

    }

    private void listarComentariosYPuntuaciones() {
	System.out.println("###listarComentariosYPuntuaciones");

	List<RatingInfo> ratings = cs.listRatings();

	imprimirRatings(ratings);

    }

    private void imprimirRatings(List<RatingInfo> ri) {
	System.out.println("----------ratings-----------------");

	for (RatingInfo r : ri) {
	    System.out.println("----------------------------------");
	    System.out.println("Destino: " + r.getDestino()
		    + "\nComentario realizado por: "
		    + r.getFromUserId()
		    + "\nSobre el usuario: "
		    + r.getAboutUserId() + "\nValoración: "
		    + r.getValue() + "\nComentario: "
		    + r.getComment());
	    System.out.println("----------------------------------");
	}
    }

    private void eliminarRatting() {
	System.out.println("###eliminarRatting");

	System.out.println("Introduce Id del rating que desea eliminar");
	Long ratingId = in.nextLong();

	cs.removeRating(ratingId);

    }

}
