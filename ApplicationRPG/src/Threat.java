
public abstract class Threat {
    private boolean isThreatning = true;
    private Player threathenedPlayer;
    
    public Player getThreathenedPlayer() {
		return threathenedPlayer;
	}

	public void setThreathenedPlayer(Player threathenedPlayer) {
		this.threathenedPlayer = threathenedPlayer;
	}

	public void setIsThreatning(boolean isThreating) {
        this.isThreatning = isThreating;
    }
    
    public boolean isThreatening() {
        return isThreatning;
    }

	public abstract void dealDamage();
	
	public void attack(Player player) {
		attackMessage();
		threathenedPlayer = player;
		player.handleRequest(this);
	}
	
	public abstract void attackMessage();
}
