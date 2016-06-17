package uo.sdi.business.impl.classes.seat;

import uo.sdi.infrastructure.Factories;
import uo.sdi.model.Application;

public class ApplicationsFind {

    public Application find(Long id, Long idUser) {
	Long[] ids = { idUser, id };

	Application a = Factories.persistence.createApplicationDao().findById(
		ids);

	return a;

    }

}
