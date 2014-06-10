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
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
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
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		foulitTavern = new Place("Taverne", "Vous entrez dans la taverne du poney qui tousse. L’odeur de la bière, de la sueur et du renfermé vous assaille. Il vous semble qu’il n’y ait que les clients habituels, puis vous remarquer un homme au teint halé, portant un capuchon pointu, assis seul dans un coin.", "") {
//			public void handleCommand(String command) {
//				
//			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		foulitMarket = new Place("Marché", "Vous êtes au marché de Foulit", "") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "acheter" :
								switch(commandChunks[1]) {
									case "anti-moustique" :
										if(player.getMoney() >= 5) {
											System.out.println("Vous achetez un spray anti-moustique.");
											player.setMoney(player.getMoney() - 5);
											player.addEquipement(new MosquitoHandler(10));
										} else {
											System.out.println("Vous êtes trop pauvre.");
										}
										
										return;
									default :
										System.out.println("impossible");
										return;
								}
							case "vendre" :
								switch(commandChunks[1]) {
								case "nouriture" :
									
									return;
								default :
									System.out.println("impossible");
									return;
							}								
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		foulitVillage = new Place("Village", "Vous êtes sur la place du village", "Le village de Foulit est modeste et son économie agraire. Cependant, étant au carrefour de la route longeant la côte et de celle plongeant au cœur de la région d’Engall, il a au fil des ans attiré marchands et artisans et finis par mériter de figurer sur une carte.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "visiter" :
								System.out.println("Vous vous promenez dans le village et  tomber sur une vieille femme affolée.");
								System.out.println("\"Pitié ! Quelqu'un aidez moi ! Mon chat est perdu dans le marais ! Je suis allergique aux moustiques je ne peux pas aller le chercher !\"");
								foulitVillage.removePossibleAction("visiter");
								grimbaughSwamp.addPossibleAction("chercherChat");
								return;
							case "rendreChat" : 
								System.out.println("\"Merci monsieur ! Merci ! Prenez ceci en retour !\"");
							    System.out.println("Vous recevez le manteau moche de la mère Michelle.");
							    player.addEquipement(new Coat());
							    foulitVillage.removePossibleAction("rendreChat");
							    foulitTavern.addPossibleAction("parlerClient");
							    return;
							case "" :
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		grimbaughSwamp = new Place("Marais", "Vous progressez difficilement en pataugeant.", "") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "chercherChat" : new Hunger(1).attack(player);
												  System.out.println("Vous trouvez le chat de la mère Michelle !");
												  grimbaughSwamp.removePossibleAction("chercherChat");
												  foulitVillage.addPossibleAction("rendreChat");
												  return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void welcomPlayer(Player target) {
				super.welcomPlayer(target);
				new MosquitoSwarm(15).attack(target);
			}
		},
		foulitFarm = new Place("Ferme", "Vous arrivez à la ferme du vieux Gérard", "") {
//			public void handleCommand(String command) {
//				
//			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		morltourMountain = new Place("Montagne", "Vous grimpez la montagne enneigée.", "") {
//			public void handleCommand(String command) {
//			
//			}
			public void welcomPlayer(Player target) {
				super.welcomPlayer(target);
				new ColdWind(5).attack(target);
			}
		},
		tengurilMine = new Place("Mine", "Vous avancez prudemment dans les ténèbres.", "") {
//			public void handleCommand(String command) {
//			
//			}
			public void welcomPlayer(Player target) {
				super.welcomPlayer(target);
				new Rockfall(5).attack(target);
			}
		},
		muytakTower = new Place("Tour", "Vous vous tenez au pied de la tour de Muytak.", "Vous êtes face à la fameuse tour de Muytak. Ses murs blanc nâcre s'élèvent sur un apic rocheux. Surmontée d'un toît de tuile noir, elle ne possède ni porte ni fenêtre. L'air autour semble vibrer d'énergie magique. Vos pensées se brouillent alors que vous la regardez trop longtemps.") {
//			public void handleCommand(String command) {
//				
//			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		},
		lindisfarneMonastry = new Place("Monastere", "", "") {
//			public void handleCommand(String command) {
//				
//			}
			public void welcomPlayer(Player target) {super.welcomPlayer(target);}
		};
	private static Place[] places = new Place[]{shore, sherwoodForest, grimbaughSwamp, muytakTower, tengurilMine, morltourMountain, foulitVillage, foulitTavern, foulitFarm, lindisfarneMonastry};
	
	static {
		shore.addPossibleAction("pecher");
		shore.addConnectedPlace(sherwoodForest);
		shore.addConnectedPlace(lindisfarneMonastry);
		
		lindisfarneMonastry.addConnectedPlace(shore);
		
		sherwoodForest.addPossibleAction("chasser");
		sherwoodForest.addConnectedPlace(grimbaughSwamp);
		sherwoodForest.addConnectedPlace(foulitVillage);
		sherwoodForest.addConnectedPlace(shore);
		
		grimbaughSwamp.addConnectedPlace(sherwoodForest);
		
		foulitVillage.addPossibleAction("visiter");
		foulitVillage.addConnectedPlace(foulitTavern);
		foulitVillage.addConnectedPlace(foulitFarm);
		foulitVillage.addConnectedPlace(morltourMountain);
		foulitVillage.addConnectedPlace(sherwoodForest);
		
		foulitTavern.addConnectedPlace(foulitVillage);
		
		foulitFarm.addConnectedPlace(foulitVillage);
		
		morltourMountain.addConnectedPlace(tengurilMine);
		
		tengurilMine.addConnectedPlace(morltourMountain);
		
		tengurilMine.addConnectedPlace(muytakTower);
		
		muytakTower.addConnectedPlace(tengurilMine);
	}
	
	protected String name;
	protected String firstVisitText;
	protected String visitText;
	protected boolean visited;
	protected LinkedList<Place> connectedPlaces;
	protected LinkedList<String> possibleActions;
	protected Player player;
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
	
	public void removePossibleAction(String action) {
		possibleActions.remove(action);
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
		
	public void welcomPlayer(Player player) {
		this.player = player;
	}
}
