package uo.sdi.business.impl.ejb;

import javax.ejb.EJB;

import uo.sdi.business.UsersService;
import uo.sdi.business.impl.local.LocalClientService;
import uo.sdi.business.impl.local.LocalUsersService;
import uo.sdi.business.impl.remote.RemoteClientService;

public class EJBClientService implements LocalClientService,
	RemoteClientService {

    @EJB(beanInterface = LocalUsersService.class)
    private UsersService usersService;

}
