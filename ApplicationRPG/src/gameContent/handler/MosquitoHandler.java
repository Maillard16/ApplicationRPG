package gameContent.handler;

import gameContent.threat.MosquitoSwarm;
import gameContent.threat.Threat;

import ui.UserInterface;

public class MosquitoHandler extends Handler {
    
    private int sprayLoad;

    public MosquitoHandler(int sprayLoad) {
        super(new Class[]{MosquitoSwarm.class});
        this.sprayLoad = sprayLoad;
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
        	UserInterface.getInstance().println("Le spray anti-moustique marche.");
            
            int killedMosqutoNbr = Math.min(sprayLoad, ((MosquitoSwarm)threat).getThreatLevel());
            sprayLoad -= killedMosqutoNbr;
            ((MosquitoSwarm)threat).setThreatLevel(((MosquitoSwarm)threat).getThreatLevel() - killedMosqutoNbr);
            
            UserInterface.getInstance().println("Vous tuez " + killedMosqutoNbr + " moustiques.");
            
            if(((MosquitoSwarm)threat).isThreatening()) {
            	UserInterface.getInstance().println("Il reste des moustiques.");
            } else {
            	UserInterface.getInstance().println("Plus de moustiques.");
            }
            
            if(sprayLoad <= 0) {
            	UserInterface.getInstance().println("Le spray anti-moustique est vide.");
                setDestroyed(true);
            }
        }
    	super.handleRequest(threat);
    }
}
