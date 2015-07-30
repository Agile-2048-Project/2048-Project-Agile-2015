package bl;

public class Data {
	
	private static Data instance = null;
	private String playerName;
	
	public static Data getInstance() {
		
		if(instance == null) {
			instance = new Data();
		}
		
		return instance;
	}
	
	protected Data() {
		
	}

	public void setPlayerName(String name) {
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return playerName;
	}
}
