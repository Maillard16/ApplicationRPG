package gameContent;

import java.util.LinkedList;

public abstract class Place {
	public String name;
	public String firstVisitText;
	public boolean visited;
	public LinkedList<Place> connectedPlaces;
	public LinkedList<String> possibleActions;
	public Place(String name, String firstVisitText) {
		this.name = name;
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
		System.out.println("Destinations possibles : ");
		for (Place place : connectedPlaces) {
			System.out.println(place.name);
		}
		System.out.println("Actions possibles : ");
		for (String action : possibleActions) {
			System.out.println(action);
		}
	}

	public abstract boolean gererCommande(String command);
}
