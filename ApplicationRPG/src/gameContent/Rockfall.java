package gameContent;

import game.Player;

public class Rockfall extends FallingThreat {

	public Rockfall(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void dealDamage(Player player) {
		player.stun();
		super.dealDamage(player);
	}

	@Override
	public void attackMessage() {
		System.out.println("Des rochers vous tombe dessus !");
	}

}
