package uo.sdi.business.impl.classes.application;

import java.util.List;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;
import uo.sdi.persistence.ApplicationDao;

public class ApplicationsByTrip {

    public List<Application> find(Long id) {
	ApplicationDao dao = Factories.persistence.createApplicationDao();
	return dao.findByTripId(id);
    }

}
