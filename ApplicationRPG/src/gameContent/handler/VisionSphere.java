package gameContent.handler;

import ui.UserInterface;
import gameContent.threat.MagicWall;
import gameContent.threat.Threat;

public class VisionSphere extends Handler {
    public VisionSphere() {
        super(new Class[]{MagicWall.class});
    }
   
    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            UserInterface.getInstance().println("La sphère de vision révèle l'entrée !");
            ((MagicWall)threat).disappear();
        }
    	super.handleRequest(threat);
    }
}
