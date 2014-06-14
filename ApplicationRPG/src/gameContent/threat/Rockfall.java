package gameContent.threat;

import game.Player;

public class Rockfall extends PoweredThreat {

	public Rockfall(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void attackMessage() {
		System.out.println("Des rochers vous tombent dessus !");
	}

}
