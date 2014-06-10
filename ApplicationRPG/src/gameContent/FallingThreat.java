package gameContent;

import game.Player;

public abstract class FallingThreat extends Threat {
	
	private int threatLevel;
	
    public FallingThreat(int threatLevel) {
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
	
	public int setThreatLevel(int threatLevel) {
		return this.threatLevel = threatLevel;
	}
}
