package gameContent.threat;

import ui.UserInterface;
import game.Player;

public class HeadBlow extends PoweredThreat {

	public HeadBlow(int threatLevel) {
		super(threatLevel);
	}

	@Override
	public void attackMessage() {
		UserInterface.getInstance().println("On vous attaque à la tête !");
	}

}