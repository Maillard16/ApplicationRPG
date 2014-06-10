package game;
import gameContent.Handler;
import gameContent.Place;
import gameContent.Threat;
import ui.ConsoleUserInterface;
import ui.UserInterface;


public class Player {
	private int lives;
	
	private int money;
	
	private Handler equipement;
	
	private Place currentPlace;
	
	private boolean stunned;
	
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
	
	public Handler getEquipement() {
		return equipement;
	}
	
	public int getLives() {
		return lives;
	}

	public void loseLife(int lives) {
		this.lives -= lives;
		UserInterface.getInstance().println("Vous perdez " + lives + " vies.");
		if(this.lives <= 0) {
			UserInterface.getInstance().println("vous êtes mort");
			System.exit(0);
		} else {
			UserInterface.getInstance().println("Il vous reste " + this.lives + " vies.");
		}
	}

	public void undergoThreat(Threat threat) {
		if(equipement != null) {
			equipement.handleRequest(threat);
		}
		
		if(threat.isThreatening()) {
			UserInterface.getInstance().println("A cours d'objet");
            threat.dealDamage(this);
		}
	}

	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}
	
	public void goToPlace(Place place) {
		if(currentPlace.isConnectedTo(place)) {
			currentPlace = place;
			place.showInfo();
			place.welcomPlayer(this);
		} else {
			ConsoleUserInterface.getInstance().println("impossible");
		}
	}

	public void stun() {
		stunned = true;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
