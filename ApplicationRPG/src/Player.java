import java.util.LinkedList;


public class Player {
	private int lives;
	private int money;
	
	private Handler equipement;
	
	public Player(int lives, int money) {
		this.lives = lives;
		this.money = money;
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
}
