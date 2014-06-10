package gameContent;

public class Helmet extends Handler {
	
	private int resistance;

	public Helmet(int resistance) {
		super(new Class[]{Rockfall.class, HeadBlow.class});
		this.resistance = resistance;
	}
	
	public Helmet(int resistance, Handler successor) {
		super(successor, new Class[]{Rockfall.class, HeadBlow.class});
		this.resistance = resistance;
	}

	public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            System.out.println("Le casque vous protège.");
            		
            int protectionGiven = Math.min(resistance, ((FallingThreat)threat).getThreatLevel());
            resistance -= protectionGiven;
            ((FallingThreat)threat).setThreatLevel(((FallingThreat)threat).getThreatLevel() - protectionGiven);
            
            if(((FallingThreat)threat).getThreatLevel() > 0) {
                System.out.println("Vous êtes toujours en danger.");
            } else {
                System.out.println("Le danger est écarté.");
            }
            
            if(resistance <= 0) {
                System.out.println("Le casque est détruit.");
                setDestroyed(true);
            }
        }
    	super.handleRequest(threat);
    }
}
