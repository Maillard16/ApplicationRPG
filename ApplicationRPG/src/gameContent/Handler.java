package gameContent;

public abstract class Handler {
    private Handler successor;
    private Class[] handableThreat;
    private boolean isDestroyed = false;

	public Handler(Class[] handableThreat) {
        this.handableThreat = handableThreat;
    }
	
	public Handler(Handler successor, Class[] handableThreat) {
        this.successor = successor;
        this.handableThreat = handableThreat;
    }
    
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
    		for (Class handableClass : handableThreat) {
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
