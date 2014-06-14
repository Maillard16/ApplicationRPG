package gameContent.handler;

import ui.UserInterface;
import gameContent.threat.ColdWind;
import gameContent.threat.Threat;

/**
 * Protection contre le froid
 */
public class Coat extends Handler {

    public Coat() {
        super(new Class[]{ColdWind.class});
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            UserInterface.getInstance().println("Le manteau vous protège du froid.");
            ((ColdWind)threat).setThreatLevel(0);
        }
    	super.handleRequest(threat);
    }
}
