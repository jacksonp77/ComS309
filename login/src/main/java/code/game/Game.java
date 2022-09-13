package code.game;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "game_name", nullable = false, unique = true)
	private String gamename;
	
	@Column(name = "gameActive")
	private boolean gameActive;
	
	public Game(String name) {
		this.gamename = name;
		this.gameActive = true;
	}
	public Game() {
	}
	
	public int getGameId() {
		return id;
	}
	
	public void setGameId(int id) {
		this.id = id;
	}
	
	public String getGameName() {
		return gamename;
	}
	
	public void setGameName(String gamename) {
		this.gamename = gamename;
	}
	
	public boolean getGameActive() {
		return gameActive;
	}
	
	public void setGameActive(boolean gameActive) {
		this.gameActive = gameActive;
	}
	

}
