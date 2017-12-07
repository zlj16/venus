
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.meidusa.venus.backend.services.ServiceManager;

	
public class TestManager {
	
	@Resource
	private ServiceManager serviceManager;

	/*public ServiceManager getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}*/
	
}
