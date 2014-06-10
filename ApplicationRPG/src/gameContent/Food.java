package gameContent;

public class Food extends Handler {

	public Food() {
		super(new Class[]{Hunger.class});
	}
	
	public Food(Handler successor) {
		super(successor, new Class[]{Hunger.class});
	}
	
    public void handleRequest(Threat threat) {
    	
        if(canHandle(threat)) {
            System.out.println("Vous mangez pour 1 jour.");
            setDestroyed(true);
            ((Hunger)threat).setHungerDuration(((Hunger)threat).getHungerDuration()-1);
        }
    	super.handleRequest(threat);
    }
}
