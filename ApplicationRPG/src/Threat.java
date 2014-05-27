
public abstract class Threat {
    private boolean isThreatening = true;

	public void setIsThreatning(boolean isThreating) {
        this.isThreatening = isThreating;
    }
    
    public boolean isThreatening() {
        return isThreatening;
    }

	public abstract void dealDamage(Player player);
	
	public void attack(Player player) {
		attackMessage();
		player.undergoThreat(this);
	}
	
	public abstract void attackMessage();
}
