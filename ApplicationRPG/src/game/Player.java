package game;
import gameContent.Handler;
import gameContent.Place;
import gameContent.Threat;

import java.util.LinkedList;


public class Player {
	private int lives;
	private int money;
	
	private Handler equipement;
	
	private Place currentPlace;
	
	public Player(int lives, int money, Place place) {
		this.lives = lives;
		this.money = money;
		this.currentPlace = place;
	}
	
	public void addEquipement(Handler item) {
		if(equipement == null) {
			equipement = item;
		} else {
		    item.setSuccessor(equipement);
		    equipement = item;
		}
	}

	public void loseLife(int lives) {
		this.lives -= lives;
		System.out.println("Vous perdez " + lives + " vies.");
		if(this.lives <= 0)
			System.out.println("vous êtes mort");
	}

	public void undergoThreat(Threat threat) {
		if(equipement != null) {
			equipement.handleRequest(threat);
		}
		
		if(threat.isThreatening()) {
            System.out.println("A cours d'objet");
            threat.dealDamage(this);
		}
	}

	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}
}
