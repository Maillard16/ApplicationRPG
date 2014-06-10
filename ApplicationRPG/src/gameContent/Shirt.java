package gameContent;

import game.Player;

/**
 * Protection contre le froid
 */
public class Shirt extends Handler {

    public Shirt() {
        super(new Class[]{ColdWind.class});
    }
    
    public Shirt(Handler successor) {
        super(successor, new Class[]{ColdWind.class});
    }
    
    public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            System.out.println("La chemise vous protège du froid.");
            ((ColdWind)threat).setColdness(((ColdWind)threat).getColdness() - 2);
            
            if(((ColdWind)threat).getColdness() > 0) {
                System.out.println("Ce n'est pas suffisant.");
            }
        }
    	super.handleRequest(threat);
    }
}
