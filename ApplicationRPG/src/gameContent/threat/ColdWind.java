package gameContent.threat;

import ui.UserInterface;

public class ColdWind extends PoweredThreat{
	
    public ColdWind(int coldness) {
        super(coldness);
    }
	
	@Override
	public void attackMessage() {
		UserInterface.getInstance().println("Une bise glaciale vous atteint.");
	}

}
