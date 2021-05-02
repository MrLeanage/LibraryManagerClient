package services.rmiService;

import java.rmi.Naming;

/**
 * Basic Example of an RMI Client
 * @author Marc Conrad
 *
 */
public class RMIClient {
	public static RMIInterface rmiInterface = null;
	public static boolean startClient() {
		try {
			rmiInterface = (RMIInterface) Naming.lookup("rmi://localhost/RMIServer");
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	public static void clearRMISession(){
		rmiInterface = null;
	}
	public static boolean isServerUp(){
		return rmiInterface != null;
	}
}
