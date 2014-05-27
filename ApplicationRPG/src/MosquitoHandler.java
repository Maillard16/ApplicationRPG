
public class MosquitoHandler extends Handler {
    
    private int sprayLoad;

    public MosquitoHandler() {
        super(new Class[]{MosquitoSwarm.class});
        sprayLoad = 10;
    }
    
    public MosquitoHandler(Handler successor) {
        super(successor, new Class[]{MosquitoSwarm.class});
        sprayLoad = 10;
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            System.out.println("Le spray anti-moustique marche.");
            
            int tmpSprayLoad = sprayLoad;
            sprayLoad -= ((MosquitoSwarm)threat).getMosquitoNbr();
            ((MosquitoSwarm)threat).setMosquitoNbr(((MosquitoSwarm)threat).getMosquitoNbr() - tmpSprayLoad);
            
            if(((MosquitoSwarm)threat).getMosquitoNbr() >= 0) {
                System.out.println("Il reste des moustiques.");
            } else {
                System.out.println("Plus de moustiques.");
            }
            
            if(sprayLoad <= 0) {
                System.out.println("Le spray anti-moustique est vide.");
                setDestroyed(true);
            }
        }
    	super.handleRequest(threat);
    }
    
}
