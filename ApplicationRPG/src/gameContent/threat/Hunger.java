package gameContent.threat;

import ui.UserInterface;
import game.Player;

public class Hunger extends PoweredThreat {

    public Hunger(int hungerDuration) {
        super(hungerDuration);
    }

	@Override
	public void dealDamage(Player player) {
		player.loseLife(getThreatLevel() * 2);
	}

	public void attackMessage() {
		UserInterface.getInstance().println("Les recherches durent " + getThreatLevel() + " jours. Vous avez faim.");
	}
}
