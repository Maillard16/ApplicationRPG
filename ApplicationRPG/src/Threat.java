
public abstract class Threat {
    private boolean isThreatening = true;
    private Player threathenedPlayer;
    
    public Player getThreathenedPlayer() {
		return threathenedPlayer;
	}

	public void setThreathenedPlayer(Player threathenedPlayer) {
		this.threathenedPlayer = threathenedPlayer;
	}

	public void setIsThreatning(boolean isThreating) {
        this.isThreatening = isThreating;
    }
    
    public boolean isThreatening() {
        return isThreatening;
    }

	public abstract void dealDamage();
	
	public void attack(Player player) {
		attackMessage();
		threathenedPlayer = player;
		player.undergoThreat(this);
	}
	
	public abstract void attackMessage();
}
