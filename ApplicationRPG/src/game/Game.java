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
		
		UserInterface.getInstance().println("Bienvenue dans le jeu. \"help\" affiche les commandes.\n");
		Place.shore.welcomPlayer(player);
		
		
		String command;
		String[] commandChunks;
		while(true) {
			UserInterface.getInstance().print("> ");
			command = ui.getLine();
			if(!command.equals("Console Input Error")) {
				commandChunks = command.split(" ");
				switch (commandChunks[0]) {
					case "go" :
						if(commandChunks.length < 2) {
							UserInterface.getInstance().println("Donner destination.");
							break;
						}
						
						Place place = Place.getPlaceByName(commandChunks[1]);
						if(place == null) {
							UserInterface.getInstance().println("Destination inconnue.");
						} else {
							player.goToPlace(place);
						}
						break;
					case "status" :
						UserInterface.getInstance().println("Vies : " + player.getLives());
						UserInterface.getInstance().println("Argent : " + player.getMoney());
						UserInterface.getInstance().println("Equipement : ");
						Handler equipement = player.getEquipement();
						while(equipement != null) {
							if(!equipement.isDestroyed()) {
								UserInterface.getInstance().println(equipement.getClass().getSimpleName());
							}
							equipement = equipement.getSuccessor();
						}
						break;
					case "eat" :
						Food playerFood = (Food) player.posessEquipement(Food.class);
						if(playerFood != null) {
							UserInterface.getInstance().println("Vous mangez et gagnez 3 points de vie");
							player.gainLife(3);
							playerFood.setDestroyed(true);
						} else {
							UserInterface.getInstance().println("Vous n'avez pas de nourriture à manger.");
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
		UserInterface.getInstance().println("help - afficher l'aide");
		UserInterface.getInstance().println("go x - aller à x");
		UserInterface.getInstance().println("status - afficher votre status");
		UserInterface.getInstance().println("eat");
		UserInterface.getInstance().println("exit - quitter le jeu");
		UserInterface.getInstance().println("Endroit actuel :");
		player.getCurrentPlace().showInfo();
	}

	public static void main(String[] args) {
		new Game();
	}
}
