package bl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Comparator;





import org.json.simple.JSONObject;

public class Score implements Comparable<Score> {
	
	private String playerName;
	private int score;
	private Date date;
	
	public Score(String playerName,int score) {
		this.playerName = playerName;
		this.score = score;
		
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		date = new Date(currentDate.getTime());
	}
	
	public Score(String playerName,int score,Date date) {
		this.playerName = playerName;
		this.score = score;
		this.date = date;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getScore() {
		return score;
	}
	
	public Date getDate() {
		return date;
	}
	
	public static Comparator<Score> ScoreDescComperator = new Comparator<Score>() {
		
		@Override
		public int compare(Score o1, Score o2) {
			
			int score1 = o1.getScore();
			int score2 = o2.getScore();
			
			return score2 - score1;
		}
	};

	@Override
	public int compareTo(Score o) {
		
		int score  = o.getScore();
		
		return score - this.score;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString()
	{
		JSONObject obj = new JSONObject();
	
	    obj.put("name",playerName);
	    obj.put("score",new Integer(score));
	    obj.put("date",date);

	   return obj.toJSONString();
	}
}
