package uo.sdi.business.impl.classes.maintenance;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.persistence.ApplicationDao;

//TODO cambiar nombre
public class ApplicationsGetToUpdate {

    public List<Application> find() {

	ApplicationDao ad = Factories.persistence.createApplicationDao();

	List<Application> applications = ad.findToUpdate();

	ad.deleteToUpdate();

	return applications;
    }

}
