package gameContent;

public class Umbrella extends Handler {
	
	private int resistance;

	public Umbrella(int resistance) {
		super(new Class[]{Rockfall.class, HeadBlow.class});
		this.resistance = resistance;
	}
	
	public Umbrella(int resistance, Handler successor) {
		super(successor, new Class[]{Rockfall.class, HeadBlow.class});
		this.resistance = resistance;
	}

	public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            System.out.println("Le parapluie vous protège.");
            		
            int protectionGiven = Math.min(resistance, ((FallingThreat)threat).getThreatLevel());
            resistance -= protectionGiven;
            ((FallingThreat)threat).setThreatLevel(((FallingThreat)threat).getThreatLevel() - protectionGiven);
            
            if(((FallingThreat)threat).getThreatLevel() > 0) {
                System.out.println("Vous êtes toujours en danger.");
            } else {
                System.out.println("Le danger est écarté.");
            }
            
            if(resistance <= 0) {
                System.out.println("Le parapluie est détruit.");
                setDestroyed(true);
            }
        }
    	super.handleRequest(threat);
    }
}
