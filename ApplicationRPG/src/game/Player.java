package game;
import gameContent.Place;
import gameContent.handler.Handler;
import gameContent.threat.Threat;
import ui.ConsoleUserInterface;
import ui.UserInterface;


public class Player {
	private int lives;
	
	private int money;
	
	//chaine de respoonsabilité représentant l'équipement du joueur
	private Handler equipement;
	
	//position du joueur
	private Place currentPlace;
	
	public Player(int lives, int money, Place place) {
		this.lives = lives;
		this.money = money;
		this.currentPlace = place;
	}
	
	/**
	 * Ajoute un équipement au début de la chaine de responsabilité.
	 * @param item : équipement à ajouter
	 */
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
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public Place getCurrentPlace() {
		return currentPlace;
	}

	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}

	/**
	 * Perdre de la vie.
	 * @param lives : nombre de vies à perdre
	 */
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
	
	/**
	 * Gagner de la vie
	 * @param lives : nombre de vies à gagner
	 */
	public void gainLife(int lives) {
		this.lives += lives;
	}

	/**
	 * Soumet le joueur à une menace.
	 * Lance la chaine de responsabilité pour gérer la menace.
	 * @param threat : menace sur le joueur
	 */
	public void undergoThreat(Threat threat) {
		if(equipement != null) {
			equipement.handleRequest(threat);
		}
		
		if(threat.isThreatening()) {
			UserInterface.getInstance().println("A cours d'objet");
            threat.dealDamage(this);
		}
	}
	
	/**
	 * Changer d'endroit
	 * @param place : nouvel endroit
	 */
	public void goToPlace(Place place) {
		if(currentPlace.isConnectedTo(place)) {
			currentPlace = place;
			place.welcomPlayer(this);
		} else {
			ConsoleUserInterface.getInstance().println("impossible");
		}
	}
	
	/**
	 * Détermine si le joueur possède un type d'équipement.
	 * @param equipementClass : type d'équipement à trouver
	 * @return premier équipement du bon type, null sinon
	 */
	public Handler posessEquipement(Class equipementClass) {
		Handler equipement = this.equipement;
		while(equipement != null) {
			if(!equipement.isDestroyed() && equipement.getClass() == equipementClass) {
				return equipement;
			}
			equipement = equipement.getSuccessor();
		}
		return null;
	}
}
