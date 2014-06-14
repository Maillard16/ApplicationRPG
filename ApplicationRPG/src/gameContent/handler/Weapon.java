package gameContent.handler;

import gameContent.threat.Fight;
import gameContent.threat.Threat;

import java.util.Random;

public class Weapon extends Handler {
	private static Random randomGenerator = new Random();
	private int damagePower;
	private String name;

    public Weapon(String name, int damagePower) {
        super(new Class[]{Fight.class});
    	this.damagePower = damagePower;
    	this.name = name;
    }

    public void handleRequest(Threat threat) {
    		    	
        if(canHandle(threat)) {
            System.out.println("Vous attaquez avec un " + name + ".");
            
            if(((Fight)threat).getEnnemi().getDefensePower() <= 0 || randomGenerator.nextInt(((Fight)threat).getEnnemi().getDefensePower()) < damagePower/2) {
            
	            ((Fight)threat).setEnnemiLives(((Fight)threat).getEnnemi().getLives() - damagePower);
	            
	            if(threat.isThreatening()) {
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
