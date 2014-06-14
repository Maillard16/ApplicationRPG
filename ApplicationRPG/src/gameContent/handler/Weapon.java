package gameContent.handler;

import gameContent.threat.Fight;
import gameContent.threat.Threat;

import java.util.Random;

import ui.UserInterface;

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
            UserInterface.getInstance().println("Vous attaquez avec un " + name + ".");
            
            if(((Fight)threat).getEnnemi().getDefensePower() <= 0 || randomGenerator.nextInt(((Fight)threat).getEnnemi().getDefensePower()) < damagePower/2) {
            
	            ((Fight)threat).setEnnemiLives(((Fight)threat).getEnnemi().getLives() - damagePower);
	            
	            if(threat.isThreatening()) {
	                UserInterface.getInstance().println("Le " + ((Fight)threat).getEnnemi().getName() + ", blessé, survit.");
	            } else {
	            	UserInterface.getInstance().println("Le " + ((Fight)threat).getEnnemi().getName() + " meurt.");
	            }
            } else {
            	UserInterface.getInstance().println("Vous ne blessez pas le " + ((Fight)threat).getEnnemi().getName());
            }
        }
    	super.handleRequest(threat);
    }
}
