package gameContent.handler;

import game.Player;
import gameContent.threat.ColdWind;
import gameContent.threat.Threat;

/**
 * Protection contre le froid
 */
public class Shirt extends Handler {

    public Shirt() {
        super(new Class[]{ColdWind.class});
    }
    
    public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            System.out.println("La chemise vous protège du froid.");
            ((ColdWind)threat).setThreatLevel(((ColdWind)threat).getThreatLevel() - 2);
            
            if(threat.isThreatening()) {
                System.out.println("Ce n'est pas suffisant.");
            }
        }
    	super.handleRequest(threat);
    }
}
