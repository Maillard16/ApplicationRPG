package gameContent;

import java.util.Random;

public class Knife extends Handler {
	
	private static Random randomGenerator = new Random();

    public Knife() {
        super(new Class[]{Fight.class});
    }
    
    public Knife(Handler successor) {
        super(successor, new Class[]{Fight.class});
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            System.out.println("Vous attaquez avec un couteau.");
            
            if(((Fight)threat).getEnnemi().getDefensePower() <= 0 || randomGenerator.nextInt(((Fight)threat).getEnnemi().getDefensePower()) < 5/2) {
            
	            ((Fight)threat).setEnnemiLives(((Fight)threat).getEnnemi().getLives() - 5);
	            
	            if(((Fight)threat).getEnnemi().getLives() > 0) {
	                System.out.println("Le " + ((Fight)threat).getEnnemi().getName() + ", blessé, survit.");
	            } else {
	            	System.out.println("Le " + ((Fight)threat).getEnnemi().getName() + " meurt.");
	            }
            } else {
            	System.out.println("Vous ne blessez pas le " + ((Fight)threat).getEnnemi().getName());
            }
        }
    	super.handleRequest(threat);
    }
}
