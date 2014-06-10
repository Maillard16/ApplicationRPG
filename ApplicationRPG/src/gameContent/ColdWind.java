package gameContent;

import game.Player;

public class ColdWind extends Threat {

	private int coldness;
	
    public ColdWind(int coldness) {
        this.coldness = coldness;
    }
	
	@Override
	public void dealDamage(Player player) {
		player.loseLife(coldness);
	}

	@Override
	public void attackMessage() {
		System.out.println("Une bise glaciale vous atteint.");
	}
	
	public void setColdness(int coldness) {
        this.coldness = coldness;
        if(coldness <= 0) {
            setIsThreatning(false);
        }
	}

	public int getColdness() {
		return coldness;
	}

}
