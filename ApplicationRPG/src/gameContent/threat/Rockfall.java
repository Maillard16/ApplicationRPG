package gameContent.threat;

import ui.UserInterface;
import game.Player;

public class Rockfall extends PoweredThreat {

	public Rockfall(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void attackMessage() {
		UserInterface.getInstance().println("Des rochers vous tombent dessus !");
	}

}
