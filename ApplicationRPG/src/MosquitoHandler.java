
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
            System.out.println("Le spray anti-moustique marche.");
            
            int killedMosqutoNbr = Math.min(sprayLoad, ((MosquitoSwarm)threat).getMosquitoNbr());
            sprayLoad -= killedMosqutoNbr;
            ((MosquitoSwarm)threat).setMosquitoNbr(((MosquitoSwarm)threat).getMosquitoNbr() - killedMosqutoNbr);
            
//            int tmpSprayLoad = sprayLoad;
//            sprayLoad -= ((MosquitoSwarm)threat).getMosquitoNbr();
//            ((MosquitoSwarm)threat).setMosquitoNbr(((MosquitoSwarm)threat).getMosquitoNbr() - tmpSprayLoad);
            
            System.out.println("Vous tuez " + killedMosqutoNbr + " moustiques.");
            
            if(((MosquitoSwarm)threat).getMosquitoNbr() > 0) {
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
