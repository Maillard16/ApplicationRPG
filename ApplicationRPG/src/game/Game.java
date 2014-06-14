package game;

import gameContent.Place;
import gameContent.handler.Food;
import gameContent.handler.Handler;
import gameContent.handler.Shirt;
import gameContent.handler.Weapon;
import ui.ConsoleUserInterface;
import ui.UserInterface;


public class Game {
	
	private Player player;
	
	public Game() {
		try {
			UserInterface.register(ConsoleUserInterface.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInterface ui = UserInterface.getInstance();
		
		player = new Player(10, 10, Place.shore);
		player.addEquipement(new Shirt());
		player.addEquipement(new Weapon("couteau", 5));
		
		System.out.println("Bienvenue dans le jeu. \"help\" affiche les commandes.\n");
		Place.shore.welcomPlayer(player);
		
		
		String command;
		String[] commandChunks;
		while(true) {
			System.out.print("> ");
			command = ui.getLine();
			if(!command.equals("Console Input Error")) {
				commandChunks = command.split(" ");
				switch (commandChunks[0]) {
					case "go" :
						if(commandChunks.length < 2) {
							System.out.println("Donner destination.");
							break;
						}
						
						Place place = Place.getPlaceByName(commandChunks[1]);
						if(place == null) {
							System.out.println("Destination inconnue.");
						} else {
							player.goToPlace(place);
						}
						break;
					case "status" :
						System.out.println("Vies : " + player.getLives());
						System.out.println("Argent : " + player.getMoney());
						System.out.println("Equipement : ");
						Handler equipement = player.getEquipement();
						while(equipement != null) {
							if(!equipement.isDestroyed()) {
								System.out.println(equipement.getClass().getSimpleName());
							}
							equipement = equipement.getSuccessor();
						}
						break;
					case "eat" :
						Food playerFood = (Food) player.posessEquipement(Food.class);
						if(playerFood != null) {
							System.out.println("Vous mangez et gagnez 3 points de vie");
							player.gainLife(3);
							playerFood.setDestroyed(true);
						} else {
							System.out.println("Vous n'avez pas de nourriture à manger.");
						}
						break;
					case "help" :
						showHelp();
						break;
					case "exit" :
						return;
					default:
						player.getCurrentPlace().handleCommand(command);
						break;
				}
			}
		}
	}
	
	public void showHelp() {
		System.out.println("help - afficher l'aide");
		System.out.println("go x - aller à x");
		System.out.println("status - afficher votre status");
		System.out.println("eat");
		System.out.println("exit - quitter le jeu");
		System.out.println("Endroit actuel :");
		player.getCurrentPlace().showInfo();
	}

	public static void main(String[] args) {
		new Game();
	}
}
