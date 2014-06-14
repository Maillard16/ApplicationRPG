package gameContent.threat;

import game.Player;

/**
 * Menace qui dispose d'un niveau de menace.
 * Pour s'en débarrasser il faut le mettre à 0.
 */
public abstract class PoweredThreat extends Threat {
	
	private int threatLevel;
	
    public PoweredThreat(int threatLevel) {
        this.threatLevel = threatLevel;
    }

	@Override
	public void dealDamage(Player player) {
		player.loseLife(threatLevel);
	}

	public abstract void attackMessage();

	public int getThreatLevel() {
		return threatLevel;
	}
	
	public void setThreatLevel(int threatLevel) {
		this.threatLevel = threatLevel;
        if(threatLevel <= 0) {
            setIsThreatning(false);
        }
	}
}
