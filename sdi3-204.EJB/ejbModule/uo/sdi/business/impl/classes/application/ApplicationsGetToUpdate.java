package uo.sdi.business.impl.classes.application;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.persistence.ApplicationDao;

//TODO Las actualizaciones se hacen en el scheluder
@Deprecated
public class ApplicationsGetToUpdate {

    public List<Application> find() {

	ApplicationDao ad = Factories.persistence.createApplicationDao();

	List<Application> applications = ad.findToUpdate();

	ad.deleteToUpdate();

	return applications;
    }

}
