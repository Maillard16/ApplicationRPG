package gameContent.threat;
import ui.UserInterface;
import game.Player;


public class MosquitoSwarm extends PoweredThreat {
    
    public MosquitoSwarm(int mosquitoNbr) {
        super(mosquitoNbr);
    }
	
	public void attackMessage() {
		UserInterface.getInstance().println("Vous êtes attaqué par un essaim de moustique !");
	}
}
