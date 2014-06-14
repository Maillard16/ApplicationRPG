package gameContent.handler;

import ui.UserInterface;
import gameContent.threat.Darkness;
import gameContent.threat.Threat;

public class Torch extends Handler {
    public Torch() {
        super(new Class[]{Darkness.class});
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            UserInterface.getInstance().println("Vous vous éclairez avec une torche.");
            ((Darkness)threat).lightUp();
        }
    	super.handleRequest(threat);
    }
}
