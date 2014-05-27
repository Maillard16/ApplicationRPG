package gameContent;

import game.Player;

import java.util.LinkedList;

public abstract class Place {
	
	public static Place 
		shore = new Place("Rivage", "Marchant sur la grève vers la terre, vous tâcher de vous repérer.\nD’après la carte, au nord devrait se trouver un village du nom de Foulit. Au nord-ouest, la forêt de Sherwood. Au nord-est, le monastère côtier de Lindisfarne.", "Vous êtes un jeune aventurier ayant fui la justice de votre contrée natale.\nVous voguez vers le pays d’Orghal en traversant la mer d’Embarh.\nAlors que votre frêle esquif se rapproche de la côte vous mettez en panne, observant votre objectif.\nVous en savez peu sur ce contient. On raconte qu’il est peuplé de créatures étranges et comporte des régions aux environnements très variés.\nOn dit aussi que la vie y est rude. Les habitants y sont sans cesse sous la menace d’une nouvelle guerre entre seigneurs et certains d’entre eux seraient des plus sauvages.\nUn lieu de tous les dangers certes, mais aux grandes richesses pour celui qui est assez téméraire pour les braver. Et de toute façon, il est hors de question de retourner en arrière.\nDans la précipitation du départ vous n’avez guère emporté d’équipement. Des vêtements estivaux, une escarcelle peu remplie, un couteau et une canne à pêche de fortune pour le voyage.\nCela fait des semaines que vous êtes en mer. La faim vous tiraille. Vous êtes impatient d’accoster.\nHeureusement, vous êtes plus ou moins parvenus à tenir votre cap et êtes arrivé à l’endroit prévu.\nVotre carte vendue par des contrebandiers signale une crique proche que vous repérer au bout d’un moment avant d’y amarrer votre bateau.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "pecher" : System.out.println("vous pechez");
											return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void generateThreat(Player target) {
				
			}
		},
		sherwoodForest = new Place("Foret", "Vous vous tenez dans l’herbe sous l’épaisse frondaison.", "Vous entrez dans la forêt de Sherwood.\nLes grands arbres assombrissent considérablement le ciel et le sol foisonne de verdure humide et poisseuse à cause de la mer proche.\nLa forêt est traversée par une route reliant des villages côtiers et celui de Foulit. Ça en fait une bonne tanière pour des brigands de grand-chemins.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "chasser" : System.out.println("vous chassez");
											return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void generateThreat(Player target) {

			}
		},
		grimbaughSwamp = new Place("Marais", "Vous progressez difficilement en pataugant dans le marais.", "") {
//			public void handleCommand(String command) {
//				
//			}
			public void generateThreat(Player target) {
				new MosquitoSwarm(15).attack(target);
			}
		},
		muytakTower = new Place("Tour_de_Muytak", "", "") {
//			public void handleCommand(String command) {
//				
//			}
			public void generateThreat(Player target) {
				
			}
		};
	private static Place[] places = new Place[]{shore, sherwoodForest, grimbaughSwamp, muytakTower};
	
	static {
		shore.addPossibleAction("pecher");
		shore.addConnectedPlace(sherwoodForest);
		sherwoodForest.addPossibleAction("chasser");
		sherwoodForest.addConnectedPlace(shore);
		sherwoodForest.addConnectedPlace(grimbaughSwamp);
		grimbaughSwamp.addConnectedPlace(sherwoodForest);
	}
	
	public String name;
	public String firstVisitText;
	public String visitText;
	public boolean visited;
	public LinkedList<Place> connectedPlaces;
	public LinkedList<String> possibleActions;
	public Place(String name, String visitText, String firstVisitText) {
		this.name = name;
		this.visitText = visitText;
		this.firstVisitText = firstVisitText;
		visited = false;
		connectedPlaces = new LinkedList<Place>();
		possibleActions = new LinkedList<String>();
	}
	
	public void addConnectedPlace(Place place) {
		connectedPlaces.add(place);
	}

	public void addPossibleAction(String action) {
		possibleActions.add(action);
	}
	
	public boolean isConnectedTo(Place place) {
		return connectedPlaces.contains(place);
	}

	public void showInfo() {
		if(!visited) {
			System.out.println(firstVisitText);
			visited = true;
		}
		System.out.println(name + " : " + visitText);
		System.out.println("Destinations possibles : ");
		for (Place place : connectedPlaces) {
			System.out.println(place.name);
		}
		System.out.println("Actions possibles : ");
		for (String action : possibleActions) {
			System.out.println(action);
		}
		System.out.println();
	}

	public static Place getPlaceByName(String name) {
		for (Place aPlace : places) {
			if(aPlace.name.equals(name)) {
				return aPlace;
			}
		}
		return null;
	}
	
	public void handleCommand(String command) {
		System.out.println("commande inconnue");
	}
	
	public abstract void generateThreat(Player target);
}
