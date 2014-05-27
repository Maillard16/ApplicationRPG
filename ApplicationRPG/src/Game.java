
public class Game {

	public static void main(String[] args) {
		Player player = new Player(20, 10);
		MosquitoHandler mosquitoHandler = new MosquitoHandler(10);
		MosquitoHandler mosquitoHandler2 = new MosquitoHandler(10);
		player.addEquipement(mosquitoHandler);
		player.addEquipement(mosquitoHandler2);
		
		MosquitoSwarm mosquitoSwarm = new MosquitoSwarm(2);
		MosquitoSwarm mosquitoSwarm2 = new MosquitoSwarm(15);
		
		mosquitoSwarm.attack(player);
		mosquitoSwarm2.attack(player);
	}
}
