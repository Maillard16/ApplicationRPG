import java.util.LinkedList;


public class Player {
	private int lives;
	private int money;
	
	private LinkedList<Handler> equipement;
	
	public Player(int lives, int money) {
		this.lives = lives;
		this.money = money;
		equipement = new LinkedList<Handler>();
	}
	
	public void addEquipement(Handler item) {
		equipement.getLast().setSuccessor(item);
		equipement.addLast(item);
	}

	public void loseLife(int lives) {
		this.lives -= lives;
		System.out.println("Vous perdez " + lives + " vies.");
		if(lives <= 0)
			System.out.println("vous êtes mort");
	}

	public void handleRequest(Threat threat) {
		if(equipement.getFirst()) {
			
		}
	}
}
