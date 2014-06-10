package game;

import java.util.Random;

import ui.ConsoleUserInterface;
import ui.UserInterface;
import gameContent.Food;
import gameContent.Handler;
import gameContent.Knife;
import gameContent.Place;
import gameContent.Shirt;


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
		System.out.println("Bienvenue dans le jeu. \"help\" affiche les commandes.\n");
		Place.shore.showInfo();
		
		player.addEquipement(new Shirt());
		player.addEquipement(new Knife());
		
		
		String command;
		String[] commandChunks;
		while(true) {
			System.out.print("> ");
			command = ui.getLine();
			if(!command.equals("Console Input Error")) {
				commandChunks = command.split(" ");
				switch (commandChunks[0]) { //TODO : stunned
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
		System.out.println("exit - quitter le jeu");
		System.out.println("Commandes selon l'endroit actuel :");
		player.getCurrentPlace().showInfo();
	}

	public static void main(String[] args) {
		new Game();
	}
}
