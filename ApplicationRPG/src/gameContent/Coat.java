package gameContent;

/**
 * Protection contre le froid
 */
public class Coat extends Handler {

    public Coat() {
        super(new Class[]{ColdWind.class});
    }
    
    public Coat(Handler successor) {
        super(successor, new Class[]{ColdWind.class});
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            System.out.println("Le manteau vous protège du froid.");
            ((ColdWind)threat).setColdness(0);
        }
    	super.handleRequest(threat);
    }
}
