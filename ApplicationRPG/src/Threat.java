
public abstract class Threat {
    private boolean isThreatening = true;

	public void setIsThreatning(boolean isThreating) {
        this.isThreatening = isThreating;
    }
    
    public boolean isThreatening() {
        return isThreatening;
    }
	
	public void attack(Player player) {
		attackMessage();
		player.undergoThreat(this);
	}
	
	public abstract void dealDamage(Player player);
	
	public abstract void attackMessage();
}
