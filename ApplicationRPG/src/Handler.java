
public abstract class Handler {
    private Handler successor;
    private Class[] handableThreat;
    private boolean isDestroyed = false;
    
    public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	public Handler(Handler successor, Class[] handableThreat) {
        this.successor = successor;
        this.handableThreat = handableThreat;
    }
    
    public void handleRequest(Threat threat) {
    	if(threat.isThreatening()) {
	    	if(successor != null) {
	            successor.handleRequest(threat);
	        } else {
	            System.out.println("A cours d'objet");
	            threat.dealDamage();
	        }
    	}
    }
    
    public boolean canHandle(Threat threat) {
    	boolean canHandle = isDestroyed == false;
    	if(canHandle) {
    		canHandle = false;
    		for (Class threatClass : handableThreat) {
				if(threatClass == threat.getClass()) {
					canHandle = false;
					break;
				}
			}
    	}
    	return canHandle;
    }

	public void setSuccessor(Handler item) {
		successor = item;		
	}
}
