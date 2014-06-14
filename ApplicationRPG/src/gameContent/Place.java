package gameContent;

import game.Ennemi;
import game.Player;
import gameContent.handler.Coat;
import gameContent.handler.Food;
import gameContent.handler.Helmet;
import gameContent.handler.MosquitoHandler;
import gameContent.handler.Torch;
import gameContent.handler.Umbrella;
import gameContent.handler.VisionSphere;
import gameContent.handler.Weapon;
import gameContent.threat.ColdWind;
import gameContent.threat.Darkness;
import gameContent.threat.Fight;
import gameContent.threat.Hunger;
import gameContent.threat.MagicWall;
import gameContent.threat.MosquitoSwarm;
import gameContent.threat.Rockfall;

import java.util.LinkedList;
import java.util.Random;

import ui.UserInterface;

public abstract class Place {
	
	private static Random randomGenerator = new Random();
	protected String name;
	protected String firstVisitText;
	protected String visitText;
	//true si l'endroit a été visité 1 fois
	protected boolean visited;
	//endroits accessibles
	protected LinkedList<Place> connectedPlaces;
	//actions réalisables
	protected LinkedList<String> possibleActions;
	//joueur actuellement dans l'endroit
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
	
	/**
	 * Déterminer si l'endroit est accessible depuis celui-ci.
	 * @param place : endroit potentiellement accessible
	 * @return true si l'endroit est accessible depuis celui-ci
	 */
	public boolean isConnectedTo(Place place) {
		return connectedPlaces.contains(place);
	}

	/**
	 * Affichage des infos de l'endroit
	 */
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

	/**
	 * Trouver un endroit avec son nom
	 * @param name : nom de l'endroit à trouver
	 * @return endroit qui porte le nom, null sinon
	 */
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
		
	/**
	 * Méthode utilisée lors de l'entrée dans l'endroit
	 * @param player
	 */
	public void welcomPlayer(Player player) {
		this.player = player;
		showInfo();
	}
	
	public static Place 
		shore = new Place("rivage", "Marchant sur la grève vers la terre, vous tâcher de vous repérer.\nD’après la carte, au nord devrait se trouver un village du nom de Foulit. Au nord-ouest, la forêt de Sherwood. Au nord-est, le monastère côtier de Lindisfarne.", "Vous êtes un jeune aventurier ayant fui la justice de votre contrée natale.\nVous voguez vers le pays d’Orghal en traversant la mer d’Embarh.\nAlors que votre frêle esquif se rapproche de la côte vous mettez en panne, observant votre objectif.\nVous en savez peu sur ce contient. On raconte qu’il est peuplé de créatures étranges et comporte des régions aux environnements très variés.\nOn dit aussi que la vie y est rude. Les habitants y sont sans cesse sous la menace d’une nouvelle guerre entre seigneurs et certains d’entre eux seraient des plus sauvages.\nUn lieu de tous les dangers certes, mais aux grandes richesses pour celui qui est assez téméraire pour les braver. Et de toute façon, il est hors de question de retourner en arrière.\nDans la précipitation du départ vous n’avez guère emporté d’équipement. Des vêtements estivaux, une escarcelle peu remplie, un couteau et une canne à pêche de fortune pour le voyage.\nCela fait des semaines que vous êtes en mer. La faim vous tiraille. Vous êtes impatient d’accoster.\nHeureusement, vous êtes plus ou moins parvenus à tenir votre cap et êtes arrivé à l’endroit prévu.\nVotre carte vendue par des contrebandiers signale une crique proche que vous repérer au bout d’un moment avant d’y amarrer votre bateau.") {},
		sherwoodForest = new Place("foret", "Vous vous tenez dans l’herbe sous l’épaisse frondaison.", "Vous entrez dans la forêt de Sherwood.\nLes grands arbres assombrissent considérablement le ciel et le sol foisonne de verdure humide et poisseuse à cause de la mer proche.\nLa forêt est traversée par une route reliant des villages côtiers et celui de Foulit ce qui en fait une bonne tanière pour des brigands de grand-chemins. En la suivant dans l'autre sens on traverse le marais de Grimbaugh.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "chasser" : System.out.println("vous chassez");
											if(randomGenerator.nextBoolean()) {
												new Fight(new Ennemi("lièvre",4,0,4)).attack(player);
												System.out.println("vous récupérez 1 unité de nourriture");
												player.addEquipement(new Food());
											} else {
												new Fight(new Ennemi("sanglier",8,4,0)).attack(player);
												System.out.println("vous récupérez 3 unités de nourriture");
												player.addEquipement(new Food());
												player.addEquipement(new Food());
												player.addEquipement(new Food());
											}
											return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
		},
		foulitTavern = new Place("taverne", "Vous entrez dans la taverne du poney qui tousse. L’odeur de la bière, de la sueur et du renfermé vous assaille. Il vous semble qu’il n’y ait que les clients habituels, puis vous remarquer un homme au teint halé, portant un capuchon pointu, assis seul dans un coin.", "") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							//prise quete vol ferme
							case "parlerClient" : 
											System.out.println("Vous adressez un signe de tête à l’homme encapuchonné et vous asseyez à une table proche. Au bout d’un moment il se lève et s’assied devant vous.\nVous examinez son visage. Il a les yeux bridés et une moustache descendant jusqu’au menton. Il vous scrute en retour, prenant visiblement son temps pour vous jauger puis finit par parler.\n« Il paraît que tu es quelqu’un qui sait ce qui est bon pour lui. »\nIl marque une pause. Vous continuez de le toisez puis répondez.\n«  C’est possible. Après tout chaque homme se doit de se faire une place dans le monde. »\nEncore une pause. L’homme reprend.\n« Tu sembles vouloir faire la tienne au fil de l’épée. »\nVous vous figez tous deux, la main sur l’épée. Pendant un instant qui semble durer une heure vous pensez avoir affaire à un chasseur de prime. Le château du comte Von Drekkenov n’est pas si loin et il est réputé pour faire la chasse à la petite racaille.\nL’homme finit par sourire sournoisement.\n« A combien se vende les mercenaires en ce moment ? » dit-il.\nVous affichez le même sourire. La tension se relâche.\n« Je suis Subotaï, voleur et archer. Je suis Hykranien, du grand ordre de Kerlait. Je cherche un compagnon pour mes … affaires. »\nIl s’assure que personne n’écoute puis continue.\n« D’après mes informations, les moines de Lindisfarne ont été chargés de veiller sur la clé permettant d’accéder à l’œil de Magnus. »\nVoyant que vous ne réagissez pas il explique.\n« Je sais que tu es étranger. Mais je vais tâcher de parler rapidement.\nD’après la légende, c’est un orbe magique d’une puissance prodigieuse. Les elfes sont censés l’avoir abandonné dans la tour de Muytak mais personne n’a réussi à y pénétrer. Il n’y a pas de porte et les murs semblent protégés par un enchantement.»\nLes elfes ? Il paraitrait que ce sont des créatures mystiques. Dotés de grands pouvoirs magiques ils auraient foulé le monde avant les hommes. On prétend qu’ils ont défié les dieux et ont disparus.\nVous demandez : « Tu crois à ces histoires ?  Comment sais-tu que les moines détiennent un tel secret ? »\n« Mes informations sont vraies. Elles viennent d’une guilde de mages dangereux. Ils veulent avoir la certitude du contenu de la tour de Muytak. Mais j’ai besoin d’aide. « Bon admettons. Quel est ton plan ? »\n« J’ai besoin de temps pour préparer l’expédition. Procure nous des torches et retrouve-moi ici. Tu pourrais allez voir les fermes de l’autre côté du village. »\n");
											foulitFarm.addPossibleAction("volerReserve");
											foulitTavern.removePossibleAction("parlerClient");
											return;
							//prise nuit mine
							case "parlerSubotai" : 
											System.out.println("\"Bon j'ai du nouveau. Au fil des siècles, la clé de la tour, les sphères de visions, ont été perdues. On devrait les trouver dans les mines de Tenguril qui plongent dans le coeur de la montange Morltour. C'est sur le chemin de la tour de Muytak ! Allons-y, c'est vers l'est depuis le village.»\n");
											System.out.println("\"Prend ce parapluie, il y a souvent des tempêtes sur la montange\"");
											player.addEquipement(new Umbrella(2));
											foulitTavern.removePossibleAction("parlerSubotai");
											return;
						}
					}
				}
				System.out.println("commande inconnue");				
			}
		},
		foulitMarket = new Place("marché", "Vous êtes au marché de Foulit.\nVentes possibles : nourriture (prix : 5)\nAchats possibles : anti-moustique (prix : 15), épée (prix : 25)", "") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "acheter" :
								if(commandChunks.length < 2) {
									System.out.println("Préciser objet.");
									return;
								}
								switch(commandChunks[1]) {
									case "anti-moustique" :
										if(player.getMoney() >= 15) {
											System.out.println("Vous achetez un spray anti-moustique.");
											player.setMoney(player.getMoney() - 15);
											player.addEquipement(new MosquitoHandler(10));
										} else {
											System.out.println("Vous êtes trop pauvre.");
										}
										
										return;
									case "épée" :
										
										if(player.getMoney() >= 25) {
											System.out.println("Vous achetez une épée");
											player.setMoney(player.getMoney() - 25);
											player.addEquipement(new Weapon("épée", 10));
										} else {
											System.out.println("Vous êtes trop pauvre.");
										}
										
										return;
									default :
										System.out.println("impossible");
										return;
								}
							case "vendre" :
								if(commandChunks.length < 2) {
									System.out.println("Préciser objet.");
									return;
								}
								switch(commandChunks[1]) {
									case "nourriture" :
										Food playerFood = (Food) player.posessEquipement(Food.class);
										if(playerFood != null) {
											System.out.println("Vous vendez de la nourriture.");
											player.setMoney(player.getMoney() + 5);
											playerFood.setDestroyed(true);
										} else {
											System.out.println("Vous n'avez pas de nourriture à vendre.");
										}
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
		},
		foulitVillage = new Place("village", "Vous êtes sur la place du village", "Le village de Foulit est modeste et son économie agraire. Cependant, étant au carrefour de la route longeant la côte et de celle plongeant au cœur de la région d’Engall, il a au fil des ans attiré marchands et artisans et finis par mériter de figurer sur une carte.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							//prise quête chercher chat
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
						}
					}
				}
				System.out.println("commande inconnue");
			}
		},
		foulitFarm = new Place("ferme", "Vous arrivez à la ferme du vieux Gérard", "") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							case "volerReserve" : 
											System.out.println("Vous vous rapprochez sournoisement de la réserve quand, semblant surgir de nul part, le propriétaire vous interpelle dans votre dos.");
											System.out.println("\"Hola aventurier !\"");
											System.out.println("Vous vous retournez lentement. Le viel homme est connu pour sa verbe. Vous pouvez l'assomer tout de suite et prendre le risque qu'il appelle la garde ou tentez de le faire partir pour revenir à votre larcin.");
											
											foulitFarm.removePossibleAction("volerReserve");
											foulitFarm.addPossibleAction("assomerFermier");
											foulitFarm.addPossibleAction("ecouterFermier");
											return;
							case "assomerFermier" :
											System.out.println("Vous cacher Gérard dans la réserve avant de vous emparer de torches.");
											
											player.addEquipement(new Torch());
											
											foulitTavern.addPossibleAction("parlerSubotai");
											foulitFarm.removePossibleAction("assomerFermier");
											foulitFarm.removePossibleAction("ecouterFermier");
											return;
											
							case "ecouterFermier" :
											String[] text = new String[]{"\"On m’a vanté votre caractère affable et vos manières révérencieuses.\"", "\"On vous dit âpres à la tâche et bienveillant avec les humbles gens.\"", "\"Mon étançon s’est brisé et la vétusté de mon araire n’a d’égal que l’état d’affliction en lequel vous me trouvez.\"", "\"De grâce, si d’aventure le resserre de Kerwik se trouve sur votre chemin, passez y prendre ces quelques pièces ouvrées qui me font tant défaut.\"", "Vous affichez un grand sourire et hochez la tête, espérant qu'il parte bientôt.", "Il marmone un moment et détourne le regard.", "\"mmmh oui c'est bien malheureux\"", "...", "Il vous regarde, soudainement plein d'entrain", "\"Mais vous avez l'allure d'un brave jeune homme\"", "\"La bonne mère Michelle était comblée de votre action.\"", "\"Et je vous en féclicite moi-même !\"", "\"Aujourd'hui les gens ne s'entraident plus.\"", "\"De mon temps s'aurait été une honte de laisser une bonne dame comme ça dans l'embaras !\"", "\"Maintenant, avec tous ces immigrés basanés ...\"", "\"Enfin. Vous au moi êtes bien coloré !\"", "\"D'ailleurs vous êtiez venu pour quelque chose ?\"", "Vous vous figez sur place.", "\"Heu oui. J'aurais besoin de torches pour ... pour chasser les rats de la cave de monsieur Remblin.\"", "\"Ah ! Ah oui ! Bien connu le père à lui ! C'est un brave petit gars.\"", "Il se remet à marmoner.", "\"Pas comme son fils qui a épousé la Aïsha ...\"", "\"Et ben en tout cas servez vous sans problème !\"", "Vous vous retournez vers la réserve.", "\"Mmmh Ah et prenez aussi ça. Je n'en ai guère besoin dorénavant. Mais vous risquez vous de le nécessiter promptement si vous croisez des bandits sur la route de Kerwick.", "Il vous donne un casque de la réserve.", "\"Ha je me souviens du temps où je servais dans la légion !\"", "\"Nous partîmes cinq cents\"", "\"mais par un prompt renfort. Nous nous vîmes trois mille en arrivant au port.\"", "Alors qu'il se tourne en continuant de divaguer vous vous eclipsez."};								
											System.out.println("(utiliser ENTER pour faire défiler le texte)");
											
											UserInterface ui = UserInterface.getInstance();
											for(String phrase : text) {
												System.out.println(phrase);
												ui.getLine();
											}
											
											player.addEquipement(new Torch());
											player.addEquipement(new Helmet(10));
											
											foulitTavern.addPossibleAction("parlerSubotai");
											foulitFarm.removePossibleAction("assomerFermier");
											foulitFarm.removePossibleAction("ecouterFermier");
											return;
						}
					}
				}
				System.out.println("commande inconnue");			
			}
		},
		grimbaughSwamp = new Place("marais", "Vous progressez difficilement en pataugeant.", "Les marais de Grimbaugh s'étendent à perte de vue.\nL'odeur de souffre est insuportable et vos pieds s'enfoncent dans la vase.") {
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
				new MosquitoSwarm(8).attack(target);
			}
		},
		morltourMountain = new Place("montagne", "Vous grimpez la montagne enneigée.", "Vous êtes face à la montagne de Morltour. Beaucoup sont morts en essayant de passer son col.\nUne départ non préparé pourrait être fatal.") {
			public void welcomPlayer(Player target) {
				super.welcomPlayer(target);
				new ColdWind(5).attack(target);
			}
		},
		tengurilMine = new Place("mine", "Vous avancez prudemment dans les ténèbres.", "La mine de Tenguril est l'anti-chambre d'un énorme complexe minier qui s'étend dans les profondeurs de la montagne. Aujourd'hui il est abandonné et la mine permet de traverser la montagne dans ses hauteurs. De l'autre côté se trouve la tour de Muytak.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							//prise quete sphere
							case "avancer" : Darkness darkness = new Darkness();
											darkness.attack(player);
											if(!darkness.isThreatening()) {
												tengurilMine.removePossibleAction("avancer");
												tengurilMine.addPossibleAction("chercherSphere");
												tengurilMine.addConnectedPlace(muytakTower);
											}
										    return;
							case "chercherSphere" : new Hunger(3).attack(player);
											  		System.out.println("Vous trouvez les sphères de vision !");
											  		tengurilMine.removePossibleAction("chercherSphere");
										  			player.addEquipement(new VisionSphere());
										  			return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
			public void welcomPlayer(Player target) {
				super.welcomPlayer(target);
				new Rockfall(5).attack(target);
			}
		},
		muytakTower = new Place("tour", "Vous vous tenez au pied de la tour de Muytak.", "Vous êtes face à la fameuse tour de Muytak. Ses murs blanc nâcre s'élèvent sur un apic rocheux. Surmontée d'un toît de tuile noir, elle ne possède ni porte ni fenêtre. L'air autour semble vibrer d'énergie magique. Vos pensées se brouillent alors que vous la regardez trop longtemps.") {
			public void handleCommand(String command) {
				String[] commandChunks = command.split(" ");
				for (String action : possibleActions) {
					if(action.equals(commandChunks[0])) {
						switch (action) {
							//prise quete oeil Magnus
							case "avancer" : MagicWall magicWall = new MagicWall();
											 magicWall.attack(player);
											 if(!magicWall.isThreatening()) {
												 System.out.println("En regardant à travers la sphère de vision, vous voyer un monde vert vibrant d'étrange façon.\nVous voyez une balafre rouge flamboyant sur une zone de la tour.");
												 muytakTower.removePossibleAction("avancer");
												 muytakTower.addPossibleAction("entrer");
											}
										    return;
						    //fin du jeu
							case "entrer" : System.out.println("Avançant une main dans la balafre, vous êtes absorbé dans la tour.\nVous êtes plongé dans le noir pendant de longues minutes puis vous voyez une lueur bleue. En vous rapprochant vous vous rendez compte que c'est un orbe énorme couvert d'inscriptions illisibles qui flotte.");
											System.out.println("Subotai parle dans votre dos. \"Nous l'avons trouvé ! ... Il est énorme ! .... Et bien maintenant je n'ai plus besoin de toi !\"");
											new Fight(new Ennemi("guerrier",15,5,5)).attack(player);
											System.out.println("Fin du jeu !");
											muytakTower.removePossibleAction("entrer");
								  			return;
						}
					}
				}
				System.out.println("commande inconnue");
			}
		};
		//lindisfarneMonastry = new Place("monastere", "", "") {
		//};
	private static Place[] places = new Place[]{shore, sherwoodForest, grimbaughSwamp, muytakTower, tengurilMine, morltourMountain, foulitVillage, foulitTavern, foulitFarm, foulitMarket, /*lindisfarneMonastry*/};
	
	//paramétrage des lieus pour le début de la partie
	static {
		shore.addConnectedPlace(sherwoodForest);
		//shore.addConnectedPlace(lindisfarneMonastry);
		
		//lindisfarneMonastry.addConnectedPlace(shore);
		
		sherwoodForest.addPossibleAction("chasser");
		sherwoodForest.addConnectedPlace(grimbaughSwamp);
		sherwoodForest.addConnectedPlace(foulitVillage);
		sherwoodForest.addConnectedPlace(shore);
		
		grimbaughSwamp.addConnectedPlace(sherwoodForest);
		
		foulitVillage.addPossibleAction("visiter");
		foulitVillage.addConnectedPlace(foulitTavern);
		foulitVillage.addConnectedPlace(foulitFarm);
		foulitVillage.addConnectedPlace(foulitMarket);
		foulitVillage.addConnectedPlace(morltourMountain);
		foulitVillage.addConnectedPlace(sherwoodForest);
		
		foulitTavern.addConnectedPlace(foulitVillage);
		
		foulitFarm.addConnectedPlace(foulitVillage);
		
		foulitMarket.addPossibleAction("acheter");
		foulitMarket.addPossibleAction("vendre");
		foulitMarket.addConnectedPlace(foulitVillage);
		
		morltourMountain.addConnectedPlace(tengurilMine);
		morltourMountain.addConnectedPlace(foulitVillage);
		
		tengurilMine.addPossibleAction("avancer");
		tengurilMine.addConnectedPlace(morltourMountain);
		
		muytakTower.addPossibleAction("avancer");
		muytakTower.addConnectedPlace(tengurilMine);
	}
}
