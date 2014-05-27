package game;

import ui.ConsoleUserInterface;
import ui.UserInterface;
import gameContent.MosquitoHandler;
import gameContent.MosquitoSwarm;
import gameContent.Place;


public class Game {

	public static void main(String[] args) {
		try {
			UserInterface.register(ConsoleUserInterface.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInterface ui = UserInterface.getInstance(); //TODO : à utiliser pour geline(), ou remplacer system.out par xx.getInstance()
		
		Place shore = new Place("rivage", "Vous êtes un jeune aventurier ayant fui la justice de votre contrée natale.\nVous voguez vers le pays d’Orghal en traversant la mer d’Embarh.\nAlors que votre frêle esquif se rapproche de la côte vous mettez en panne, observant votre objectif.\nVous en savez peu sur ce contient. On raconte qu’il est peuplé de créatures étranges et comporte des régions aux environnements très variés.\nOn dit aussi que la vie y est rude. Les habitants y sont sans cesse sous la menace d’une nouvelle guerre entre seigneurs et certains d’entre eux seraient des plus sauvages.\nUn lieu de tous les dangers certes, mais aux grandes richesses pour celui qui est assez téméraire pour les braver. Et de toute façon, il est hors de question de retourner en arrière.\nDans la précipitation du départ vous n’avez guère emporté d’équipement. Des vêtements estivaux, une escarcelle peu remplie, un couteau et une canne à pêche de fortune pour le voyage.\nCela fait des semaines que vous êtes en mer. La faim vous tiraille. Vous êtes impatient d’accoster.\nHeureusement, vous êtes plus ou moins parvenus à tenir votre cap et êtes arrivé à l’endroit prévu.\nVotre carte vendue par des contrebandiers signale une crique proche que vous repérer au bout d’un moment avant d’y amarrer votre bateau.");
		shore.addPossibleAction("pecher");
		
		Place sherwoodForest = new Place("foret de Sherwood", "Vous entrez dans la forêt de Sherwood.\nLes grands arbres assombrissent considérablement le ciel et le sol foisonne de verdure humide et poisseuse à cause de la mer proche.\nLa forêt est traversée par une route reliant des villages côtiers et celui de Foulit. Ça en fait une bonne tanière pour des brigands de grand-chemins.");
		shore.addConnectedPlace(sherwoodForest);
		sherwoodForest.addPossibleAction("chasser");
		
		Player player = new Player(10, 10, shore);
		shore.showInfo();
		goToPlace(player, sherwoodForest);
//		MosquitoHandler mosquitoHandler = new MosquitoHandler(10);
//		MosquitoHandler mosquitoHandler2 = new MosquitoHandler(10);
//		player.addEquipement(mosquitoHandler);
//		player.addEquipement(mosquitoHandler2);
//		
//		MosquitoSwarm mosquitoSwarm = new MosquitoSwarm(2);
//		MosquitoSwarm mosquitoSwarm2 = new MosquitoSwarm(15);
//		MosquitoSwarm mosquitoSwarm3 = new MosquitoSwarm(15);
//		
//		mosquitoSwarm.attack(player);
//		mosquitoSwarm2.attack(player);
//		mosquitoSwarm3.attack(player);
	}
	
	public static void goToPlace(Player player, Place place) {
		if(player.getCurrentPlace().isConnectedTo(place)) {
			player.setCurrentPlace(place);
			place.showInfo();
		} else {
			ConsoleUserInterface.getInstance().println("impossible");
		}
	}
}
