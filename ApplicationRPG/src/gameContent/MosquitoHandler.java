package gameContent;

import ui.UserInterface;

public class MosquitoHandler extends Handler {
    
    private int sprayLoad;

    public MosquitoHandler(int sprayLoad) {
        super(new Class[]{MosquitoSwarm.class});
        this.sprayLoad = sprayLoad;
    }
    
    public MosquitoHandler(int sprayLoad, Handler successor) {
        super(successor, new Class[]{MosquitoSwarm.class});
        this.sprayLoad = sprayLoad;
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
        	UserInterface.getInstance().println("Le spray anti-moustique marche.");
            
            int killedMosqutoNbr = Math.min(sprayLoad, ((MosquitoSwarm)threat).getMosquitoNbr());
            sprayLoad -= killedMosqutoNbr;
            ((MosquitoSwarm)threat).setMosquitoNbr(((MosquitoSwarm)threat).getMosquitoNbr() - killedMosqutoNbr);
            
            UserInterface.getInstance().println("Vous tuez " + killedMosqutoNbr + " moustiques.");
            
            if(((MosquitoSwarm)threat).getMosquitoNbr() > 0) {
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
