package gameContent.handler;

import ui.UserInterface;
import gameContent.threat.PoweredThreat;
import gameContent.threat.HeadBlow;
import gameContent.threat.Rockfall;
import gameContent.threat.Threat;

public class Umbrella extends Handler {
	
	private int resistance;

	public Umbrella(int resistance) {
		super(new Class[]{Rockfall.class, HeadBlow.class});
		this.resistance = resistance;
	}

	public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            UserInterface.getInstance().println("Le parapluie vous protège.");
            		
            int protectionGiven = Math.min(resistance, ((PoweredThreat)threat).getThreatLevel());
            resistance -= protectionGiven;
            ((PoweredThreat)threat).setThreatLevel(((PoweredThreat)threat).getThreatLevel() - protectionGiven);
            
            if(threat.isThreatening()) {
                UserInterface.getInstance().println("Vous êtes toujours en danger.");
            } else {
                UserInterface.getInstance().println("Le danger est écarté.");
            }
            
            if(resistance <= 0) {
                UserInterface.getInstance().println("Le parapluie est détruit.");
                setDestroyed(true);
            }
        }
    	super.handleRequest(threat);
    }
}
