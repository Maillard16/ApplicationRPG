package gameContent.threat;
import game.Player;


public class MosquitoSwarm extends PoweredThreat {
    
    public MosquitoSwarm(int mosquitoNbr) {
        super(mosquitoNbr);
    }
	
	public void attackMessage() {
		System.out.println("Vous êtes attaqué par un essaim de moustique !");
	}
}
