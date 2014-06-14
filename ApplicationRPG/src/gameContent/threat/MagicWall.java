package gameContent.threat;

import ui.UserInterface;
import game.Player;

public class MagicWall extends Threat {

	@Override
	public void dealDamage(Player player) {}

	@Override
	public void attackMessage() {
		UserInterface.getInstance().println("Un enchantement vous empêche de progresser.");
	}
	
	public void disappear() {
		setIsThreatning(false);
	}

}
