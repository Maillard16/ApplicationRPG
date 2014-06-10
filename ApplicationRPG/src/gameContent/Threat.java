package gameContent;
import game.Player;


public abstract class Threat {
    private boolean isThreatening = true;
    //si true, la menace attaque en boucle jusqu'à ce qu'elle soit vaincue, sinon il n'y a qu'une seule attaque
    private boolean isPersistant = false;

	public void setIsThreatning(boolean isThreating) {
        this.isThreatening = isThreating;
    }
    
    public boolean isThreatening() {
        return isThreatening;
    }
    
	public void setIsPersistant(boolean isPersistant) {
		this.isPersistant = isPersistant;
	}
	
	public void attack(Player player) {
		do {
			attackMessage();
			player.undergoThreat(this);
		}while(isThreatening && isPersistant);
	}
	
	public abstract void dealDamage(Player player);
	
	public abstract void attackMessage();
}
