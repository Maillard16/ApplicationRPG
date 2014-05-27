
public class Game {

	public static void main(String[] args) {
		Player player = new Player(20, 10);
		MosquitoHandler mosquitoHandler = new MosquitoHandler(null);
		player.addEquipement(mosquitoHandler);
		
		MosquitoSwarm mosquitoSwarm = new MosquitoSwarm(15);
		
		mosquitoSwarm.attack(player);
	}
}
