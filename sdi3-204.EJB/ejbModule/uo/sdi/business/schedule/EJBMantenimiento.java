package uo.sdi.business.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class EJBMantenimiento {
    
    @Schedule(second = "0", minute = "*/5", hour = "*")
    public void runTask1() {
      //
    } 
    
}
