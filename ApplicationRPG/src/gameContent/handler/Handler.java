package gameContent.handler;

import gameContent.threat.Threat;

public abstract class Handler {
	//successeur dans la chaine de responsabilité
    private Handler successor;
    private Class[] handableRequest;
    //si le handler est détruit il est inutil
    private boolean isDestroyed = false;

	public Handler(Class[] handableRequest) {
        this.handableRequest = handableRequest;
    }
    
	/**
	 * Demande au successeur de gérer la menace si besoin est.
	 * @param threat : menace à gérer
	 */
    public void handleRequest(Threat threat) {
    	if(threat.isThreatening()) {
	    	if(successor != null) {
	            successor.handleRequest(threat);
	        }
    	}
    }
    
    public boolean canHandle(Threat threat) {
    	boolean canHandle = isDestroyed == false;
    	if(canHandle) {
    		canHandle = false;
    		for (Class handableClass : handableRequest) {
				if(handableClass == threat.getClass()) {
					canHandle = true;
					break;
				}
			}
    	}
    	return canHandle;
    }

	public void setSuccessor(Handler handler) {
		successor = handler;		
	}
	
	public Handler getSuccessor() {
		return successor;		
	}
	
	
    public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
}
