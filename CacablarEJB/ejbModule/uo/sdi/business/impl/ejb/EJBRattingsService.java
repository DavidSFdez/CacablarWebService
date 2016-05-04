package uo.sdi.business.impl.ejb;

import javax.ejb.Stateless;

import uo.sdi.business.impl.local.LocalRattingsService;
import uo.sdi.business.impl.remote.RemoteRattingsService;

@Stateless
public class EJBRattingsService implements LocalRattingsService,RemoteRattingsService {
 
}
