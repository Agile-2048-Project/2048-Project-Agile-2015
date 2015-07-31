package bl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Data {
	
	private static Data instance = null;
	private String playerName;
	private List<Score> scores;
	private int lastScore;
	
	public static Data getInstance() {
		
		if(instance == null) {
			instance = new Data();
		}
		
		return instance;
	}
	
	protected Data() {
		scores = new ArrayList<Score>(); 
		playerName = "";
		lastScore = 0;
		Load();
	}

	public void setPlayerName(String name) {
		this.playerName = name;
		save();
		Load();
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public void setScore(int score) {
		scores.add(new Score(playerName, score));
		save();
		Load();
	}
	
	public int getBestScore() {
		Collections.sort(scores,Score.ScoreDescComperator);
		return scores.get(0).getScore();
	}
	
	public int getLastScore() {
		return lastScore;
	}
	
	public void setLastScore(int score) {
		lastScore = score;
	}
	
	public List<Score> getScores() {
		return scores;
	}
	
	@SuppressWarnings("unchecked")
	private void save() {
		
		JSONArray list = new JSONArray();;
		JSONObject obj = new JSONObject();
		
		obj.put("Name",playerName);
		
		Collections.sort(scores, Score.ScoreDescComperator);
		
		for(int i=0;i<5 && i<scores.size();i++) {
			list.add(scores.get(i).toString());
		}
		obj.put("scores", list);
		
		try {
			FileWriter file = new FileWriter("c:\\test.json");
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void Load() {
		JSONParser parser = new JSONParser();
		
		try {
			Object _obj = parser.parse(new FileReader("c:\\test.json"));
			JSONObject jsonObject = (JSONObject) _obj;
			
			JSONArray list = (JSONArray) jsonObject.get("scores");
			playerName = (String) jsonObject.get("Name");
			scores = new ArrayList<Score>();
			
			for(int i=0;i<list.size();i++) {
				
				org.json.JSONObject score = new org.json.JSONObject(list.get(i).toString());
				Date date = java.sql.Date.valueOf(score.getString("date"));
				int  _score = score.getInt("score");
				String name = score.getString("name");
				scores.add(new Score(name, _score,date));
			}
				
			if(scores.size() == 0)
				scores.add(new Score(playerName,0));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
