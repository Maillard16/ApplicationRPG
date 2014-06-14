package gameContent.handler;

import ui.UserInterface;
import gameContent.threat.Hunger;
import gameContent.threat.Threat;

public class Food extends Handler {

	public Food() {
		super(new Class[]{Hunger.class});
	}
	
    public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            UserInterface.getInstance().println("Vous mangez pour 1 jour.");
            setDestroyed(true);
            ((Hunger)threat).setThreatLevel(((Hunger)threat).getThreatLevel() - 1);
        }
    	super.handleRequest(threat);
    }
}
