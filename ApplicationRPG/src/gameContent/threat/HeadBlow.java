package gameContent.threat;

import game.Player;

public class HeadBlow extends PoweredThreat {

	public HeadBlow(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void attackMessage() {
		System.out.println("On vous attaque à la tête !");
	}

}