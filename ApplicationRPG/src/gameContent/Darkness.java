package gameContent;

import game.Player;

public class Darkness extends Threat {

	@Override
	public void dealDamage(Player player) {}

	@Override
	public void attackMessage() {
		System.out.println("Il fait noir. Vous ne pouvez pas avancer.");
	}
	
	public void lightUp() {
		setIsThreatning(false);
	}

}
