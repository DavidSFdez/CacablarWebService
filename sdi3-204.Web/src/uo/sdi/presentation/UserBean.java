package uo.sdi.presentation;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import alb.util.log.Log;
import uo.sdi.business.exception.EntityAlreadyExistsException;
import uo.sdi.infrastructure.Factories;
import uo.sdi.model.User;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = -6981760995314343162L;

    private User user;

    public UserBean() {
	user = new User();
    }

    private void addMessage(FacesMessage message) {
	FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isNotLogged() {
	return null == user.getId();
    }

    public boolean isLogged() {
	if (user == null)
	    return false;
	return null != user.getId();
    }

    public String register() {
	Log.trace("Iniciando registro de usuario.");
	try {
	    user = Factories.services.getUsersService().saveUser(user);
	    Log.debug("Usuario encontrado: " + user);
	} catch (EntityAlreadyExistsException e) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "El usuario ya eiste.");

	    addMessage(message);
	    Log.error("El usuario ya eiste.", e);
	    return "fracaso";
	}
	Log.info("El usuario se ha creado con éxito.");
	return "exito";
    }

    public String login() {
	Log.trace("Iniciando identificación de usuario.");
	user = Factories.services.createLoginService().verify(user.getLogin(),
		user.getPassword());
	Log.debug("Usuario encontrado: " + user);
	if (user == null)
	    user = new User();

	if (user.getId() == null) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		    "ERROR", "Login incorrecto");

	    addMessage(message);
	    Log.error("No se ha encontrado el usuario");
	    return "fracaso";
	} else {
	    Log.info("Usuario identificado con éxito.");
	    return "exito";
	}
    }

    public String logout() {
	Log.trace("Iniciando proceso de logout.");
	user = new User();
	Log.info("Desconectado con éxito.");
	return "exito";
    }

    public void checkIfNotLogged() throws IOException {
	if (isLogged()) {
	    ExternalContext ec = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
	}
    }

    public void checkIfLogged() throws IOException {
	if (!isLogged()) {
	    ExternalContext ec = FacesContext.getCurrentInstance()
		    .getExternalContext();
	    ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
	}
    }
}
