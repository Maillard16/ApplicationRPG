package gameContent.threat;

public class ColdWind extends PoweredThreat{
	
    public ColdWind(int coldness) {
        super(coldness);
    }
	
	@Override
	public void attackMessage() {
		System.out.println("Une bise glaciale vous atteint.");
	}

}
