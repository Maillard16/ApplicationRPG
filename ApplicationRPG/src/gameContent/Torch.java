package gameContent;

public class Torch extends Handler {
    public Torch() {
        super(new Class[]{Darkness.class});
    }
    
    public Torch(Handler successor) {
        super(successor, new Class[]{Darkness.class});
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            System.out.println("Vous vous éclairez avec une torche.");
            ((Darkness)threat).lightUp();;
        }
    	super.handleRequest(threat);
    }
}
