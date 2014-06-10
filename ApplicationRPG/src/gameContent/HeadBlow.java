package gameContent;

import game.Player;

public class HeadBlow extends FallingThreat {

	public HeadBlow(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void dealDamage(Player player) {
		super.dealDamage(player);
	}

	@Override
	public void attackMessage() {
		System.out.println("On vous attaque à la tête !");
	}

}
