package game;

public class Ennemi {
	private String name;
	private int lives;
	private int attackPower;
	private int defensePower;
	
	public Ennemi(String name, int lives, int attackPower, int defensePower) {
		this.name = name;
		this.lives = lives;
		this.attackPower = attackPower;
		this.defensePower = defensePower;
	}
	
	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getAttackPower() {
		return attackPower;
	}
	
	public int getDefensePower() {
		return defensePower;
	}

	public String getName() {
		return name;
	}
}
