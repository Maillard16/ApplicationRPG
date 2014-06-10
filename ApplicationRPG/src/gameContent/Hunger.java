package gameContent;

import game.Player;

public class Hunger extends Threat {

	private int hungerDuration;
	
    public Hunger(int threatLevel) {
        this.hungerDuration = threatLevel;
    }

	@Override
	public void dealDamage(Player player) {
		player.loseLife(hungerDuration * 2);
	}

	public void attackMessage() {
		System.out.println("Les recherches durent " + hungerDuration + " jours. Vous avez faim.");
	}

	public int getHungerDuration() {
		return hungerDuration;
	}
	
	public void setHungerDuration(int hungerDuration) {
		this.hungerDuration = hungerDuration;
        if(hungerDuration <= 0) {
            setIsThreatning(false);
        }
	}
}
