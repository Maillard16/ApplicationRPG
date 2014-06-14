package gameContent.threat;

import ui.UserInterface;
import game.Ennemi;
import game.Player;

public class Fight extends Threat {

	private Ennemi ennemi;
	
	public Fight(Ennemi ennemi) {
		this.ennemi = ennemi;
		setIsPersistant(true);
	}

	@Override
	public void dealDamage(Player player) {
		player.loseLife(ennemi.getAttackPower());
	}

	@Override
	public void attackMessage() {
		UserInterface.getInstance().println("Vous combattez un " + ennemi.getName());
	}

	public Ennemi getEnnemi() {
		return ennemi;
	}

	public void setEnnemiLives(int lives) {
        ennemi.setLives(lives);
        if(lives <= 0) {
            setIsThreatning(false);
        }		
	}

}
