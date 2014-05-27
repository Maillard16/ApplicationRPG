package game;

import ui.ConsoleUserInterface;
import ui.UserInterface;
import gameContent.MosquitoHandler;
import gameContent.Place;


public class Game {
	
	public Game() {
		try {
			UserInterface.register(ConsoleUserInterface.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserInterface ui = UserInterface.getInstance();
		
		Player player = new Player(10, 10, Place.shore);
		System.out.println("Bienvenue dans le jeu. \"help\" affiche les commandes.\n");
		Place.shore.showInfo();
		
		//TODO : test, à enlever
		MosquitoHandler mosquitoHandler = new MosquitoHandler(10);
		player.addEquipement(mosquitoHandler);
		
		String command;
		String[] commandChunks;
		while(true) {
			System.out.print("> ");
			command = ui.getLine();
			if(!command.equals("Console Input Error")) {
				commandChunks = command.split(" ");
				switch (commandChunks[0]) {
				case "go" :
					Place place = Place.getPlaceByName(commandChunks[1]);
					if(place == null) {
						System.out.println("Destination inconnue.");
					} else {
						player.goToPlace(place);
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
		System.out.println("exit - quitter le jeu");
		System.out.println("autres commandes selon l'endroit disponible");
	}

	public static void main(String[] args) {
		new Game();
	}
}
